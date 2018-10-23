import java.util.*;

public class Course {
    private AppController appController;
    private Scanner in;

    /*Constructor*/
    Course(){
        this.in =new Scanner(System.in);//new scanner to take input
        this.appController= new AppController();
    }

    public void addCourse(){
        System.out.println("Since you don't like the courses we have, lets insert your new course!\n");

        //get the course code.
        System.out.println("Enter the course code, ex->(JAVA-101): ");
        String c_course =in.nextLine();
        //get the course title
        System.out.println("Enter the course title: ");
        String title =in.nextLine();
        //get the course level
        System.out.println("Enter the course level, ex->(medium): ");
        String lvl =in.nextLine();
        //get the course description
        System.out.println("Enter the course description: ");
        String description =in.nextLine();
        //get the course status
        System.out.println("Enter the course status,ex->(active or expired): ");
        String status =in.nextLine();
        //get the course price
        System.out.println("Enter the course price: ");
        int price = Integer.parseInt(in.nextLine());
        this.createCourse(c_course,title,lvl,description,status,price);
    }

    public void removingCourse(){
        //get the course code. The course status will be sat to expired
        System.out.println("Enter the course code you would like to remove, ex->(JAVA-101): ");
        String c_course =in.nextLine();
        this.deleteCourse(c_course);

    }

    /*Create course*/
    public void createCourse(String c_course,String title,String lvl,String description,String status,int price){
        int result = appController.createCourse(c_course,title,lvl,description,status,price);
        //Checks if the process of creating the course went well.
        if( result == -1 ){
            System.out.printf("ERROR: creating course %s was unsuccessful\n", c_course);
        } else {
            System.out.printf("Creating course %s was successful\n",c_course);
        }
    }

    /*Delete course*/
    public void deleteCourse(String c_course){
        int result = appController.deleteCourse(c_course);
        //checks if process of updating the course status went well.
        if( result == -1 ){
            System.out.printf("ERROR: deleting course %s was unsuccessful\n", c_course);
        } else {
            System.out.printf("deleting course %s was successful\n",c_course);
        }
    }

}
