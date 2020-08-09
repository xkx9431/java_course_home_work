package home_work.week04.userMsg;

import java.io.*;
import java.net.Socket;

/**
 * 服务器对socket进行处理的线程
 */
public class ServerHandleThread implements  Runnable {
    Socket socket = null;
    public ServerHandleThread(Socket socket){
        super();
        this.socket = socket;
    }

//     todo check login status logic
    public void  checkLoginStatus( UserMessage userMessage){
        User user = userMessage.getUser();
        if("admin".equals(user.getUserName()) &&"123450".equals(user.getPassword())){
            userMessage.setLoginStatus("success");
        } else {
            userMessage.setLoginStatus("fail");
        }

    }

    @Override
    public void run() {
        //
        OutputStream os = null;
        InputStream is = null;
        ObjectOutputStream oos = null;
        try {
            // 1. 获取套接字的输入流，得到客户端的输入
            is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            UserMessage userMessage = (UserMessage)ois.readObject();
            socket.shutdownInput();// 禁用套接字的输入流

            // 2.当前的输入信息类型为"check"，检查客户端的登录状态
            if("check".equals(userMessage.getMessageType())){
                checkLoginStatus(userMessage);

                // 3. server 输出判断结果到客户端
                os=socket.getOutputStream();
                oos = new ObjectOutputStream(os);
                oos.writeObject(userMessage);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
                try {
                    if(null!=oos){
                        oos.close();
                    }
                    if(null!=os){
                        os.close();
                    }
                    if(socket!=null){
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }
}
