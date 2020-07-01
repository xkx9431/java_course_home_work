package home_work.week04.chatRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        System.out.println("请输入昵称");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();

        Socket client = new Socket("localhost",7777);
        //客户端发送
        new Thread(new Send(client,name)).start();
        //客户端接收
        new Thread(new Receive(client)).start();
    }

}