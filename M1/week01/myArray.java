package home_work.wee01;
import java.util.Arrays;

public class myArray {
    public int[] data;
    private  int n;
    private  int count;
    private  double factor;

    // 构造方法
    public myArray( int capacity){
        this.data = new int[capacity];
        this.n = capacity;
        this.count = 0;
        this.factor = 0.8;
    }

    //根据索引，找到数据中的元素并返回
    public int find(int index){
        if (index<0 || index>=count) return -1;
        return data[index];
    }

    public void insert (int index, int value){

        if(count >= n* factor){
            n *= 1.5;
            int[] old = data;
            data = new int[n];
            System.arraycopy(old,0,data,0,count);
        }

        // 位置合法
        for( int i = count; i > index; --i){
            data[i] = data[i - 1];
        }
        data[index] = value;
        ++count;
    }


}
