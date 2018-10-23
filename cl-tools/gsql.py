"""
gsheet-sqlconverter

This is a CL tool for converting a google sheet to a sql script
with insert statements.

@author: Nolan Sherman

The file(s) are downloaded from google drive as TSV converted to sql insert statements
using the name of the file as the table name, and the first row's values as the column name.
They are saved in a file 'tablename-insert.sql' in the directory the script is called from.

usage:
    Converting a signgle file:
        python gsql.py convertfile 1p7gGeBmGEGMKj2kdqmjL_DIocPPoMeZD
    converting all files in given folder
        python gsql.py convertfile 1p7gGeBmGEGMKj2kdqmjL_DIocPPoMeZD
"""
from __future__ import print_function

try:
    import httplib2
    import os
    import io
    import sys

    from apiclient import discovery
    from oauth2client import client
    from oauth2client import tools
    from oauth2client.file import Storage

    from apiclient.http import MediaIoBaseDownload

    import argparse
    flags = argparse.ArgumentParser(parents=[tools.argparser])

    # #add custom flags
    # flags.add_argument("--mime", type=str, nargs=1, default=['default'],
    #     help="optional:Export type for file")
    #
    # flags.add_argument("--id", type=str, nargs=1, default=None,
    #                 required=True, help="The ID of the Google Drive file to download")
    # flags.add_argument("--toFile", type=str, nargs=1, default=None,
    #                 required=True, help="Where to save the file downloaded")

    flags.add_argument('command', type=str,
        choices=['convertfile','convertfolder'],
                            help='convertfile [converts a single google sheet into sql insert script]'+
                                'convertfolder [converts all sheets within a folder into sql insert script]')

    #The Id of the Google drive files or folders where the sheet(s) to convert are located.
    flags.add_argument('id', metavar='id', type=str, nargs='+',
                    help='The id of a google drive folder or file.')
    # flags.add_argument('-id', '--id', type=str, nargs='+', default=None,
    #                required=True, help="The ID of the Google Drive folder the Sheet(s) are located in")



    flags = flags.parse_args()
except ImportError:
    print('It looks like you are missing some dependencies.. Ensure you have Goolge Drive API client install for python.')
    print('to install run: pip install --upgrade google-api-python-client')
    quit()

thisPath = os.path.realpath(__file__)[:-len(os.path.basename(__file__))]#path to this directory

SCOPES = 'https://www.googleapis.com/auth/drive'
CLIENT_SECRET_FILE = thisPath+'client_secret.json'
APPLICATION_NAME = 'DB Project Tools'

#Drive service
service = None




def downloadDriveFileAs(mime_type, file_id, file_name):
    global service
    #file_id = '0BwwA4oUTeiV1UVNwOHItT0xfa2M'
    request = service.files().export_media(fileId=file_id, mimeType=mime_type)
    fh = io.FileIO(file_name, 'wb')
    downloader = MediaIoBaseDownload(fh, request)
    done = False
    while done is False:
        status, done = downloader.next_chunk()
        print("Download %d%%." % int(status.progress() * 100))

def get_credentials():
    """Gets valid user credentials from storage.

    If nothing has been stored, or if the stored credentials are invalid,
    the OAuth2 flow is completed to obtain the new credentials.

    Returns:
        Credentials, the obtained credential.
    """
    home_dir = os.path.expanduser('~')
    credential_dir = os.path.join(home_dir, '.credentials')
    if not os.path.exists(credential_dir):
        os.makedirs(credential_dir)
    credential_path = os.path.join(credential_dir,
                                   'gnocia-website-tools.json')

    store = Storage(credential_path)
    credentials = store.get()
    if not credentials or credentials.invalid:
        flow = client.flow_from_clientsecrets(CLIENT_SECRET_FILE, SCOPES)
        flow.user_agent = APPLICATION_NAME
        if flags:
            credentials = tools.run_flow(flow, store, flags)
        else: # Needed only for compatibility with Python 2.6
            credentials = tools.run(flow, store)
        print('Storing credentials to ' + credential_path)
    return credentials


def print_files_in_folder(service, folder_id):
    """Print files belonging to a folder.

    Args:
    service: Drive API service instance.
    folder_id: ID of the folder to print files from.
    """
    page_token = None

    folder = service.files().get(fileId=folder_id).execute()
    print('id: %s' % folder['id'])
    print('name: %s' % folder['name'])

    # children = service.children().list(folder_id).execute()
    sheets = service.files().list(q="mimeType='application/vnd.google-apps.spreadsheet' and '%s' in parents" % folder_id).execute()
    # print(sheets)

    for sheet in sheets.get('files', []):
        print(sheet.get('name'))

def convertfiles(fileIDs, service):
    #print('converting files')
    #for each id in fileIDs
    for id in fileIDs:
        #get file name from gdrive and store it
        filename=''
        try:
            file = service.files().get(fileId=id).execute()
            filename = file.get('name');
        except:
            print('ERROR: Probably the file with id %s was not found.' % id)
            quit()

        #download file from gdrive
        local_fd = io.FileIO('.gcvsql-tmp', 'wb')
        request = service.files().export_media(fileId=id, mimeType='text/tab-separated-values')
        media_request = MediaIoBaseDownload(local_fd, request)

        print('downloading and converting %s' % filename)
        while True:
            try:
                download_progress, done = media_request.next_chunk()
            except errors.HttpError, error:
                print ('An error occurred: %s' % error)
                quit()
                break
            #if download_progress:
                #print ('Download Progress: %d%%' % int(download_progress.progress() * 100))
            if done:
                break
        #convert to sql insert statements
        local_fd = io.FileIO('.gcvsql-tmp', 'r')
        convertToSQLinsert(local_fd, filename)

def convertToSQLinsert(csvfile, newFileName):
    #print('converting to sql')
    sqlfile = io.FileIO('%s-insert.sql' % newFileName, 'wb')
    columns=None
    lines = csvfile.readlines()
    i=0
    for line in lines:
        i+=1
        if i==1:#get the table columns
            columns = line.replace('\t', ', ').strip()
        else: #write
            sqlfile.write('INSERT INTO %s ( %s )\n' % (newFileName, columns) )
            values= line.replace('\t', ', ').strip()
            sqlfile.write('VALUES (%s);\n\n' % values)

def convertfolders(folderIDs, service):
    print('Getting files from folders specified')
    #define array of fileIDs
    fileIDs = []
    #for each id in folderIDs
    for id in folderIDs:
        #get all the gsheet files in folders
        sheets = service.files().list(q="mimeType='application/vnd.google-apps.spreadsheet' and '%s' in parents" % id).execute()
        # print(sheets)
        #for each file found
        for sheet in sheets.get('files', []):
            #print(sheet.get('name'))
            #append id to folderIDs
            fileIDs.append(sheet.get('id'))

    #convert files
    convertfiles(fileIDs, service)


def main():
    global service, flags
    """Shows basic usage of the Google Drive API.

    Creates a Google Drive API service object and outputs the names and IDs
    for up to 10 files.
    """
    credentials = get_credentials()
    http = credentials.authorize(httplib2.Http())
    service = discovery.build('drive', 'v3', http=http)

    command = flags.command;

    if command == 'convertfile':
        convertfiles(flags.id, service)

    elif command == 'convertfolder':
        convertfolders(flags.id, service)

    os.remove('.gcvsql-tmp')#remove the temporary file.
    quit()

    print_files_in_folder(service, folder_id);


    #Download files
    #Files to download

    #print('mimetype: ' + mimeType)
    #downlaod them
    if mimeType=='default':
        downloadDriveFile(fileID, saveToFile)
    else:
        downloadDriveFileAs(mimeType, fileID, saveToFile)


if __name__ == '__main__':
    main()
