import java.util.*;

public class Job_category {
    private AppController appController;
    private Scanner in;

    /*Constructor*/
    Job_category(){
        this.in =new Scanner(System.in);//new scanner to take input
        this.appController= new AppController();
    }

    public void addCate(){
        System.out.println("Since you don't like the Job Categories we have, lets insert your new Category!\n");

        //get the category code.
        System.out.println("Enter the Category code, ex->('15-1140'): ");
        String cate_code =in.nextLine();
        //get the category title
        System.out.println("Enter the Category title: ");
        String title =in.nextLine();
        //get the parent category of that category
        System.out.println("Enter its parent Category code, ex->('15-1150'): ");
        String parent_cate =in.nextLine();
        //get the category pay range high
        System.out.println("Enter the category pay range high: ");
        int pay_range_high =Integer.parseInt(in.nextLine());
        //get the category pay range low
        System.out.println("Enter the category pay range low: ");
        int pay_range_low =Integer.parseInt(in.nextLine());
        //get the course price
        this.createCate(cate_code,title,pay_range_high,pay_range_low,parent_cate);
    }

    public void removingCate(){
        //get the job category code.
        System.out.println("Enter the job category code you would like to remove,ex->('15-1150'): ");
        String cate_code =in.nextLine();
        this.deleteCate(cate_code);

    }

    /*Create job_category*/
    public void createCate(String cate_code,String title,int pay_range_high,int pay_range_low,String parent_cate){
        int result = appController.createCate(cate_code,title,pay_range_high,pay_range_low,parent_cate);
        //Checks if the process of creating the job category went well.
        if( result == -1 ){
            System.out.printf("ERROR: creating job category %s was unsuccessful\n", cate_code);
        } else {
            System.out.printf("Creating job category %s was successful\n",cate_code);
        }
    }

    /*Delete job category*/
    public void deleteCate(String cate_code){
        int result = appController.deleteCate(cate_code);
        //checks if process of deleting the job category went well.
        if( result == -1 ){
            System.out.printf("ERROR: deleting job category %s was unsuccessful\n", cate_code);
        } else {
            System.out.printf("deleting job category %s was successful\n",cate_code);
        }
    }

}
