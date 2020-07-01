package home_work.week04.student;


import java.io.Serializable;

public class Student implements StudentCompareInt , java.io.Serializable {
    private static final long serialVersionUID = -5814716593800822421L;
    /**
     * studentID： 学生学号
     * studentName： 学生姓名
     * studentAge： 学生年纪
     */
    private  String studentID;
    private  String studentName;
    private  int studentAge;


    // 构造方法

    public Student() {
    }

    public Student(String studentID, String studentName, int studentAge)  throws  Exception {
            setStudentID(studentID);
            setStudentAge(studentAge);
            this.studentName = studentName;
    }

    //getter setter begin
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) throws Exception {
//        matches("^20[0-9]{2}-[0-9]{2}-[0-9]{3}$")
        if(studentID.matches("^20[0-9]{2}-[0-9]{2}-[0-9]{3}$") ){
            this.studentID = studentID;
        } else {
            throw new IdException("the id pattern is not correct,should be '20xx-xx-xxx' ");
        }

    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge)  throws Exception {
        if (studentAge > 6 && studentAge < 150) {
            this.studentAge = studentAge;
        } else {
            throw new AgeException("入学年龄不合理！！！");
        }
    }
    //getter setter end


    /**
     * @rewrite toString() method
     */
    @Override
    public String toString(){
        return "\nStudent: {学号=" + this.studentID+ "，姓名= " + this.studentName + "，年龄=" + this.studentAge +"}";
    }



    @Override
    public int compareTo(Student s) {
        int num = this.getStudentID().compareTo(s.getStudentID());
        if (num ==0 )
            return	this.getStudentName().compareTo(s.getStudentName());
        return num;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Student))
            throw new RuntimeException("no Student");

        Student s = (Student)o;
        int num =this.getStudentID().compareTo(s.getStudentID());
        if (num ==0 )
            return	this.getStudentName().compareTo(s.getStudentName());
        return num;
    }
}
