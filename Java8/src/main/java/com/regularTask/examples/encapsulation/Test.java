package com.regularTask.examples.encapsulation;

public class Test {
    public static void main(String[] args) {
        Student student = new Student();
        System.out.println("Name: " + student.getName("John Doe"));
        System.out.println("Age: " + student.getAge(18));
        
    }
}
