package home_work.week04.maniFileViaTP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

class CopyFiles implements Runnable {

    private String oriFileDirPath;
    private String desFileDirPath;
    private String prefix;

    /**
     * filePathsList： 所有需要操作文件
     * index： 当前操作文件数组中的索引
     */
    List<File> filePathsList = new ArrayList<File>();
    int index = 0;

    /**
     * @param oriFilePath 读取文件夹的源路径参数
     * @param desFilePath 读取文件夹的目标路径参数
     * @param prefix 文件夹下需要操作的文件类型
     */
    public CopyFiles( String oriFilePath, String desFilePath,  String prefix) {

        this.setOriFileDirPath(oriFilePath);
        File f = new File(this.oriFileDirPath);

        this.setDesFileDirPath(desFilePath);


        //默认操作文件为 “.dat文件”
        if("".equals(prefix)){
            prefix = ".dat";
        }
        this.prefix = prefix;
        //获取目录下所有文件
        getFileList(f,  this.prefix);
    }

    /** 复制文件操作
     * @param fromFile 原文件
     * @param toFile 目标文件
     */
    private void copyFile(File fromFile,File toFile){
        try {
            FileInputStream in = new FileInputStream(fromFile);
            FileOutputStream os = new FileOutputStream(toFile);
            byte[] b=new byte[1024];
            int n=0;
            while((n=in.read(b))!=-1){
                os.write(b, 0, n);
            }
            in.close();
            os.close();
        } catch (Exception e) {
            //
            e.printStackTrace();
        }
    }

    private void getFileList(File f, String prefix) {
        File[] filePaths = f.listFiles();
        for (File file : filePaths) {
            if (file.isDirectory()) {
                getFileList(file, prefix);
            }else {
                if (-1 !=file.getName().lastIndexOf(prefix)) {
                    filePathsList.add(file);
                }
            }
        }

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        File file=null;
        while(index<filePathsList.size()){
            synchronized (this) {
                if (index>=filePathsList.size()) {
                    continue;
                }
                file=filePathsList.get(index);
                index++;
            }
            try {
                String osPath=this.desFileDirPath + File.separator + file.getName();
                Thread.sleep(30);
//                FileInputStream is = new FileInputStream(file.getPath());
                System.out.println("当前使用的线程是："+Thread.currentThread().getName()+",正在读文件："+filePathsList.indexOf(file)
                        +"文件名为："+file.getName());
                File file1 = new File(osPath);
                //复制文件
                copyFile(file, file1);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }


    //  oriFileDirPath getter and setter
    //  源文件目录
    public String getOriFileDirPath() {
        return oriFileDirPath;
    }

    public void setOriFileDirPath(String oriFileDirPath) {
        oriFileDirPath = "".equals(oriFileDirPath) ? "D:\\javaFileTest" :oriFileDirPath;
        this.oriFileDirPath = oriFileDirPath;
    }

    //  desFileDirPath getter and setter
    //  目标文件目录
    public String getDesFileDirPath() {
        return desFileDirPath;
    }

    public void setDesFileDirPath(String desFileDirPath) {
        desFileDirPath = "".equals(desFileDirPath) ? "D:\\javaFileTestDest" :desFileDirPath;
        this.desFileDirPath = desFileDirPath;
    }
}
