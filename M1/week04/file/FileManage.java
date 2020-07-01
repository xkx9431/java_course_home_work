package home_work.week04.file;

import java.io.File;

/**
 * 文件管理类，
 * show（） 提供显示目录信息（包含子文件）
 *  delete() 删除子文件以及当前目录
 *
 */
public class FileManage {

    /**
     * @param file 给定当前文件信息
     *  打印所有文件以及子目录文件信息
     */
    public static void show(File file) {
        //获取当前文件下信息，是文件打印，文件夹继续递归打印
        File[] files = file.listFiles();
        for(File currF : files){
            String name =currF.getName();
            if(currF.isFile()){
                System.out.println("Filename:" + name);
            } else if( currF.isDirectory()){
                System.out.println("--" + name );
                show(currF);
            }
        }

    }

    /**
     * @param file 删除当前文件内容，
     *             存在子目录则继续递归调用删除子目录后再删除该文件
     */
    public  static  void  delete(File file){
        File[] files = file.listFiles();
        for(File curF: files){
            String name = curF.getName();
            // 先删除文件子项
            if(curF.isFile()){
                System.out.println(curF.delete()? "删除文件" + name + "成功": "文件删除失败");
                // 递归删除文件夹子项
            } else if(curF.isDirectory()){
                delete(curF);
                System.out.println("删除：" + name );
            }
        }
        //
        file.delete();
        System.out.println( "删除成功");
    }
}
