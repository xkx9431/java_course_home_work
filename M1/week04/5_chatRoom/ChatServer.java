package home_work.week04.chatRoom;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class ChatServer {
    /**
     * 当前所有连接客户端
     */
    private List<Channel> all = new ArrayList<Channel>();

    public static void main(String[] args) throws IOException {
        new ChatServer().start();
    }

    /**
     * @throws IOException
     * 启动server， 并且监听连接，为每个连接的客户端启动线程
     */
    public void start() throws IOException {

        ServerSocket server = new ServerSocket(7777);
        System.out.println("聊天室初始化成功，等待加入....");
        while(true) {
            Socket client = server.accept();
            Channel channel = new Channel(client);

            all.add(channel);
            new Thread(channel).start();    //消息通道
        }
    }

    /**
     * 一个客户一个通道（线程）
     * 建立服务器与客户端之间的数据通道
     *
     */
    private  class Channel implements Runnable {

        /**
         * DataInputStream dis： 数据输入流
         * DataOutputStream dos： 数据输出流
         * flag： 当前客户端连接状态
         */
        private DataInputStream dis;
        private DataOutputStream dos;
        private boolean flag = true;
        private String name;

        public Channel(Socket client) {
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                flag = false;

                try {
                    dis.close();
                    dos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        /**
         * @param reply
         * 对消息源客户端的回复处理
         */
        public  void  sendBack(String reply){
            try {
                dos.writeUTF(reply);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //接收客户端的信息
        private String receive() {
            String msg = "";
            try {
                msg = dis.readUTF();

                // todo : 接受文件的flag
                if(msg.matches("please prepare receive file: .*")){
                    processReceiveFile(msg);
                    sendBack("OK, Please send!");
                }

            } catch (IOException e) {
                flag = false;
                e.printStackTrace();
                all.remove(this);
                try {
                    dis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return msg;
        }

        public void processReceiveFile(String msg ) throws IOException {

            sendOthers( msg );
            File receiveFile = new File("server-" + msg.substring(29));
            if(!receiveFile.exists()){
                receiveFile.createNewFile();
            }
        }

        //向客户端发送信息
        private void send(String msg) {
            if (null == msg || msg.equals("")) {
                return;
            }
            try {
                dos.writeUTF(time());
                dos.writeUTF(msg);
            } catch (IOException e) {
                flag = false;
                e.printStackTrace();
                all.remove(this);  //移除自身
                try {
                    dos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        /**
         * 转发消息给其他 客户
         * @param msg
         */
        private void sendOthers(String msg) {

            for ( Channel other : all) {
                if (other == this) {
                    continue;
                }
                other.send(msg);
            }

        }


        /**
         * @return  生成当前时间信息
         */
        private String time () {
            Date now = new Date(System.currentTimeMillis());
            String time = new SimpleDateFormat("yyyy.MM.dd  hh:mm:ss").format(now);
            return time;
        }

        @Override
        public void run () {
            send("欢迎加入群聊");
            name = receive();
            sendOthers(name + "加入了群聊");
            while (flag) {
                sendOthers(receive());
            }
        }
    }
}

