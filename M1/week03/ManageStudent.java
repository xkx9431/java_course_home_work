package home_work.week03;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ManageStudent {
    private ArrayList<Student> students = new ArrayList<Student>();
    private  HashSet<String> studentIDs = new HashSet<>();

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
    public void addStudent(String newStudentID,String newStudentName,int newStudentAge){
        Student newStudent = new Student(newStudentID,newStudentName,newStudentAge);

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
            this.currStudent.setStudentID(studentID);
            this.studentIDs.add(this.currStudent.getStudentID());
        }
        if(!studentName.equals("")){
            this.currStudent.setStudentID(studentID);
        }
        if(studentAge > 0){
            this.currStudent.setStudentAge(studentAge);
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

}
