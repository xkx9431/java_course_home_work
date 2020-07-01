package home_work.week03;


public class Student implements StudentCompareInt {
    /**
     * studentID： 学生学号
     * studentName： 学生姓名
     * studentAge： 学生年纪
     */
    private  String studentID;
    private  String studentName;
    private  int studentAge;


    // 构造方法
    public Student(String studentID, String studentName, int studentAge) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentAge = studentAge;
    }

    //getter setter begin
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
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

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }
    //getter setter end


    /**
     * @rewrite toString() method
     */
    @Override
    public String toString() {
        return "\nStudent: {学号=" + this.getStudentID() + "，姓名= " + this.getStudentName() + "，年龄=" + this.getStudentAge()+"}";
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
