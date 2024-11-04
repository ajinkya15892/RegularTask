package com.regularTask.examples.inheritance;

public class Base {
    int x,a,b;
    void fun(){
        System.out.println("Base class");
    }
    Base(){
        a=10;
        b=20;

    }
    public void print(){
        System.out.println("a="+a+"b="+b);
    }
}
