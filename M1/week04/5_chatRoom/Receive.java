package home_work.week04.chatRoom;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 客户端的接受线程
 */
public class Receive implements Runnable {

    //输入流
    private DataInputStream dis;
    //线程标识，判断线程运行状态
    private boolean flag = true;

    public Receive(Socket client) {
        try {
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
            try {
                dis.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    //接收数据
    public String receive(){
        String msg = "";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
            try {
                dis.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return msg;
    }


    @Override
    public void run() {
        while(flag){
            System.out.println(receive());
        }
    }
}
