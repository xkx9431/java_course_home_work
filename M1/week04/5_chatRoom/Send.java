package home_work.week04.chatRoom;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 客户端的发送消息方法
 */
public class Send implements Runnable{
    //控制台输入流
    private BufferedReader console;
    //管道输出流
    private DataOutputStream dos;
    //控制线程
    private boolean flag= true;
    //聊天昵称
    private String name;

    public Send() {
        console = new BufferedReader(new InputStreamReader(System.in));
    }


    public Send(Socket client, String name){
        this();
        this.name = name;
        try {
            dos = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
        }

    }



    //从控制台接收数据
    private String getMsgFromConsole() {
        try {
           return console.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //客户端发送数据
    public void send(String msg){
        if (null != msg && !msg.equals("")){
            try {
                dos.writeUTF(name +": " + msg);
                Date now = new Date(System.currentTimeMillis());
                String time = new SimpleDateFormat("hh:mm:ss   yyyy/MM/dd  ").format(now);
                System.out.println(time);
                System.out.println(name + ":" + msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    flag = false;
                    dos.close();
                    console.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        }else{
            try {
                dos.writeUTF(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        //线程体
        send("");
        while (flag){
            send(getMsgFromConsole());
        }
    }
}