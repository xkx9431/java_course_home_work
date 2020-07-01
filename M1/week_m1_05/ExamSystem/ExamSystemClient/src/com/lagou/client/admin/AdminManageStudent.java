package com.lagou.client.admin;

import com.lagou.client.ClientInitClose;
import com.lagou.client.ClientScanner;
import com.lagou.model.ManipulationMsg;
import com.lagou.model.Student;
import com.lagou.model.StudentMsg;

import java.io.IOException;


// 管理员的add,delete,modify,search for student
public class AdminManageStudent {
    /**
     * 为了可以使用输入输出流，采用合成复用原则实现
     */
    private ClientInitClose cic;

    /**
     * 通过构造方法实现成员变量的初始化
     * @param cic
     */

    public AdminManageStudent (ClientInitClose cic) {
        this.cic = cic;
    }

    public  void  init() throws IOException, ClassNotFoundException {

        sendServerInit();

        ManipulationMsg initRes = (ManipulationMsg) this.cic.getOis().readObject();
        // 服务器初始化学生管理系统成功
        if( "initSuccess".equals(initRes.getType()) ){
            while(true){
                System.out.println("\n\n\t\t请选择需要执行的操作：");
                System.out.println("-------------------------------------------");
                System.out.print("   [1] 增加学生");
                System.out.println("     [2] 删除学生");
                System.out.print("   [3] 修改学生信息");
                System.out.println("     [4] 查找学生信息");
                System.out.println("   [0] 返回上一级");
                System.out.println("-------------------------------------------");

                int choose = ClientScanner.getScanner().nextInt();

                switch (choose) {

                    case 1:
                        System.out.println("增加学生...");
                        addStudent();
                        break;
                    case 2:
                        System.out.println("删除学生...");
                        deleteStudent();
                        break;
                    case 3:
                        System.out.println("修改学生信息...");
                        modifyStudent();
                        break;
                    case 4:
                        System.out.println("searchStudent...");
                        searchStudent();
                        break;
                    case 0:
                        quitStudentSystem();
                        System.out.println("返回上一级...\n\n");
                        return;
                    default:
                        System.out.println("输入错误，请重新选择！");
                }

            }
        } else{
            // todo
        }


    }

    private void quitStudentSystem() throws IOException {
        String initStr = "quitStudentSystem";
        String msgContent = "";
        ManipulationMsg initMsg = new ManipulationMsg(initStr, msgContent);
        this.cic.getOos().writeObject( initMsg );
    }

    private void sendServerInit() throws IOException {
        String initStr = "studentSystemInit";
        String msgContent = "";
        ManipulationMsg initMsg = new ManipulationMsg(initStr, msgContent);
        this.cic.getOos().writeObject( initMsg );
    }


    public void addStudent() throws IOException, ClassNotFoundException {

        String addType = "addStudent";
        this.cic.getOos().writeObject( new ManipulationMsg(addType,""));

        // constructor for new Student

        Student student =createNewStudent();
        this.cic.getOos().writeObject( new StudentMsg(addType, student) );

        ManipulationMsg addStudentRes = (ManipulationMsg) this.cic.getOis().readObject();
        if("addSuccess".equals( addStudentRes.getType())){
            System.out.println(" 添加学生成功！");
        } else{
            System.out.println("未添加成功！");
        }


    }
    public void deleteStudent() throws IOException, ClassNotFoundException {
        String delType = "deleteStudent";
        System.out.printf("please enter the ID for delete: ");
        String StudentID =  ClientScanner.getScanner().next();
        ManipulationMsg delMsg = new ManipulationMsg(delType, StudentID);
        this.cic.getOos().writeObject( delMsg);
        ManipulationMsg delResMsg = (ManipulationMsg) this.cic.getOis().readObject();
        if("delSuccess".equals(delResMsg.getType() )){
            System.out.println("del success");
        } else{
            System.out.println("未添加成功！");
        }

    }

    public void modifyStudent() throws IOException, ClassNotFoundException {
        String modType = "modStudent";
        System.out.printf("please enter the ID for modify: ");
        String StudentID =  ClientScanner.getScanner().next();

        ManipulationMsg modMsg = new ManipulationMsg(modType, StudentID);
        this.cic.getOos().writeObject( modMsg);
        Student student = createNewStudent();
        //
        this.cic.getOos().writeObject( new StudentMsg("newModStudent", student) );

        ManipulationMsg modResMsg = (ManipulationMsg) this.cic.getOis().readObject();
        if("modSuccess".equals(modResMsg.getType() )){
            System.out.println("修改成功");
        } else{
            System.out.println("未成功！");
        }

    }

    public void searchStudent() throws IOException, ClassNotFoundException {
        String modType = "searchStudent";
        System.out.printf("please enter the ID for search: ");
        String StudentID =  ClientScanner.getScanner().next();

        ManipulationMsg searchMsg = new ManipulationMsg(modType, StudentID);
        this.cic.getOos().writeObject( searchMsg);
        ManipulationMsg searchResMsg = (ManipulationMsg) this.cic.getOis().readObject();
        if("searchSuccess".equals(searchResMsg.getType() )){
            System.out.println(searchResMsg.getMsgContent());
        } else{
            System.out.println("未找到！");
        }

    }

    public Student createNewStudent() {

        Student student = null;
        System.out.printf("please enter the new ID (pattern is strict! ): \n 20xx-xx-xxx is a sample\n");
        String newStudentID =  ClientScanner.getScanner().next();
        System.out.printf("please enter the new name: ");
        String newStudentName =  ClientScanner.getScanner().next();
        System.out.printf("please enter the new age (7-120) : ");
        int newStudentAge = ClientScanner.getScanner().nextInt();

        try {
            student = new Student(newStudentID,newStudentName,newStudentAge);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  student;
    }

}

