package com.poortoys.examples.encapsulation;
/*
 * Overloading is used to implement multiple methods with the same name.
 * overloading is used to change arguments to different types.
 * Overloading is used to change return types to different types
 */

public class Overloading {
    int Sum(int a,int b) {
        return a + b;
    }
    double Sum(double a,double b) {
        return a + b;
    }

    int Sum(int a,int b,int c) {
        return a + b + c;
    }
    public static void main(String[] args) {
        Overloading obj = new Overloading();
        System.out.println(obj.Sum(10, 20)); // Outputs: 30
        System.out.println(obj.Sum(10.5, 20.7)); // Outputs: 31.2
        System.out.println(obj.Sum(10, 20, 30)); // Outputs: 60

    }
}
