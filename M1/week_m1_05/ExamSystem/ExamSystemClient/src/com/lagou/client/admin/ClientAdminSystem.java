package com.lagou.client.admin;

import com.lagou.client.ClientInitClose;
import com.lagou.client.ClientScanner;
import com.lagou.model.ManipulationMsg;
import com.lagou.model.User;
import com.lagou.model.UserMessage;

import java.io.IOException;

public class ClientAdminSystem {
    /**
     * 为了可以使用输入输出流，采用合成复用原则实现
     */
    private ClientInitClose cic;

    /**
     * 通过构造方法实现成员变量的初始化
     * @param cic
     */

    public ClientAdminSystem(ClientInitClose cic) {
        this.cic = cic;
    }

    public void init() throws IOException, ClassNotFoundException {

        while (true){

            System.out.println( "[如需返回到上一级请输入]:  -1\n请输入管理员的账户信息：");
            String userName = ClientScanner.getScanner().next();
            if("-1".equals(userName))return;
            System.out.println("请输入管理员的密码信息：");
            String password = ClientScanner.getScanner().next();
            UserMessage tum = new UserMessage("managerCheck", new User(userName, password));
            // 2.将UserMessage类型的对象通过对象输出流发送给服务器
            cic.getOos().writeObject(tum);
            System.out.println("客户端发送管理员账户信息成功！");
            // 3.接收服务器的处理结果并给出提示
            tum = (UserMessage) cic.getOis().readObject();
            if ("success".equals(tum.getType())) {
                System.out.println("登录成功，欢迎使用！");
                break;
            }
            System.out.println("用户名或密码错误,请重试！\n\n");
        }
        switchSubTask();

    }
    public void switchSubTask() throws IOException, ClassNotFoundException {
        while(true) {
            System.out.println("\n\n\t\t请选择需要执行的任务子模块：");
            System.out.println("-------------------------------------------");
            System.out.print("   [1] 学员管理模块");
            System.out.println("     [2] 试题管理模块");
            System.out.println("   [0] 返回上一级");
            System.out.println("-------------------------------------------");

            int choose = ClientScanner.getScanner().nextInt();
            switch (choose) {
                case 1:
                    System.out.println("学员管理模块...");
                    AdminManageStudent ams = new AdminManageStudent( cic );
                    ams.init();
                    break;
                case 2:
                    System.out.println("试题管理模块...");
                    AdminManageTest amt = new AdminManageTest(cic);
                    amt.init();
                    break;
                case 0:
                    System.out.println("返回上一级...\n\n");
                    String quitType = "quitAdminSystem";
                    this.cic.getOos().writeObject( new ManipulationMsg( quitType, null ));
                    return;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }

    }
}
