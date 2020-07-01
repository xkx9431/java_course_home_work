package home_work.week2;

public interface TelSvc {
    /**
     * @param talkTimes  通话时长
     * @param simCardObj 手机卡类型
     */
    abstract void telTimeWithCardSize(double talkTimes,SimCard simCardObj);
}
