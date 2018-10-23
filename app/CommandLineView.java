import java.util.*;

public class CommandLineView{

  private AppController appController;
  private Scanner scan;
  private NewEmployeeProcess empProcess;
  private Course course;
  private Job_category job_cate;
  private Job_position job_pos;

  public static void main(String[] args){
    CommandLineView cv = new CommandLineView();
    cv.run();
  }

  CommandLineView(){
    appController = new AppController();
    scan = new Scanner(System.in);
    empProcess = new NewEmployeeProcess();
    course = new Course();
    job_cate =new Job_category();
    job_pos= new Job_position();
  }

  public void run(){
    boolean isOver = false;

    while(!isOver){
      String choice = this.promptForSelection();

      //run selected Command
      String result = null;
      switch(choice){
        case "0": isOver=true; continue;
        // break;
        case "1": result = this.query1();
          break;
        case "2": result = this.query2();
          break;
        case "3": result = this.query3();
          break;
        case "4": result = this.query4();
          break;
        case "5": result = this.query5();
          break;
        case "6": result = this.query6();
          break;
        case "7a": result = this.query7a();
          break;
        case "7b": result = this.query7b();
          break;
        case "8": result = this.query8();
          break;
        case "9": result = this.query9();
          break;
        case "10": result = this.query10();
          break;
        case "11": result = this.query11();
          break;
          case "12": result = this.query12();
            break;
        case "13": result = this.query13();
          break;
        case "14": result = this.query14();
          break;
        case "15": result = this.query15();
          break;
        case "16": result = this.query16();
          break;
        case "17": result = this.query17();
          break;
        case "18": result = this.query18();
          break;
        case "19": result = this.query19();
          break;
        case "20": result = this.query20();
          break;
        case "21": result = this.query21();
          break;
        case "22": result = this.query22();
          break;
        case "23a": result = this.query23a();
          break;
        case "23b": result = this.query23b();
          break;
        case "24a": result = this.query24a();
          break;
        case "24b": result = this.query24b();
          break;
        case "25a": result = this.query25a();
          break;
        case "25b": result = this.query25b();
          break;
        case "25c": result = this.query25c();
          break;
        case "25d": result = this.query25d();
          break;
        case "26": result = this.query26();
          break;
        case "27": result = this.query27();
          break;
        case "P1": result = ""; this.newEmployeProcess();
        break;
        case "P2": result = this.bp2FindQualifiedCategories();
        break;
        case "P3": result = this.bp3FindBestCandidatesForJobWithTraining();
        break;
        case "C1": result = ""; this.creatingCourse();
          break;
        case "C2": result = ""; this.deletingCourse();
          break;
        case "JC1": result = ""; this.creatingCate();
          break;
        case "JC2": result = ""; this.deletingCate();
          break;
        case "JP1": result = ""; this.createPos();
          break;
        case "JP2": result = ""; this.deletePos();
          break;
        default: result="Error: Invalid Menu Selection";
      }

      System.out.println(
      "\n\n" +
      result +
      "\n\n\n\n\n\n"
      );

      System.out.print("press Enter to continue...");
      try{
        System.in.read();
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
      } catch(Exception e){e.printStackTrace();}

    }
  }

  public String promptForSelection(){
    //Print Menu
    System.out.println(
      "-----------------Please Choose One of the Following Queries to run -----------------\n"+
      "| 0)  Exit \n" +
      "| 1)  getCompWorkersByCompId(String comp_id) \n" +
      "| 2)  getCompWorkersSalaryByCompId(String comp_id) \n" +
      "| 3)  getAllCompLaborCosts() \n" +
      "| 4)  getAllPositionsByPersonId(int per_id) \n" +
      "| 5)  getKnowledgeSkillsByPersonId(int per_id) \n" +
      "| 6)  getSkillGapOfCurrentPositionsByPersonId(int per_id) \n" +
      "| 7a) getReqKnowledgeSkillsByPositionCode(String pos_code) \n" +
      "| 7b) getReqKnowledgeSkillsByCateCode(String cate_code) \n" +
      "| 8)  getSkillGapByPersoIdForPositionCode(int per_id, String pos_code) \n" +
      "| 9)  getCoursesThatTeachMissingSkillsByPersonIdForPositionCode(int per_id, String pos_code) \n" +
      "| 10) getQuickestCoursesThatTeachMissingSkillsByPersonIdForPositionCode(int per_id, String pos_code) \n" +
      "| 11) getCheapestCoursesThatTeachMissingSkillsByPersonIdForPositionCode(int per_id, String pos_code) \n" +
      "| 12) Query 12(int per_id, pos_code) \n" +
      "| 13) jobCategory_person_qualified_for(int per_id) \n" +
      "| 14) highest_pay_rate_position(int per_id) \n" +
      "| 15) qualified_for_position(String pos_code) \n" +
      "| 16) missing_one_list(String pos_code) \n" +
      "| 17) missing_skills_missing_one_list(String pos_code) \n" +
      "| 18) people_miss_least_number_of_skills(String pos_code) \n" +
      "| 19) k_list(String pos_code,int k) \n" +
      "| 20) missed_skills_from_k_list(String pos_code,int k) \n" +
      "| 21) national_crises_needed_people(String cate_code) \n" +
      "| 22) unemployed_people_for_old_position(String pos_code) \n" +
      "| 23a) biggest_employer_by_number_employees() \n" +
      "| 23b) biggest_employer_by_payroll() \n" +
      "| 24a) biggest_sector_by_number_of_employees() \n" +
      "| 24b) biggest_sector_by_payroll() \n" +
      "| 25a) how_many_people_earning_increased() \n" +
      "| 25b) how_many_people_earning_decreased() \n" +
      "| 25c) ratio_increased_to_decreased_earnings() \n" +
      "| 25d) average_of_earning_change_for_a_sector(String sec_id) \n" +
      "| 26)  leaf_node_job_category() \n" +
      "| 27)  courses_help_jobless_people() \n" +
      "| P1) New Employee Process \n"+
      "| P2) Find The Job Categories You Qualify For \n"+
      "| P3) Find Nearly Qualified Candiates \n"+
      "| C1) Create a New Course \n" +
      "| C2) Delete a course \n"+
      "| JC1) Create a new Job Category \n"+
      "| JC2) Delete a Job Category \n"+
      "| JP1) Create a new Job Position \n"+
      "| JP2) Delete a Job Position \n"+
      "| ------------------------------------------------------------------------------------ "
    );
    //get input
    System.out.print("Please Enter the number of the query you want to run: ");

    return scan.next();
  }

  public void creatingCourse(){
    this.course.addCourse();
  }
  public void deletingCourse(){
    this.course.removingCourse();
  }
  public void creatingCate(){
    this.job_cate.addCate();
  }
  public void deletingCate(){
    this.job_cate.removingCate();
  }
  public void createPos(){
    this.job_pos.addPos();
  }
  public void deletePos(){
    this.job_pos.removingPos();
  }
  public void newEmployeProcess(){
    this.empProcess.run();
  }

  public String bp3FindBestCandidatesForJobWithTraining(){
      System.out.println("Find the candidates that will be easiest to train for a job position. Just enter the position id and we will search for you.");
      System.out.print("Enter the pos id (521, 321):");
      String pos_code = this.scan.next();
      System.out.println("Here are the following candidates that requrie the least training:");
      return appController.people_miss_least_number_of_skills(pos_code).toString();

  }

  public String bp2FindQualifiedCategories(){
      System.out.println("-- Find your dream job today, Enter your id and we will show you all the job categories you qualify for and the job with the best pay rate.");
      System.out.print("Your Id (123): ");
      int per_id = this.scan.nextInt();
      System.out.println("These are the categories you qualify for, ");
      System.out.println(this.appController.jobCategory_person_qualified_for(per_id).toString());
      System.out.println("Based on that, here is the job you qualify for with the best pay rate:");
      System.out.println( this.appController.highest_pay_rate_position(per_id).toString() );
      return "Enjoy your new job!";
  }

  public String query1(){
    System.out.println("-- getCompWorkersByCompId(comp_id) --");
    System.out.print("\nPlease enter comp_id (ex: 1943): ");
    String comp_id = this.scan.next();

    return this.appController.getCompWorkersByCompId(comp_id).toString();
  }

  public String query2(){
    System.out.println("-- getCompWorkersSalaryByCompId(comp_id) --");
    System.out.print("\nPlease enter comp_id (ex: 1943): ");
    String comp_id = this.scan.next();

    return this.appController.getCompWorkersSalaryByCompId(comp_id).toString();
  }

  public String query3(){
    System.out.println("-- getAllCompLaborCosts() --");
    return this.appController.getAllCompLaborCosts().toString();
  }

  public String query4(){
    System.out.println("-- getAllPositionsByPersonId(per_id) --");
    System.out.print("\nPlease enter per_id (ex: 123): ");
    int per_id = this.scan.nextInt();

    return this.appController.getAllPositionsByPersonId(per_id).toString();
  }

  public String query5(){
    System.out.println("-- getKnowledgeSkillsByPersonId(per_id) --");
    System.out.print("\nPlease enter per_id (ex: 123): ");
    int per_id = this.scan.nextInt();

    return this.appController.getKnowledgeSkillsByPersonId(per_id).toString();
  }

  public String query6(){
    System.out.println("-- getSkillGapOfCurrentPositionsByPersonId(per_id) --");
    System.out.print("\nPlease enter per_id (ex: 123): ");
    int per_id = this.scan.nextInt();

    return this.appController.getSkillGapOfCurrentPositionsByPersonId(per_id).toString();
  }

  public String query7a(){
    System.out.println("-- getReqKnowledgeSkillsByPositionCode(pos_code) --");
    System.out.print("\nPlease enter pos_code (ex: 521): ");
    String pos_code = this.scan.next();

    return this.appController.getReqKnowledgeSkillsByPositionCode(pos_code).toString();
  }

  public String query7b(){
    System.out.println("-- getReqKnowledgeSkillsByCateCode(cate_code) --");
    System.out.print("\nPlease enter cate_code (ex: 15-1134): ");
    String cate_code = this.scan.next();

    return this.appController.getReqKnowledgeSkillsByCateCode(cate_code).toString();
  }

  public String query8(){
    System.out.println("-- getSkillGapByPersoIdForPositionCode(per_id, pos_code) --");

    System.out.print("\nPlease enter per_id (ex: 123): ");
    int per_id = this.scan.nextInt();
    System.out.print("\nPlease enter pos_code (ex: 321): ");
    String pos_code = this.scan.next();

    return this.appController.getSkillGapByPersoIdForPositionCode(per_id, pos_code).toString();
  }

  public String query9(){
    System.out.println("-- getCoursesThatTeachMissingSkillsByPersonIdForPositionCode(per_id, pos_code) --");

    System.out.print("\nPlease enter per_id (ex: 123): ");
    int per_id = this.scan.nextInt();
    System.out.print("\nPlease enter pos_code (ex: 321): ");
    String pos_code = this.scan.next();

    return this.appController.getCoursesThatTeachMissingSkillsByPersonIdForPositionCode(per_id, pos_code).toString();
  }

  public String query12(){
    System.out.println("-- getCoursesThatTeachMissingSkillsByPersonIdForPositionCode(per_id, pos_code) --");

    System.out.print("\nPlease enter per_id (ex: 123): ");
    int per_id = this.scan.nextInt();
    System.out.print("\nPlease enter pos_code (ex: 321): ");
    String pos_code = this.scan.next();

    return this.appController.query12(per_id, pos_code).toString();
  }

  public String query10(){
    System.out.println("-- getQuickestCoursesThatTeachMissingSkillsByPersonIdForPositionCode(per_id, pos_code) --");

    System.out.print("\nPlease enter per_id (ex: 123): ");
    int per_id = this.scan.nextInt();
    System.out.print("\nPlease enter pos_code (ex: 321): ");
    String pos_code = this.scan.next();

    return this.appController.getQuickestCoursesThatTeachMissingSkillsByPersonIdForPositionCode(per_id,pos_code).toString();
  }

  public String query11(){
    System.out.println("-- getCheapestCoursesThatTeachMissingSkillsByPersonIdForPositionCode(per_id, pos_code) --");

    System.out.print("\nPlease enter per_id (ex: 123): ");
    int per_id = this.scan.nextInt();
    System.out.print("\nPlease enter pos_code (ex: 321): ");
    String pos_code = this.scan.next();

    return this.appController.getCheapestCoursesThatTeachMissingSkillsByPersonIdForPositionCode(per_id, pos_code).toString();
  }

  public String query13(){
    System.out.println("-- jobCategory_person_qualified_for(per_id) --");
    System.out.print("\nPlease enter per_id (ex: 123): ");
    int per_id = this.scan.nextInt();

    return this.appController.jobCategory_person_qualified_for(per_id).toString();
  }

  public String query14(){
    System.out.println("-- highest_pay_rate_position(per_id) --");
    System.out.print("\nPlease enter per_id (ex: 123): ");
    int per_id = this.scan.nextInt();

    return this.appController.highest_pay_rate_position(per_id).toString();
  }
  public String query15(){
    System.out.println("-- qualified_for_position(pos_code) --");
    System.out.print("\nPlease enter pos_code (ex: 521): ");
    String pos_code = this.scan.next();

    return this.appController.qualified_for_position(pos_code).toString();
  }
  public String query16(){
    System.out.println("-- missing_one_list(pos_code) --");
    System.out.print("\nPlease enter pos_code (ex: 521): ");
    String pos_code = this.scan.next();

    return this.appController.missing_one_list(pos_code).toString();
  }
  public String query17(){
    System.out.println("-- missing_skills_missing_one_list(pos_code) --");
    System.out.print("\nPlease enter pos_code (ex: 521): ");
    String pos_code = this.scan.next();

    return this.appController.missing_skills_missing_one_list(pos_code).toString();
  }

  public String query18(){
    System.out.println("-- people_miss_least_number_of_skills( pos_code) --");
    System.out.print("\nPlease enter pos_code (ex: 521): ");
    String pos_code = this.scan.next();

    return this.appController.people_miss_least_number_of_skills(pos_code).toString();
  }

  public String query19(){
    System.out.println("-- k_list(pos_code, k) --");

    System.out.print("\nPlease enter pos_code (ex: 321): ");
    String pos_code = this.scan.next();
    System.out.print("\nPlease enter kth limit value (ex: 5): ");
    int k = this.scan.nextInt();

    return this.appController.k_list(pos_code, k).toString();
  }

  public String query20(){
    System.out.println("-- missed_skills_from_k_list(pos_code, k) --");

    System.out.print("\nPlease enter pos_code (ex: 321): ");
    String pos_code = this.scan.next();
    System.out.print("\nPlease enter kth limit value (ex: 5): ");
    int k = this.scan.nextInt();

    return this.appController.missed_skills_from_k_list(pos_code, k).toString();
  }

  public String query21(){
    System.out.println("-- national_crises_needed_people(cate_code) --");

    System.out.print("\nPlease enter cate_code (ex: 15-1134): ");
    String cate_code = this.scan.next();

    return this.appController.national_crises_needed_people(cate_code).toString();
  }

  public String query22(){
    System.out.println("-- unemployed_people_for_old_position(pos_code) --");
    System.out.print("\nPlease enter pos_code (ex: 521): ");
    String pos_code = this.scan.next();

    return this.appController.unemployed_people_for_old_position(pos_code).toString();
  }

  public String query23a(){
    System.out.println("-- biggest_employer_by_number_employees() --");
    return this.appController.biggest_employer_by_number_employees().toString();
  }

  public String query23b(){
    System.out.println("-- biggest_employer_by_payroll() --");
    return this.appController.biggest_employer_by_payroll().toString();
  }

  public String query24a(){
    System.out.println("-- biggest_sector_by_number_of_employees() --");
    return this.appController.biggest_sector_by_number_of_employees().toString();
  }

  public String query24b(){
    System.out.println("-- biggest_sector_by_payroll() --");
    return this.appController.biggest_sector_by_payroll().toString();
  }

  public String query25a(){
    System.out.println("-- how_many_people_earning_increased() --");
    return this.appController.how_many_people_earning_increased().toString();
  }

  public String query25b(){
    System.out.println("-- how_many_people_earning_decreased() --");
    return this.appController.how_many_people_earning_decreased().toString();
  }

  public String query25c(){
    System.out.println("-- ratio_increased_to_decreased_earnings() --");
    return this.appController.ratio_increased_to_decreased_earnings().toString();
  }

  public String query25d(){
    System.out.println("-- average_of_earning_change_for_a_sector(sec_id) --");
    System.out.print("\nPlease enter sec_id (ex: 511210-02): ");
    String sec_id = this.scan.next();

    return this.appController.average_of_earning_change_for_a_sector(sec_id).toString();
  }

  public String query26(){
    System.out.println("-- leaf_node_job_category() --");
    return this.appController.leaf_node_job_category().toString();
  }

  public String query27(){
    System.out.println("-- courses_help_jobless_people() --");
    return this.appController.courses_help_jobless_people().toString();
  }

}
