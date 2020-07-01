package home_work.week03;

import java.util.Scanner;

/**
 *  用于处理两个字符串的逻辑
 *  longStr： 长度较长的字符串
 *  shortStr： 长度较短的字符串
 */
public class TwoStr {
    private String longStr;
    private  String shortStr;


    /**
     * @param str1 用户给定字符串1
     * @param str2 用户给定字符串1
     *     简单判断字符串长短，并赋值
     */
    public TwoStr(String str1, String str2) {
        if(str1.length() >= str2.length()){
            this.longStr = str1;
            this.shortStr = str2;
        }else{
            this.longStr = str2;
            this.shortStr = str1;
        }


    }

    /**
     * @return 返回当前两个字符串中的最大字串（子串连续）
     *
     */
    String getLCS(){
        int len = this.shortStr.length();
        // 暴力法，依次在 longStr 中查找short 的子串，子串长度为 n(shortStr.length),n-1,n-2
        for(int i = len;i>0;--i){
            for(int j=0; j<=len-i; j++){
                if(this.longStr.indexOf(this.shortStr.substring(j,i+j))!= -1){
                    return this.shortStr.substring(j,i+j);
                }
            }
        }
        //没有找到则返回“”
        return new StringBuilder().toString();
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请依次输入两个字符串");
        String str1 = scanner.next();
        String str2 = scanner.next();

        //调用getLCS
        String res = new TwoStr( str1, str2).getLCS();
        //输出结果
        if(res.length() ==0 ){
            System.out.println( str1 + " and " + str2 + " has no common sub strings!" );
        }else{
            System.out.println( str1 + " and " + str2 + " the largest common sub string result is '" + res + "'" );
        }
    }
}
