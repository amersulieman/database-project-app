import java.util.*;
import java.io.File;

public class NewEmployeeProcess{
  private AppController appController;
  private Scanner in;
  /*Contstructor*/
  NewEmployeeProcess(){
    this.in = new Scanner(System.in);
    this.appController = new AppController();
  }

  public void run(){
    System.out.println("--New Employee--");

    //get person id
    System.out.print("Enter per_id of the new employee (999, 998): ");
    int per_id = in.nextInt();
    //get position code
    System.out.print("\nEnter pos_code of the position (999): ");
    String pos_code = in.next();
    //get Transcript
    System.out.println("Please enter path to transcript ('./rec/Jack_transcript.csv' , './rec/Johny_transcript.csv'): ");
    String filePath = in.next();

    //upload Transcript
    this.uploadTranscript(per_id, filePath);

    //update has populateHasSkill
    this.populateHasSkill(per_id);

    //verify has every skill
    if(!this.verifiyHasEverySkillForPosition(per_id, pos_code)){
      //offer training plan.
      this.purposeTrainingPlan(per_id, pos_code);
    } else {
      System.out.printf("Person %d has all the skills for this position. \n", per_id);
    }

  }

  public void uploadTranscript(int per_id, String filePath){
    try{

      Scanner file = new Scanner( new File(filePath) );
      while( file.hasNextLine() ){
        String line = file.nextLine();
        if( line.contains("Courses:") ){
          int numCourses = Integer.parseInt(line.split(" ")[1]);
          System.out.println("num courses: "+numCourses);
          for(int i=0; i<numCourses; i++){
            line = file.nextLine();
            String[] vars = line.split(" ");
            this.insertTakes(per_id, vars[0], vars[1], vars[2], vars[3]);
          }

          break;
        }

      }


    }
    catch(Exception e){e.printStackTrace();}

  }

  public void populateHasSkill(int per_id){
    appController.updateHasSkillOnTakes(per_id);
  }

  public boolean verifiyHasEverySkillForPosition(int per_id, String pos_code){
    ResultData data = appController.getSkillGapByPersoIdForPositionCode(per_id, pos_code);
    if(data.getRowCount() > 0 ){
      return false;
    } else{
      return true;
    }
  }

  public void purposeTrainingPlan(int per_id, String pos_code){
    ResultData data = appController.getCoursesThatTeachMissingSkillsByPersonIdForPositionCode(per_id, pos_code);
    System.out.printf("Person %s need to take the following courses to fill his/her skill gap: \n c_code: %s title: %s", per_id, data.data.get(0).get(0), data.data.get(1).get(0) );
  }

  public void insertTakes(int per_id, String sec_no, String c_code, String complete_date, String offered_by){

    //System.out.printf("%d %s %s %s %s \n", per_id, sec_no, c_code, complete_date, offered_by);
    int result = appController.insertTakes(per_id, sec_no, c_code, complete_date, offered_by);
    if( result == -1 ){
      System.out.printf("ERROR: Insert section %s for person %d unsuccesfull\n", sec_no, per_id);
    } else {
      System.out.printf("Insert section %s for person %d succesfull\n", sec_no, per_id);

    }


  }



}
