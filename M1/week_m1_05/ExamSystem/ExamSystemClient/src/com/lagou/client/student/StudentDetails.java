package com.lagou.client.student;

import com.lagou.client.ClientInitClose;
import com.lagou.client.ClientScanner;
import com.lagou.model.ManipulationMsg;

import java.io.IOException;

public class StudentDetails {

    /**
     * 为了可以使用输入输出流，采用合成复用原则实现
     */
    private ClientInitClose cic;

    /**
     * 通过构造方法实现成员变量的初始化
     * @param cic
     */

    public StudentDetails (ClientInitClose cic) {
        this.cic = cic;
    }


    public void init() throws IOException, ClassNotFoundException {

        // process
        ManipulationMsg checkRes  = (ManipulationMsg) this.cic.getOis().readObject();
        if( "pleaseChangePsd".equals( checkRes.getType())){
                changePsd();
        }

        while(true) {
            System.out.println("\n\n\t\t请选择需要执行的任务子模块：");
            System.out.println("-------------------------------------------");
            System.out.print("   [1] 修改密码");
            System.out.println("   [0] 返回上一级");
            System.out.println(" ------------------------------------------- ");

            int choose = ClientScanner.getScanner().nextInt();
            switch (choose) {
                case 1:
                    System.out.println("修改密码模块...");
                    changePsd();
                    break;
                case 0:
                    System.out.println("返回上一级...\n\n");
                    logout();
                    return;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }

    }

    private void logout() throws IOException {
        this.cic.getOos().writeObject( new ManipulationMsg( "logoutStudent",""));
    }

    private void changePsd() throws IOException, ClassNotFoundException {
        System.out.println(" please update your password!\n");
        this.cic.getOos().writeObject( new ManipulationMsg( "changePassWord",""));
        System.out.println(" please reInput Your Student ID!\n");
        String id = ClientScanner.getScanner().next();
        System.out.println(" please reInput Your password\n");
        String pwd = ClientScanner.getScanner().next();
        this.cic.getOos().writeObject( new ManipulationMsg( id,pwd));
        ManipulationMsg msg = (ManipulationMsg) this.cic.getOis().readObject();
        if( "changeSuccess".equals( msg ) ){
            System.out.println(" 密码跟换成功！");
        }
    }
}
