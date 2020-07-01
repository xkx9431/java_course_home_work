package home_work.week2;

import java.math.BigDecimal;

/**
 * lengthOfCall: 通话时长
 * msgTotalNum: 短信条数
 * monthFees: 每月费用
 */
public class TelPackage extends CommonPackage implements TelSvc {
    private double totalTimesOfCall = 0;
    private int msgTotalNum;
    private BigDecimal monthFees;

    /**
     * @param totalTimesOfCall  套餐的通话时长
     * @param msgTotalNum 套餐的短信条数
     * @param monthFees 套餐费用
     */
    TelPackage(double totalTimesOfCall, int msgTotalNum, BigDecimal monthFees){
        super(monthFees);
        this.totalTimesOfCall = totalTimesOfCall;
        this.msgTotalNum = msgTotalNum;
    }


    /**
     * @return 获取套餐的通话时长
     */
    public double getTotalTimesOfCall() {
        return totalTimesOfCall;
    }

    /**
     * @param totalTimesOfCall  设置当前套餐的通话时长
     */
    public void setTotalTimesOfCall(double totalTimesOfCall) {
        this.totalTimesOfCall = totalTimesOfCall;
    }

    /**
     * @return  获取当前套餐的短信条数
     */
    public int getMsgTotalNum() {
        return msgTotalNum;
    }

    /**
     * @param msgTotalNum 设置当前套餐的短信条数
     */
    public void setMsgTotalNum(int msgTotalNum) {
        this.msgTotalNum = msgTotalNum;
    }

    /**
     * @return 获取当前的套餐费用
     */
    public BigDecimal getMonthFees() {
        return monthFees;
    }

    /**
     * @param monthFees 设置当前的套餐基本费用
     */
    public void setMonthFees(BigDecimal monthFees) {
        this.monthFees = monthFees;
    }

    /**
     * 打印当前手机套餐的详细信息
     */
    @Override
    public void showDetail() {
        System.out.println("the fees for per month is" + this.getMonthFees() );
        System.out.println("the total time of the call for per month is" + this.getTotalTimesOfCall() );
        System.out.println("the total num of msg  for per month is" + this.getMsgTotalNum() );
    }

    /**
     * @param talkTimes 通话时长
     * @param simCardObj 通话所用的手机卡类
     *  根据本次通话时长来更新已通话时长
     */
    @Override
    public void telTimeWithCardSize(double talkTimes, SimCard simCardObj) {
        System.out.println("当前使用手机卡详情：");
        simCardObj.show();

        simCardObj.setTotalTimesOfCall(simCardObj.getTotalTimesOfCall() - talkTimes );
        System.out.println("本次通话 " + talkTimes + " 总通话时间为： " + simCardObj.getTotalTimesOfCall());
    }
}
