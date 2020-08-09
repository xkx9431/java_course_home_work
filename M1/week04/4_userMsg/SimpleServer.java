package home_work.week04.userMsg;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * tcp SERVER
 * FOR RESPONSE THE CLIENT REQUEST
 */
public class SimpleServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("服务器启动，等待客户端的连接。。。");
            int count = 0;// 记录客户端的数量
            Socket socket  = null;
            while(true){
                // 1. 获取客户端的连接
                socket = serverSocket.accept();
                ++count;
                System.out.println("欢迎登陆>>>");
                // 2.提示当前客户端的信息
                InetAddress inetAddress = socket.getInetAddress();
                System.out.println("当前客户端的IP地址是："+inetAddress.getHostAddress());
                //
                Thread serverHandleThread = new Thread(new ServerHandleThread(socket));
                serverHandleThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
