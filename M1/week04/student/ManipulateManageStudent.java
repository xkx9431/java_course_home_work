package home_work.week04.student;

import java.util.Scanner;

public class ManipulateManageStudent {

    static final char givenInput = 'a';
    static final char completeManipulate = 'b';

    static final int addStudent = 1;
    static final int removeStudent = 2;
    static  final  int updateStudent = 3;
    static  final  int searchStudent = 4;
    static  final  int showAllStudents = 5;
    static  final  int EXIT = 6;


    static char curState = givenInput;


    static ManageStudent manageStudent = new ManageStudent();


    /**
     * print the options for user to select
     */
    static void giveOptions(){
        System.out.println("--------------------------------------");
        System.out.println("please select what you want do ?");
        System.out.println("OPTIONS: ");
        System.out.println("add a student? enter : " + addStudent );
        System.out.println("remove a student? enter : " + removeStudent );
        System.out.println("update a student?  enter : " + updateStudent );
        System.out.println("search  a student? enter : " +  searchStudent );
        System.out.println("show all student info? enter : " +  showAllStudents );
        System.out.println("退出? enter : " +  EXIT );
    }

    /**
     * @return 根据用户输入，返回需要操作的选项
     */
    static int getOption(){
        Scanner scanner  = new Scanner(System.in);
        int choosed = scanner.nextInt();
        if( (choosed>=1)&&(choosed<= 6) ){
            return  choosed;
        } else{
            System.out.printf("please output the correct options");
            return getOption();
        }
    }

    /**
     * 执行查找学生信息相关工作
     */
    private static void processSearchStudent() {
        System.out.printf("please enter the studentID: ");
        Scanner scanner = new Scanner(System.in);
        String searchStudentID = scanner.next();
        manageStudent.searchStudent(searchStudentID,"");
    }

    /**
     * 执行跟新学生相关操作
     */
    private static void processUpdateStudent() {
        System.out.printf("update the student");
        System.out.printf("please enter the studentID you want update: ");
        Scanner scanner = new Scanner(System.in);
        // first get the student to update
        String searchStudentID = scanner.next();
        manageStudent.searchStudent( searchStudentID,"" );
        // get the new msg
        System.out.printf("please enter the new ID: ");
        String newStudentID = scanner.next();
        System.out.printf("please enter the new name: ");
        String newStudentName = scanner.next();
        System.out.printf("please enter the new age: ");
        int newStudentAge = scanner.nextInt();

        manageStudent.updateStudent(newStudentID,newStudentName,newStudentAge);
    }

    /**
     * 处理删除学生相关操作
     */
    private static void processRemoveStudent() {
        System.out.printf("remove the student");
        System.out.printf("please enter the studentID you want remove: ");
        Scanner scanner = new Scanner(System.in);
        // first get the student to update
        String removeStudentID = scanner.next();
        //perform remove
        manageStudent.removeStudent(removeStudentID);
    }

    /**
     * 处理添加学生相关操作
     */
    private static void processAddStudent() {
        System.out.printf("add the new student");
        Scanner scanner = new Scanner(System.in);

        // get the new student msg
        System.out.printf("please enter the new ID: ");
        String newStudentID = scanner.next();
        System.out.printf("please enter the new name: ");
        String newStudentName = scanner.next();
        System.out.printf("please enter the new age: ");
        int newStudentAge = scanner.nextInt();

        try {
            manageStudent.addStudent(newStudentID,newStudentName,newStudentAge);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        System.out.printf("当前的学生情况为: ");
        manageStudent.showAll();
        int option;

        while(curState != completeManipulate ){

            //process options
            giveOptions();
            option = getOption();
            if(option == addStudent ){
                processAddStudent();
            } else if(option == removeStudent ){
                processRemoveStudent();
            } else if(option == updateStudent ){
                processUpdateStudent();
            } else if(option == searchStudent){
                processSearchStudent();
            } else if(option == EXIT){
                curState = completeManipulate;
            } else if(option == showAllStudents){
                manageStudent.showAll();
            }

        }
        if(curState == completeManipulate){
            // ensure done?
            ensureDone();
        }

        manageStudent.showAll();


    }

    /**
     * 判断用户是否退出
     */
    private static void ensureDone() {
        System.out.printf("是否确认退出：（press）1 ？  ...继续（press）2 \n");
        Scanner scanner = new Scanner(System.in);
        String ifEnd = scanner.next();
        if(ifEnd.equals("1")){
            curState = completeManipulate;
            manageStudent.saveStudents();
        }else {
            return;
        }
    }


}
