package com.regularTask.examples.inheritance;


public class TestInheritance extends Derived {
    public static void main(String[] args) {
        Derived d = new Derived();
        Base b = new Base();
        Base c = new Derived();
        c.fun();
//        d.fun(); // This will call the overridden fun() method in Derived class
//        b.fun(); // This will call the fun() method in Base class
    }
}
