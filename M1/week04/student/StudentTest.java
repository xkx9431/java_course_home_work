package home_work.week04.student;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class StudentTest {
    public static void main(String[] args) {
        Student student1 = null;
        Student student2 = null;
        Student student3 = null;
        try {
            student1 = new Student("2011-08-033","xkx",18);
//            student2 = new Student("2011-08","xkx",18);
            student3 = new Student("2011-08-033","xkx",2018);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        ObjectOutputStream oos = null;
//        try {
//            // 1.创建ObjectOutputStream类型的对象与d:student.dat文件关联
//            oos = new ObjectOutputStream(new FileOutputStream("d:\\student.dat"));
//            // 2.准备一个User类型的对象并初始化
//            oos.writeObject(student1);
//            System.out.println("学生信息保存成功！");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 4.关闭流对象并释放有关的资源
//            if (null != oos) {
//                try {
//                    oos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

}

