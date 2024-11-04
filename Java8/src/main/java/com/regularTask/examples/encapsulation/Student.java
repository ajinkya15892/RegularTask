package com.regularTask.examples.encapsulation;
/*
 * Encapsulation is a key principle in object-oriented programming (OOP) that allows us to hide the internal details of a class and provide a public interface for accessing and modifying these details.
 * In encapsulation, the data and the methods that operate on it are bundled together in a single unit, known as a class.
 * Encapsulation provides the following benefits:
 * 1. Encapsulation prevents data from being accessed or modified directly, which can help maintain data integrity and security.
 * 2. Encapsulation allows us to define the behavior of a class without revealing its internal implementation details.
 * 3. Encapsulation promotes code reusability and maintainability, as it allows us to create objects with specific attributes and behaviors without worrying about their internal implementation details.
 * 4. Encapsulation - allows us to define the behavior of a class without worrying about its internal implementation details and behavior without worrying about its behavior details and behavior
 * Access modifier are private,default,protected & public.
 * Access
 * --------------------------------
 *            within the class  within package org  outside package within subclass    outside package
 * private         Y                   N                   N                               N
 * default         Y                   Y                   N                               N
 * protected       Y                   Y                   Y                               N
 * public          Y                   Y                   Y                               Y
 */
public class Student {
    private String name;
    private int age;
    int defaultvar=0;
    protected int b=248;
    int getDefaultvar(){
        return defaultvar;
    }
    //Try to access getDefaultvar method in AppMain class , It will not work.
    public String getName(String name) {
        return name;
    }
    public int getAge(int age) {
        return age;
    }
    public int getB(int b) {
        return b;
    }
}
