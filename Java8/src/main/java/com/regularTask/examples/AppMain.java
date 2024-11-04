package com.regularTask.examples;
import com.regularTask.examples.encapsulation.Student;
public class AppMain {

    public static void main(String[] args) {
        Student student = new Student();

        System.out.println("Hello, world");
        System.out.println("This is an example Java program");
//        System.out.println(student.getDefaultvar());
        System.out.println(student.getName("John Doe"));
        System.out.println(student.getAge(25));
        System.out.println(student.getB(416));
//        System.out.println(super.getB());
    }

}
