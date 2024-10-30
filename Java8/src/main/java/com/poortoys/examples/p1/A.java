//package com.poortoys.examples;
package com.poortoys.examples.p1;
public class A {
        private int a=805; //private variable is accessible within a class only.
        public void display(){
            System.out.println("Value of a: "+a);
        }

       //print is default method so it is accessible within package only.
       //For more detail try to access print method in class B.
        void print(){
            System.out.println("Value of a: "+a); //accessing private variable from within a class
        }
}
