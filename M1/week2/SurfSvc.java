package home_work.week2;

public interface SurfSvc {
    /**
     * @param surfFlow  使用流量
     * @param simCardObj 手机卡类型
     *   上网服务接口
     */
    abstract void surfSvc(double surfFlow,SimCard simCardObj);
}
