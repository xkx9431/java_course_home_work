package com.lagou.client;

import com.lagou.client.admin.ClientAdminSystem;
import com.lagou.client.student.ClientStudentSystem;
import com.lagou.model.User;
import com.lagou.model.UserMessage;

import java.io.IOException;

public class ClientView {

    /**
     * 为了可以使用输入输出流，采用合成复用原则实现
     */
    private ClientInitClose cic;

    /**
     * 通过构造方法实现成员变量的初始化
     * @param cic
     */
    public ClientView(ClientInitClose cic) {
        this.cic = cic;
    }

    /**
     * 自定义成员方法实现客户端主界面的绘制
     */

    public void clientMainPage() throws IOException, ClassNotFoundException {
        while(true) {
            System.out.println("  \n\n\t\t   在线考试系统");
            System.out.println("-------------------------------------------");
            System.out.print("   [1] 学员系统");
            System.out.println("     [2] 管理员系统");
            System.out.println("   [0] 退出系统");
            System.out.println("-------------------------------------------");
            System.out.println("请选择要进行的业务编号：");
//            Scanner sc = new Scanner(System.in);
//            int choose = sc.nextInt();
            int choose = ClientScanner.getScanner().nextInt();
            switch (choose) {
                case 1:
                    System.out.println("正在进入学员系统...");
                    clientStudentLogin();
                    break;
                case 2:
                    System.out.println("正在进入管理员系统...");
                    clientManagerLogin();
                    break;
                case 0:
                    System.out.println("正在退出系统...");
                    clientLogout();
                    return;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }

    /**
     * 自定义成员方法实现用户登出的功能
     */
    private void clientLogout() throws IOException, ClassNotFoundException {
        //String type
        String logoutType = "logoutSystem";
        this.cic.getOos().writeObject( new UserMessage(logoutType,null));
        return;
    }

    /**
     * 自定义成员方法实现客户端学生用户登录的功能
     */
    private void clientStudentLogin() throws IOException, ClassNotFoundException {
        // 1.准备管理员登录的相关数据
        ClientStudentSystem css = new ClientStudentSystem( this.cic);
        css.init();
    }

    /**
     * 自定义成员方法实现客户端管理员登录的功能
     */
    private void clientManagerLogin() throws IOException, ClassNotFoundException {
        // 1.准备管理员登录的相关数据
        ClientAdminSystem cas = new ClientAdminSystem( this.cic);
        cas.init();
    }
}
