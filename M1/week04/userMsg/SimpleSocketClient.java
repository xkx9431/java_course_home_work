package home_work.week04.userMsg;

import java.io.*;
import java.net.Socket;

public class SimpleSocketClient {

    public static void processReceive(UserMessage userMessage) {
       if("success".equals( userMessage.getLoginStatus() )){
           System.out.println("密码正确，当前用户登录成功！");
       } else if("fail".equals( userMessage.getLoginStatus() )){
            System.out.println("密码错误，请从新登录");
       } else {
           System.out.println("！！！ 未知情况");
       }

    }


    public static void main(String[] args) {
        try {
            // 1 建立客户端连接，指定服务器的ip和端口
            Socket socket = new Socket("127.0.0.1",9999);

            //2. 获取socket 的输出流，并向server 发送消息
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            //2.1 create a msg type
            User user = new User("admin","12345");
            UserMessage usermsg = new UserMessage(user,"check");
            System.out.println("发送前消息:\n" + usermsg);

            //2.2 send to sever
            oos.writeObject(usermsg);
            socket.shutdownOutput();

            // 3.获取输入流，读取服务器信息
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            UserMessage userMessage1 = (UserMessage) ois.readObject();

            // 4.处理返回信息
            processReceive(userMessage1);

            System.out.println("server 返回了:\n" + userMessage1);
            oos.close();
            os.close();
            is.close();
            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // todo
        }

    }
}
