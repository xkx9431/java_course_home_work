package home_work.week04.maniFileViaTP;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 对文件进行操作
 */
public class ManiFileByMultiThread {


    // 最大线程数
    public static final Integer MAX_THREAD_NUM = 5;
    // 固定线程池
    static ExecutorService pool = Executors.newFixedThreadPool(MAX_THREAD_NUM);


    // 创建测试文件
    public static void createFiles(String base, int num) throws IOException {
        try {
            File dir = new File(base);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            for(int i=1; i<=num; i++){
                File file = new File(base, i + ".dat");
                if (!file.exists()) {
                    file.createNewFile();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // main 多线程执行，文件复制
    public static void main(String[] args) {

        //调用线程池执行方法
        CopyFiles copyFiles = new CopyFiles("","", ".dat");
        pool.execute(copyFiles);

        // shutdown
        pool.shutdown();


        /*       直接多线程调用，*/
//        new Thread(copyFiles, "线程1").start();
//        new Thread(copyFiles, "线程2").start();
//        new Thread(copyFiles, "线程3").start();
    }

}

