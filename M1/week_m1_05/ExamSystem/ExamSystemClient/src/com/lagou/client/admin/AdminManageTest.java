package com.lagou.client.admin;

import com.lagou.client.ClientInitClose;
import com.lagou.client.ClientScanner;
import com.lagou.model.ManipulationMsg;
import com.lagou.model.Test;
import com.lagou.model.TestMsg;

import java.io.IOException;

public class AdminManageTest {
    /**
     * 为了可以使用输入输出流，采用合成复用原则实现
     */
    private ClientInitClose cic;

    /**
     * 通过构造方法实现成员变量的初始化
     * @param cic
     */

    public AdminManageTest (ClientInitClose cic) {
        this.cic = cic;
    }

    public  void  init() throws IOException, ClassNotFoundException {

        sendServerInit();

        ManipulationMsg initRes = (ManipulationMsg) this.cic.getOis().readObject();
        // 服务器初始化试题管理系统成功
        if( "initSuccess".equals(initRes.getType()) ){
            while(true){
                System.out.println("\n\n\t\t请选择需要执行的操作：");
                System.out.println("-------------------------------------------");
                System.out.print("   [1] 增加试题");
                System.out.println("     [2] 删除试题");
                System.out.print("   [3] 修改试题");
                System.out.println("     [4] 查找试题");
                System.out.println("   [0] 返回上一级 ");
                System.out.println("--------------------------------------------");

                int choose = ClientScanner.getScanner().nextInt();

                switch (choose) {

                    case 1:
                        System.out.println("增加试题...");
                        addTest();
                        break;
                    case 2:
                        System.out.println("删除试题...");
                        deleteTest();
                        break;
                    case 3:
                        System.out.println("修改试题信息...");
                        modifyTest();
                        break;
                    case 4:
                        System.out.println("查找试题...");
                        searchTest();
                        break;
                    case 0:
                        quitTestSystem();
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

    private void quitTestSystem() throws IOException {
        String initStr = "quitTestSystem";
        String msgContent = "";
        ManipulationMsg initMsg = new ManipulationMsg(initStr, msgContent);
        this.cic.getOos().writeObject( initMsg );
    }

    private void sendServerInit() throws IOException {
        String initStr = "testSystemInit";
        String msgContent = "";
        ManipulationMsg initMsg = new ManipulationMsg(initStr, msgContent);
        this.cic.getOos().writeObject( initMsg );
    }


    public void addTest() throws IOException, ClassNotFoundException {

        String addType = "addTest";
        this.cic.getOos().writeObject( new ManipulationMsg(addType,""));

        // constructor for new Test

        Test test =createNewTest();
        this.cic.getOos().writeObject( new TestMsg(addType, test) );

        ManipulationMsg addTestRes = (ManipulationMsg) this.cic.getOis().readObject();
        if("addSuccess".equals( addTestRes.getType())){
            System.out.println(" 添加试题成功！");
        } else{
            System.out.println("未添加成功！");
        }


    }
    public void deleteTest() throws IOException, ClassNotFoundException {
        String delType = "deleteTest";
        System.out.printf("please enter the ID for delete: ");
        String TestID =  ClientScanner.getScanner().next();
        ManipulationMsg delMsg = new ManipulationMsg(delType, TestID);
        this.cic.getOos().writeObject( delMsg);
        ManipulationMsg delResMsg = (ManipulationMsg) this.cic.getOis().readObject();
        if("delSuccess".equals(delResMsg.getType() )){
            System.out.println("del success");
        } else{
            System.out.println("添加失败！");
        }

    }

    public void modifyTest() throws IOException, ClassNotFoundException {
        String modType = "modTest";
        System.out.printf("please enter the ID for modify: ");
        String TestID =  ClientScanner.getScanner().next();

        ManipulationMsg modMsg = new ManipulationMsg(modType, TestID);
        this.cic.getOos().writeObject( modMsg);
        Test student = createNewTest();
        //
        this.cic.getOos().writeObject( new TestMsg("newModTest", student) );

        ManipulationMsg modResMsg = (ManipulationMsg) this.cic.getOis().readObject();
        if("modSuccess".equals(modResMsg.getType() )){
            System.out.println("修改成功");
        } else{
            System.out.println("未成功！");
        }

    }

    public void searchTest() throws IOException, ClassNotFoundException {
        String modType = "searchTest";
        System.out.printf("please enter the testID for search: ");
        String TestID =  ClientScanner.getScanner().next();
        ManipulationMsg searchMsg = new ManipulationMsg( modType, TestID );
        this.cic.getOos().writeObject( searchMsg);
        ManipulationMsg searchResMsg = (ManipulationMsg) this.cic.getOis().readObject();
        if("searchSuccess".equals(searchResMsg.getType() )){
            System.out.println(searchResMsg.getMsgContent());
        } else{
            System.out.println("试题不存在！");
        }

    }

    public Test createNewTest() {

        Test test = null;
        System.out.printf("please enter the new ID: ");
        String newTestID =  ClientScanner.getScanner().next();
        System.out.printf("please enter the new content: ");
        String newTestContent =  ClientScanner.getScanner().next();
        System.out.printf("please enter the new answer: ");
        String  newTestAnswer = ClientScanner.getScanner().next();
        System.out.printf("please enter the new score: ");
        int  newTestScore = ClientScanner.getScanner().nextInt();

        try {
            test = new Test(newTestID,newTestContent,newTestAnswer,newTestScore);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  test;
    }

}

