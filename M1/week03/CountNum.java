package home_work.week03;

import java.util.HashMap;

/**
 * 统计统计给定字符串中的每个数字字符串的次数
 * str：给定字符串
 *
 */
public class CountNum {
    private String str;
    HashMap<String,Integer> numMap = new HashMap<> ();

    public CountNum(String str) {
        this.str = str;
    }

    /**
     * 用于统计给定字符串中的每个数字字符串的次数
     * 结果保存在HashMap
     */
    void countNum(){
       String [] nums = this.str.split(",");
       // 存在 getvalue +1, else set 1.
       for(String num:nums){
           numMap.put(num, numMap.getOrDefault(num,0) +1);
       }
    }

    /**
     * 打印输出结果，遍历 hashMap
     */
    void showCounter() {
        for(String key : numMap.keySet() ){
            System.out.println( key + "出现了：" + numMap.get(key));
        }
    }

    //测试方法
    public static void main(String[] args) {
        CountNum counterNum = new CountNum("123,456,789,123,456");
        counterNum.countNum();
        System.out.println( "字符串'123,456,789,123,456'中每个数字字符串出现的次数结果为");
        counterNum.showCounter();
    }

}
