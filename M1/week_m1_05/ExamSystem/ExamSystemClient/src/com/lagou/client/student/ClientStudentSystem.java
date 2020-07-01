package com.lagou.client.student;

import com.lagou.client.ClientInitClose;
import com.lagou.client.ClientScanner;
import com.lagou.model.ManipulationMsg;
import com.lagou.model.User;
import com.lagou.model.UserMessage;

import java.io.IOException;
import java.util.Scanner;

public class ClientStudentSystem {
    /**
     * 为了可以使用输入输出流，采用合成复用原则实现
     */
    private ClientInitClose cis;

    private StudentDetails studentDetails =null;



    /**
     * 通过构造方法实现成员变量的初始化
     * @param cis
     */
    public ClientStudentSystem(ClientInitClose cis) {
        this.cis = cis;
    }

    public void init() throws IOException, ClassNotFoundException {
        while (true){
            System.out.println( "[如需返回到上一级请输入]:  -1\n请输入学生的学号信息：");
            String userName = ClientScanner.getScanner().next();
            if("-1".equals(userName))return;
            System.out.println("请输入学生的密码信息：");
            String password = ClientScanner.getScanner().next();
            UserMessage tum = new UserMessage("studentCheck", new User(userName, password));
            // 2.将UserMessage类型的对象通过对象输出流发送给服务器
            cis.getOos().writeObject(tum);
            System.out.println("客户端发送学生账户信息成功！");
            // 3.接收服务器的处理结果并下一步
             ManipulationMsg checkRes = (ManipulationMsg) cis.getOis().readObject();
            if ("success".equals(checkRes.getType())) {
                System.out.println("登录成功，欢迎使用！");
               break;
            } else {
                System.out.println(checkRes.getMsgContent());
            }
        }
        switchSubTask();
    }

    private void switchSubTask() throws IOException, ClassNotFoundException {

        while(true) {
            System.out.println("\n\n\t\t请选择需要执行的任务子模块：");
            System.out.println("-------------------------------------------");
            System.out.print("   [1] 学员个人信息模块");
            System.out.println("     [2] 学员考试模块");
            System.out.println("   [0] 返回上一级");
            System.out.println(" ------------------------------------------- ");

            int choose = ClientScanner.getScanner().nextInt();
            switch (choose) {
                case 1:
                    System.out.println("学员个人信息模块...");
                    studentDetails =  new StudentDetails( this.cis) ;
                    studentDetails.init();
                    break;
                case 2:
                    System.out.println("学员考试模块...");
                    break;
                case 0:
                    System.out.println("返回上一级...\n\n");
                    if(null != studentDetails){
                    }
                    return;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }



}
