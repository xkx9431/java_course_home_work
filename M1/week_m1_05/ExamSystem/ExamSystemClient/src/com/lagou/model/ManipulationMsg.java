package com.lagou.model;

public class ManipulationMsg implements java.io.Serializable{

    private static final long serialVersionUID = -7406260876790534871L;

    private String type; // 消息的类型代表具体的业务
    private String msgContent;   // 消息的具体内容


    public ManipulationMsg(String type, String msgContent) {
        this.type = type;
        this.msgContent = msgContent;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    @Override
    public String toString() {
        return "ManipulationMsg{" +
                "type='" + type + '\'' +
                ", msgContent='" + msgContent + '\'' +
                '}';
    }
}
