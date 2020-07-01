package home_work.week03;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

class StudentTest {
    public static void main(String[] args) {
        Student student1 = new Student("1","xkx",18);
        System.out.println(student1.toString());
        student1.setStudentID("1");
        System.out.println(student1.toString());
        student1.setStudentAge(25);
        System.out.println(student1.toString());
        student1.setStudentName("Kevin");
        System.out.println(student1.toString());

        Student student2 = new Student("2","xkx1",19);
        Student student3 = new Student("3","xkx2",20);

        ArrayList<Student> students = new ArrayList<>();
        students.add(student3);
        students.add(student2);
        students.add(student1);

        System.out.println(students);

        Collections.sort( students);

        System.out.println( students );





    }

}