package home_work.week03;


/**
 * 单张扑克牌代表
 * value： 字面量
 * primValue： 实际大小刻度， A.primValue > K.primValue
 * symbol: 牌的花色
 */
public class Poker implements Comparable {

    private String value;
    private int primValue;
    private PokerSymbol symbol;

    /**
     * @param value  表示牌的大小， 大小王 牌值 52，53， A：11，2:12,3-10: 3-10
     * @param symbol 表示牌的花色
     */
    public Poker(int value,PokerSymbol symbol){
        this.primValue = value;
        this.symbol = symbol;
        if(value<=7){
            this.value = value+3 + "";
        }else if( value == 8 ){
            this.value = "J";
        }else if(value == 9){
            this.value = "Q";
        }else if(value == 10){
            this.value = "K";
        } else if(value == 11){
            this.value = "A";
        } else if(value == 12){
            this.value = "2";
        }
    }

    /**
     * @return 扑克牌的打印值，与实际一致，大小+ 花色
     */
    @Override
    public String toString() {
        if(value==null){
            return symbol.toString();
        }
        return  value + symbol.toString();
    }

    /**
     * @return 用于比较两张大小牌的时候用如果primValue
     */
    public int getValue(){
        return primValue;
    }

    /**
     * @param obj 被比较poker insatnce,降序排序
     * @return int
     */
    @Override
    public int compareTo(Object obj) {
        Poker poker1 =(Poker)obj;
        if(this.primValue == poker1.getValue()){
            return 0;			//如果primValue 一致则相等
        }else if(this.primValue > poker1.getValue()) {
            return -1;			//如果这个primValue大于后者 返回正数
        }else{
            return 1;			// 如果这个primValue小于后者 返回负数
        }
    }


}
