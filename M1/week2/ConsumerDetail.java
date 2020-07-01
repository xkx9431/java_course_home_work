package home_work.week2;

import java.math.BigDecimal;


public class ConsumerDetail {

    /**
     * @totalTalkTimes: 统计通话时长
     * @totalSurfFlows: 统计上网流量
     * @totalMonthFees: 每月消费金额
     */
    private double totalTalkTimes;
    private double totalSurfFlows;
    private BigDecimal totalMonthFees;
    {
        totalTalkTimes = 0;
        totalSurfFlows = 0;
        totalMonthFees = new BigDecimal("0");
    }


    /**
     * 当前用户使用的手机卡类型
     */
    private SimCard curSimCard;


    /**
     * 当前用户使用的手机套餐
     */
    private TelPackage curTelPackage;

    /**
     * 当前用户使用的手流量套餐
     */
    private SurfPackage curSurfPackage;


    /**
     * @param curSimCard  当前用户使用的手机卡类型
     * @param curTelPackage 当前用户使用的手机套餐
     * @param curSurfPackage 当前用户使用的手流量套餐
     */
    public ConsumerDetail(SimCard curSimCard, TelPackage curTelPackage, SurfPackage curSurfPackage){
        this.curSimCard = curSimCard;
        this.curTelPackage = curTelPackage;
        this.curSurfPackage = curSurfPackage;
    }


    /**
     * @return 当前用户累计通话时长
     */
    public double getTotalTalkTimes() {
        return curSimCard.getTotalTimesOfCall();
    }

    /**
     * @return 当前用户累计使用流量
     */
    public double getTotalSurfFlows() {
        return this.curSimCard.getSurfFlows();
    }


    /**
     * @return 当前用户累计套餐费用
     */
    public BigDecimal getTotalMonthFees() {
        return  this.totalMonthFees;
    }

    /**
     * 设置当前总套餐费用： 流量 + 通话
     */
    public void setTotalMonthFees() {
        this.totalMonthFees = curSurfPackage.getMonthFees().add( curTelPackage.getMonthFees() );
    }
}
