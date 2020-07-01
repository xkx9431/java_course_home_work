package com.lagou.model;

public class StudentMsg  implements java.io.Serializable {
    private static final long serialVersionUID = -4524863079322303434L;
    private String type;
    private Student student;


    public StudentMsg(String type, Student student) {
        this.type = type;
        this.student = student;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentMsg{" +
                "type='" + type + '\'' +
                ", student=" + student +
                '}';
    }
}
