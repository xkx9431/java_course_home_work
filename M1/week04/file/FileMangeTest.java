package home_work.week04.file;

import java.io.File;
import java.io.IOException;

/**
 * 文件系统管理测试类
 * 测试打印文件，
 * 测试管理文件
 */
public class FileMangeTest {
    public static void main(String[] args) throws IOException {
        // 创建文件目录
        File fDirs = new File("d:/捣乱/猜猜我是谁/你猜我猜不猜/死鬼");
        if (fDirs.exists()) {
            System.out.println("：\n--" + fDirs.getName());
        } else {
            //System.out.println(f2.mkdir()? "目录创建成功": "目录创建失败");   // 创建单层目录
            System.out.println(fDirs.mkdirs()? "目录创建成功": "目录创建失败");   // 创建多层目录
        }
        //  创建文件
        File cur = new File("d:/捣乱/猜猜我是谁/你猜我猜不猜/死鬼/a.txt");
        cur.createNewFile();

        // 给定需要删除文件目录
        File parent = new File("d:/捣乱");
        // 先打印文件
        FileManage.show(parent);

        //删除文件
        FileManage.delete(parent);
    }


}
