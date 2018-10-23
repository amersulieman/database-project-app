# database-project-app
An information system that can help optimize training programs, job assistance services, career planning, and company recruitment. 

Explaining the directories:

------------------------------------------------------------------------------------------------------------------------------

app directory:
    Directory contain the java application built to interact with the user on what queries they would like to run.
    Important:
        The file CommanLineView.java contain the main method to run the program.
        To run the program, complie the java applications using javac -classpath ojdbc8.jar *.java, to include the oracle driver 
        to run the program use : java -cp ojdbc8.jar CommandLineView
        Then your program will run and ask you for inputs on the queries 
---------------------------------------------------------------
        

cl-tools:
    Contain a script that will convert google sheet to a sql script with insert statement.
    The script written by my project partner Nolan Sherman
    
inserts:
    sql insert to the database
    
queries:
    Contain each query in a sql file
    
    
    
