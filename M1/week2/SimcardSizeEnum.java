package home_work.week2;

/**
 * 电话卡的枚举类，三种不同类型
 */
enum SimCardSizeEnum{

    LARGE("large"), NORMAL("normal"), SMALL("small");

    private final String size;

    SimCardSizeEnum(String size){
        this.size = size;
    }


    public String getSize() {
        return size;
    }
}

