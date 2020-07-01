package com.lagou.server.admin;

import com.lagou.model.*;
import com.lagou.server.ServerInitClose;
import com.lagou.server.model.ManageTest;
import com.lagou.server.model.ManageStudent;

import java.io.IOException;

public class ServerAdminSystem {
    /**
     * 使用合成复用原则
     */
    private ServerInitClose sic;

    private ManageStudent manageStudent = null;

    private ManageTest manageTest = null;

    /**
     * 通过构造方法实现成员变量的初始化
     * @param sic
     */
    public ServerAdminSystem(ServerInitClose sic) {
        this.sic = sic;
    }

    public boolean checkAdmin( UserMessage tum) throws IOException {
        User user = tum.getUser();

        if( "admin".equals(user.getUserName()) && "123456".equals(user.getPassword()) ){
            tum.setType("success");
            // 将校验结果发送给客户端
            sic.getOos().writeObject(tum);
            return true;
        } else {
            tum.setType("fail");
            sic.getOos().writeObject(tum);
            return false;
        }

    }


    public void init( ) throws IOException, ClassNotFoundException {
        System.out.println("server admin system init successful! ");
        while(true){
            ManipulationMsg tum = (ManipulationMsg) sic.getOis().readObject();
            if("studentSystemInit".equals(tum.getType() ) ){
                manageStudent = new ManageStudent();
                manageStudent.loadStudent();
                sic.getOos().writeObject( new ManipulationMsg("initSuccess","") );
                while(true){
                    ManipulationMsg maniStudentMsg = (ManipulationMsg) this.sic.getOis().readObject();
                    String maniType =  maniStudentMsg.getType();
                    if("addStudent".equals(maniType)){
                        StudentMsg studentMsg = ( StudentMsg )  this.sic.getOis().readObject();
                        while (null == studentMsg ){
                            System.out.println("the student is null");
                            studentMsg = ( StudentMsg )  this.sic.getOis().readObject();
                        }
                        manageStudent.addStudent( studentMsg.getStudent() );
                        maniStudentMsg.setType("addSuccess");
                        System.out.println("当前学生:\n"+ studentMsg.getStudent() + "\n添加成功！");
                        this.sic.getOos().writeObject( maniStudentMsg );
                    }else if("deleteStudent".equals( maniType)){
                        manageStudent.removeStudent( maniStudentMsg.getMsgContent());
                        maniStudentMsg.setType("delSuccess");
                        System.out.println("当前学生:\n"+ maniStudentMsg.getMsgContent() + "\n删除成功！");
                        this.sic.getOos().writeObject( maniStudentMsg );
                    } else if("modStudent".equals(maniType)){
                        manageStudent.removeStudent( maniStudentMsg.getMsgContent());
                        StudentMsg studentMsg = ( StudentMsg )  this.sic.getOis().readObject();
                        manageStudent.addStudent( studentMsg.getStudent() );
                        maniStudentMsg.setType("modSuccess");
                        System.out.println("当前学生:\n"+ maniStudentMsg.getMsgContent() + "\n修改成功！");
                        this.sic.getOos().writeObject( maniStudentMsg );

                    } else  if("searchStudent".equals( maniType)){
                        String searchRes =  manageStudent.searchStudent( maniStudentMsg.getMsgContent());
                        if(!"-1".equals(searchRes)){
                            maniStudentMsg.setType("searchSuccess");
                            maniStudentMsg.setMsgContent(searchRes);
                            System.out.println("当前学生:\n"+ maniStudentMsg.getMsgContent() + "\n查找成功！");
                            this.sic.getOos().writeObject(maniStudentMsg);
                        } else{
                            maniStudentMsg.setType("searchNotFound");
                            maniStudentMsg.setMsgContent(searchRes);
                            this.sic.getOos().writeObject(maniStudentMsg);
                        }
                    } else if( "quitStudentSystem".equals(maniType)){
                        manageStudent.saveStudents();
                        System.out.println("saved all students ,quit current student system");
                        break;
                    }
                }
            } else if("testSystemInit".equals( tum.getType())){
                ManageTest manageTest = new ManageTest();
                manageTest.loadTest();
                sic.getOos().writeObject( new ManipulationMsg("initSuccess","") );
                while(true){
                    ManipulationMsg maniTestMsg = (ManipulationMsg) this.sic.getOis().readObject();
                    String maniType =  maniTestMsg.getType();
                    if("addTest".equals(maniType)){
                        TestMsg testMsg = ( TestMsg )  this.sic.getOis().readObject();
                        while (null == testMsg ){
                            System.out.println("the test is null");
                            testMsg = ( TestMsg )  this.sic.getOis().readObject();
                        }
                        manageTest.addTest( testMsg.getTest() );
                        maniTestMsg.setType("addSuccess");
                        System.out.println("当前试题:\n"+ testMsg.getTest() + "\n添加成功！");
                        this.sic.getOos().writeObject( maniTestMsg );
                    }else if("deleteTest".equals( maniType)){
                        manageTest.removeTest( maniTestMsg.getMsgContent());
                        maniTestMsg.setType("delSuccess");
                        System.out.println("当前试题:\n"+ maniTestMsg.getMsgContent() + "\n删除成功！");
                        this.sic.getOos().writeObject( maniTestMsg );
                    } else if("modTest".equals(maniType)){
                        manageTest.removeTest( maniTestMsg.getMsgContent());
                        TestMsg testMsg = ( TestMsg )  this.sic.getOis().readObject();
                        manageTest.addTest( testMsg.getTest() );
                        maniTestMsg.setType("modSuccess");
                        System.out.println("当前试题:\n"+ maniTestMsg.getMsgContent() + "\n修改成功！");
                        this.sic.getOos().writeObject( maniTestMsg );

                    } else  if("searchTest".equals( maniType)){
                        String searchRes =  manageTest.searchStudent( maniTestMsg.getMsgContent());
                        if(!"-1".equals(searchRes)){
                            maniTestMsg.setType("searchSuccess");
                            maniTestMsg.setMsgContent(searchRes);
                            System.out.println("当前试题:\n"+ maniTestMsg.getMsgContent() + "\n查找成功！");
                            this.sic.getOos().writeObject(maniTestMsg);
                        }else{
                            maniTestMsg.setType("searchFail");
                            this.sic.getOos().writeObject(maniTestMsg);
                        }
                    } else if( "quitTestSystem".equals(maniType)){
                        manageTest.saveTests();
                        System.out.println("saved all tests ,quit current student system");
                        break;
                    }
                }
            }  else if( "quitAdminSystem".equals( tum.getType()) ){
                System.out.println("退出当前管理员系统");
                break;
            }
        }

    }

}
