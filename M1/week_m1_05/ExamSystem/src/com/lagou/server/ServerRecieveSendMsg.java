package com.lagou.server;

import com.lagou.model.User;
import com.lagou.model.UserMessage;

import java.io.IOException;

public class ServerRecieveSendMsg {

    private ServerInitClose sic;

    public ServerRecieveSendMsg(ServerInitClose sic) {
        this.sic = sic;
    }

    public void constructUserMessage(String type, UserMessage tum ) throws IOException {
        if("sucess".equals( type) ){
            sendSucess( tum );
        }
    }

    private void sendSucess( UserMessage tum ) throws IOException {
        tum.setType( "success" );
        sic.getOos().writeObject(tum);
        System.out.println("服务器发送校验结果成功！");
    }
    private void sendFail( UserMessage tum ) throws IOException {
        tum.setType( "fail" );
        sic.getOos().writeObject(tum);
        System.out.println("服务器发送校验结果成功！");
    }
}
