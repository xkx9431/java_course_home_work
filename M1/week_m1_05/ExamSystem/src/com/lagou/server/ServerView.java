package com.lagou.server;

import com.lagou.model.ManipulationMsg;
import com.lagou.model.UserMessage;
import com.lagou.server.admin.ServerAdminSystem;
import com.lagou.server.student.ServerStudentSystem;

import java.io.IOException;

/**
 * 编程实现服务器的主功能
 */
public class ServerView {

    /**
     * 使用合成复用原则
     */
    private ServerInitClose sic;
    private ServerDao sd;

    /**
     * 通过构造方法实现成员变量的初始化
     * @param sic
     */
    public ServerView(ServerInitClose sic, ServerDao sd) {
        this.sic = sic;
        this.sd = sd;
    }

    /**
     * 自定义成员方法实现客户端发来消息的接收并处理
     */
    public void serverReceive() throws IOException, ClassNotFoundException {

        // 验证密码登录后启动 server admin system
        while(true){
            UserMessage tum = (UserMessage) sic.getOis().readObject();
            if("managerCheck".equals(tum.getType())){
                ServerAdminSystem sas = new ServerAdminSystem(sic);
                while ( !sas.checkAdmin( tum ) ){
                    tum = (UserMessage) sic.getOis().readObject();
                }
                sas.init();
            } else if( "logoutSystem".equals(tum.getType())){
                break;
            } else if("studentCheck".equals(tum.getType())){
                ServerStudentSystem sss = new ServerStudentSystem(sic);
                ManipulationMsg checkRes = sss.checkLogin( tum );
                while ( "false".equals( checkRes.getType() ) ){
                    this.sic.getOos().writeObject( checkRes );
                    tum = (UserMessage) sic.getOis().readObject();
                    checkRes = sss.checkLogin( tum );
                }
                this.sic.getOos().writeObject( checkRes );
                sss.init( checkRes.getMsgContent() );
            } else {
                break;
            }
        }

    }
}
