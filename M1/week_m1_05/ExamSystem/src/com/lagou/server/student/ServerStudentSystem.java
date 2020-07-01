package com.lagou.server.student;

import com.lagou.model.ManipulationMsg;
import com.lagou.model.User;
import com.lagou.model.UserMessage;
import com.lagou.server.ServerInitClose;
import com.lagou.server.model.ManageStudent;
import com.lagou.server.model.ManageTest;

import java.io.*;
import java.util.HashMap;

public class ServerStudentSystem {
    /**
     * 使用合成复用原则
     */
    private ServerInitClose sic;
    private ManageStudent manageStudent = null;
    private ManageTest manageTest = null;
    private  String filePath = "D:/studentPsds.dat";

    private HashMap<String,String> studentsPsds = new HashMap<>();

    {
        // 加载以前的用户密码
        loadStudentsPsds();
    }

    // 加载以前的用户密码
    private void loadStudentsPsds() {
        ObjectInputStream ois = null;
        File file = new File(filePath);
        if(!file.exists()){
            return;
        }

        try {
            // 1.创建ObjectInputStream类型的对象与d:/a.dat文件关联
            ois = new ObjectInputStream(new FileInputStream(filePath));
            // 2.从输入流中读取一个对象并打印
            HashMap<String,String> tmpobj = ( HashMap<String,String> ) ois.readObject();
            System.out.println("读取的学生为：" + tmpobj);
            this.studentsPsds = ( HashMap<String,String> ) tmpobj.clone();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 3.关闭流对象并释放有关的资源
            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private  void  saveStudensPsds(){
        ObjectOutputStream oos = null;
        try {
            // 1.创建ObjectOutputStream类型的对象与d:student.dat文件关联
            oos = new ObjectOutputStream(new FileOutputStream(filePath));
            // 2.准备一个User类型的对象并初始化
            oos.writeObject(this.studentsPsds);
            System.out.println("学生账户密码保存成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭流对象并释放有关的资源
            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过构造方法实现成员变量的初始化
     * @param sic
     */
    public ServerStudentSystem(ServerInitClose sic) {
        this.sic = sic;
    }



    public ManipulationMsg  checkLogin( UserMessage umsg ){
        manageStudent = new ManageStudent();
        manageStudent.loadStudent();
        User user = umsg.getUser();
        // 用户不存在
        if("-1".equals( manageStudent.searchStudent( user.getUserName() ) )){
            System.out.println("账户不存在");
            return  new ManipulationMsg( "false", "用户名错误");
        } else {
            if(this.studentsPsds.containsKey( user.getUserName() )){
                String curPsd =  this.studentsPsds.get( user.getUserName());
                if( "123456".equals( curPsd ) ){
                    System.out.println("账户需要跟换密码");
                    return new ManipulationMsg( "success", "pleaseChangePsd");
                } else if( user.getPassword().equals( curPsd )){
                    return new ManipulationMsg( "success", "");
                }
            } else {
                this.studentsPsds.put( user.getUserName(),"123456");
                return new ManipulationMsg( "success", "pleaseChangePsd");
            }
        }
        System.out.println("密码错误");
        return new ManipulationMsg( "false", "密码错误");
    }



    public void init( String needChange) throws IOException, ClassNotFoundException {
        if( "pleaseChangePsd".equals( needChange ) ){
            this.sic.getOos().writeObject( new ManipulationMsg( "pleaseChangePsd","") );
            changePSD();

        } else {
            this.sic.getOos().writeObject( new ManipulationMsg( "notNeedChangePsd","") );
        }
        ManipulationMsg msg =  (ManipulationMsg) this.sic.getOis().readObject();
        while (!"logoutStudent".equals( msg.getType() ) ){
            String maniType = msg.getType();
            if("changePassWord".equals( maniType)){
                changePSD();
            }
            msg =  (ManipulationMsg) this.sic.getOis().readObject();
        }
        saveStudensPsds();
    }

    private void changePSD() throws IOException, ClassNotFoundException {
        ManipulationMsg changeMsg = ( ManipulationMsg) this.sic.getOis().readObject();
        this.studentsPsds.put( changeMsg.getType(),changeMsg.getMsgContent());
        System.out.println(" server change student password Success" );
        changeMsg.setType( "changeSuccess" );
        this.sic.getOos().writeObject( changeMsg );
    }


}
