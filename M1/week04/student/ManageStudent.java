package home_work.week04.student;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ManageStudent {


    private ArrayList<Student> students = new ArrayList<Student>();
    private  HashSet<String> studentIDs = new HashSet<>();
    private String filePath = "d://a.dat";

    //静态代码块，初始化先加载上次已经录入的学生信息
    {
        System.out.println("student system init");
        System.out.println("加载之前的学生.....");
        loadStudent();
    }

    /**
     * 当前操作的学生instance
     * 只有在查询是将该变量有效
     * 跟新操作需要先执行查询
     */
    private Student currStudent;


    /**
     * 向学生系统中添加学生
     * @param newStudentID: 学号
     * @param newStudentName: 姓名
     * @param newStudentAge: 年纪
     */
    public void addStudent(String newStudentID,String newStudentName,int newStudentAge) throws  Exception {
        Student newStudent = null;
        newStudent = new Student(newStudentID,newStudentName,newStudentAge);


        if(studentIDs.contains(newStudent.getStudentID())){
            System.out.println("当前学生" + newStudent.getStudentID() + "已经存在");
        } else {
            this.students.add(newStudent);
            this.studentIDs.add(newStudent.getStudentID());
            System.out.println("当前学生" + newStudent + "已经成功添加");
        }
    }
    /**
     * 向学生系统中删除学生,依据学号操作
     * @param studentID
     *
     */
    public void removeStudent(String studentID){
        if(studentIDs.contains(studentID)){
            this.students.remove(getStudentByID(studentID));
            this.studentIDs.remove(studentID);
            System.out.println("当前学生" + studentID + "已经成功删除");
        }else {
            System.out.printf("当前学生" + studentID + "不存在");
        }
    }
    /**
     * 向学生系统中更改学生信息
     * （该步骤前需要先执行一次查询操作）
     * @param studentID 最新的学号
     * @param studentName 最新的姓名
     * @param studentAge 最新的年纪
     *
     */
    public void updateStudent(String studentID , String studentName, int studentAge ){
        if(!studentID.equals("")){
            this.studentIDs.remove(this.currStudent.getStudentID());
            try {
                this.currStudent.setStudentID(studentID);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.studentIDs.add(this.currStudent.getStudentID());
        }
        if(!studentName.equals("")){
            try {
                this.currStudent.setStudentID(studentID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(studentAge > 0){
            try {
                this.currStudent.setStudentAge(studentAge);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("跟新如下！");
        showCurrStudent();
        this.currStudent = null;

    }
    /**
     * 查找 //仅支持学号，姓名查找学生，
     * 优先学号查找
     * @param studentID
     * @param studentName
     *
     */
    public void searchStudent(String studentID , String studentName ){
        if(!studentID.equals("")){
            this.currStudent =  getStudentByID(studentID);
            if(this.currStudent==null){
                System.out.println("当前学生不存在！");

            }else{
                showCurrStudent();
            }
        } else if(!studentName.equals("")) {
            this.currStudent = getStudenByStudentName(studentName);
            showCurrStudent();
        }
    }

    /**
     * @param studentName
     * @return student
     * 依据学生姓名查找，返回找到目标学生
     */
    private Student getStudenByStudentName(String studentName) {
        for (Iterator<Student> iterator = students.iterator(); iterator.hasNext(); ) {
            Student curStu = iterator.next();
            if(curStu.getStudentID().equals(studentName) ){
                return curStu;
            }
        }
        return null;
    }

    /**
     * @param studentID
     * @return student
     *  依据学生查找，返回找到目标学生
     */
    private Student getStudentByID(String studentID) {
        for (Iterator<Student> iterator = students.iterator(); iterator.hasNext(); ) {
            Student curStu = iterator.next();
            if(curStu.getStudentID().equals(studentID)){
                return curStu;
            }
        }
        return null;
    }

    /**
     * 遍历所有学生信息
     * 基于 iterator
     */
    public void showAll() {
        for (Iterator<Student> iterator = students.iterator(); iterator.hasNext(); ) {
            Student curStu = iterator.next();
            System.out.println(curStu);
        }
    }

    public Student getCurrStudent() {
        return currStudent;
    }

    void  showCurrStudent() {
        System.out.println("当前学生为：");
        System.out.println(  getCurrStudent() );
    }


    //关闭学生操作时，讲对应的学生信息保存到“d:student.dat”

    public void saveStudents(){
        ObjectOutputStream oos = null;
        try {
            // 1.创建ObjectOutputStream类型的对象与d:student.dat文件关联
            oos = new ObjectOutputStream(new FileOutputStream("d:\\a.dat"));
            // 2.准备一个User类型的对象并初始化
            oos.writeObject(this.students);
            System.out.println("学生信息保存成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭流对象并释放有关的资源
            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadStudent() {
        ObjectInputStream ois = null;
        File file = new File(filePath);
        if(!file.exists()){
            return;
        }

        try {
            // 1.创建ObjectInputStream类型的对象与d:/a.dat文件关联
            ois = new ObjectInputStream(new FileInputStream(filePath));
            // 2.从输入流中读取一个对象并打印
            ArrayList<Student> tmpobj = ( ArrayList<Student> ) ois.readObject();
            System.out.println("读取的学生为：" + tmpobj);
            this.students = ( ArrayList<Student> ) tmpobj.clone();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 3.关闭流对象并释放有关的资源
            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
