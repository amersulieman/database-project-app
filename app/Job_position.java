import java.util.*;

public class Job_position{
    private AppController appController;
    private Scanner in;

    /*Constructor*/
    Job_position(){
        this.in =new Scanner(System.in);//new scanner to take input
        this.appController= new AppController();
    }

    public void addPos(){
        System.out.println("Since you don't like the job positions we have, lets insert your new job position!\n");

        //get the position code.
        System.out.println("Enter the job position code, ex->(031): ");
        String pos_code =in.nextLine();
        //get the position title
        System.out.println("Enter the job position title: ");
        String pos_title =in.nextLine();
        //get the employee mode
        System.out.println("Enter the employee mode, ex->(full_time): ");
        String emp_mode =in.nextLine();
        //get the pay type
        System.out.println("Enter the pay type,ex->(salary or wage): ");
        String pay_type =in.nextLine();
        //get the category code
        System.out.println("Enter the job category code,ex->(15-1140): ");
        String cate_code =in.nextLine();
        //get the company id
        System.out.println("Enter the company ID: ");
        String comp_id =in.nextLine();
        //get the pay rate
        System.out.println("Enter the pay rate, ex->(50 or 100000): ");
        int pay_rate =Integer.parseInt(in.nextLine());
        this.createPos(pos_code,pos_title,emp_mode,pay_rate,pay_type,cate_code,comp_id);
    }

    public void removingPos(){
        //get the job position code.
        System.out.println("Enter the job position you would like to remove, ex->(031): ");
        String pos_code =in.nextLine();
        this.deletePos(pos_code);

    }

    /*Create Job position*/
    public void createPos(String pos_code,String pos_title,String emp_mode,int pay_rate,String pay_type,String cate_code,String comp_id){
        int result = appController.createPos(pos_code,pos_title,emp_mode,pay_rate,pay_type,cate_code,comp_id);
        //Checks if the process of creating the job position went well.
        if( result == -1 ){
            System.out.printf("ERROR: creating job position %s was unsuccessful\n", pos_code);
        } else {
            System.out.printf("Creating job position %s was successful\n",pos_code);
        }
    }

    /*Delete job position*/
    public void deletePos(String pos_code){
        int result = appController.deletePos(pos_code);
        //checks if process of deleting job position  went well.
        if( result == -1 ){
            System.out.printf("ERROR: deleting job position %s was unsuccessful\n", pos_code);
        } else {
            System.out.printf("deleting position code %s was successful\n",pos_code);
        }
    }

}
