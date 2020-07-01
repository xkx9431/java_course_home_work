package home_work.week04.userMsg;

public class UserMessage  implements  java.io.Serializable{
    private User user;
    private String  messageType;
    private String loginStatus = "";

    public UserMessage(){

    }

    public UserMessage(User user, String messageType) {
        this.user = user;
        this.messageType = messageType;
    }




    @Override
    public String toString() {
        return "UserMessage{" +
                "user=" + user +
                ", messageType='" + messageType + '\'' +
                ", loginStatus='" + loginStatus + '\'' +
                '}';
    }





    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }
    public String  getLoginStatus() {
        return this.loginStatus;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return this.user;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    public String getMessageType() {
        return this.messageType;
    }
}
