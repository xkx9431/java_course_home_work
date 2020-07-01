package home_work.week2;

import java.math.BigDecimal;

/**
 * 通用套餐抽象类
 */
public abstract class CommonPackage {
    private BigDecimal monthFees;

    protected CommonPackage(BigDecimal monthFees) {
        this.monthFees = monthFees;
    }

    public abstract void showDetail();
}
