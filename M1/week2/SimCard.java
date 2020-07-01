package home_work.week2;

import java.math.BigDecimal;

public class SimCard {
    /**
     * size: 手机卡类型
     * cardNo: 手机卡号
     * password: 密码
     * balance：余额
     * totalTimesOfCall： 通话时长
     * surfFlows: 已使用流量
     */
    private SimCardSizeEnum size;
    private String cardNo;
    private String userName;
    private String password;
    private BigDecimal balance;
    private double totalTimesOfCall;
    private double surfFlows;

    public SimCard(SimCardSizeEnum size, String cardNo, String userName, String password, BigDecimal balance, double totalTimesOfCall) {
        this.size = size;
        this.cardNo = cardNo;
        this.userName = userName;
        this.password = password;
        this.balance = balance;
        this.totalTimesOfCall = totalTimesOfCall;
    }
    public void show(){
        System.out.println("卡号： " + cardNo + " 用户： "+ userName  + "当前余额"  + balance);
    }

    public double getTotalTimesOfCall() {
        return totalTimesOfCall;
    }

    public void setTotalTimesOfCall(double totalTimesOfCall) {
        this.totalTimesOfCall = totalTimesOfCall;
    }

    public double getSurfFlows() {
        return surfFlows;
    }

    public void setSurfFlows(double surfFlows) {
        this.surfFlows = surfFlows;
    }
}
