package com.lagou.model;

public class StudentWithDetail extends Student  implements java.io.Serializable{
    private static final long serialVersionUID = -2063828850243100983L;
    /**
     * studentID： 学生学号
     * studentName： 学生姓名
     * studentAge： 学生年纪
     */
    private  String studentID;
    private  String studentName;
    private  int studentAge;
    private  String studentPsd;
    private  int[] studentCJ;

    public StudentWithDetail(String studentID, String studentName, int studentAge, String studentPsd,int[] studentCJ) throws Exception {
        super(studentID,studentName,studentAge);
        this.studentPsd = studentPsd;
        this.studentCJ = studentCJ;
    }
}
