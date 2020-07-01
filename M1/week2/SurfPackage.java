package home_work.week2;

import java.math.BigDecimal;

/**
 * monthFees: 费用
 * flowType: 流量类型
 * internetNum： 流量包大小
 */
public class SurfPackage extends  CommonPackage implements SurfSvc{
    private BigDecimal monthFees;
    private String surfType;
    private double surfFlows;

    protected SurfPackage(BigDecimal monthFees, String surfType, double surfFlows) {
        super(monthFees);
        this.surfType = surfType;
        this.surfFlows = surfFlows;
    }


    /**
     * @return 流量类型 getter
     */
    public String getSurfType() {
        return surfType;
    }

    /**
     * @param surfType 流量类型 setter
     */
    public void setSurfType(String surfType) {
        this.surfType = surfType;
    }

    /**
     * @return 流量 getter
     */
    public double getSurfFlows() {
        return surfFlows;
    }

    /**
     * @param surfFlows 流量 setter
     */
    public void setSurfFlows(double surfFlows) {
        this.surfFlows = surfFlows;
    }


    /**
     * @return 费用 getter
     */
    public BigDecimal getMonthFees() {
        return monthFees;
    }

    /**
     * @param monthFees 费用 setter
     */
    public void setMonthFees(BigDecimal monthFees) {
        this.monthFees = monthFees;
    }

    /**
     * 显示流量套餐详情
     */
    @Override
    public void showDetail() {
        System.out.println("the total fees for this month is " + this.getMonthFees() );
        System.out.println("the total surf flows for this month is " + this.getSurfFlows() );
        System.out.println("the surf types for this month is " + this.getSurfType() );

    }

    /**
     * @param surfFlow   使用流量
     * @param simCardObj 手机卡类型
     *   上网服务接口
     */
    @Override
    public void surfSvc(double surfFlow, SimCard simCardObj) {
        System.out.println("当前使用手机卡详情：");
        simCardObj.show();

        simCardObj.setTotalTimesOfCall(simCardObj.getSurfFlows() - surfFlow );
        System.out.println("本次使用流量 " + surfFlow + " 总已使用流量为： " + simCardObj.getSurfFlows());
    }
}
