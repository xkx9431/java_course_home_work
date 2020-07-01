package home_work.week03;

/**
 * 给定字符串，用来统计，各类型的字符串个数
 * str： 构造参数 -> 给定的字符串
 */
public class CountChar {
    private String str;
    /**
     * countNum： 数字字符的个数
     * countUpChar: 大写字符的个数
     * countLowChar：小写字符的个数
     * otherChar：其它字符的个数
     */
    private int countNum = 0;
    private int countUpChar = 0;
    private  int countLowChar = 0;
    private  int otherChar = 0;


    /**
     * @param str 给定参数，字符串
     */
    public CountChar(String str) {
        this.str = str;
    }

    /**
     * 遍历字符串，统计各类型字符个数
     */
    private void processCount(){
        StringBuilder rawStrings = new StringBuilder( this.str );
        for(int i = 0; i< rawStrings.length();++i){

            if(Character.isUpperCase(rawStrings.charAt(i))){
                countUpChar++;
            }  else if(Character.isLowerCase(rawStrings.charAt(i))){
                countLowChar++;
            } else  if(Character.isDigit(rawStrings.charAt(i))){
                countNum++;
            } else {
                otherChar++;
            }
        }

    }
    //根据处理结果，打印结果
    private void showRes() {
        System.out.println("str:" + this.str + " contains Uppercase char: " + this.countUpChar );
        System.out.println("str:" + this.str + " contains Lowercase char: " + this.countLowChar );
        System.out.println("str:" + this.str + " contains Digit char: " + this.countNum );
        System.out.println("str:" + this.str + " contains other case  char: " + this.otherChar );
    }


    /**
     * @param args
     * 测试用例
     */
    public static void main(String[] args){
        CountChar str  = new CountChar("ABCD123!@#$%ab");
        str.processCount();
        str.showRes();
    }

}
