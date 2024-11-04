package com.regularTask.examples.inheritance;
/*
 * Overriding is runtime polymorphism
 */

public class Derived extends Base {
    @Override
    void fun(){
        System.out.println("Derived class");
    }
    void Derived() {
        System.out.println("Derived class");
    }
    public static void main(String[] args) {
        new Derived();
        Derived b=new Derived();
        b.Derived();
        float f1[],f2[];
        f1 = new float[10];
        f2 = f1;
        Base F=new Base();
        Base G=F;
        F.a +=1;
        F.b +=1;
        F.print();
        G.print();
        System.out.println(F.b); // Output: 2

        System.out.println(f2[0]); // Output: true
        int value=3,sum=6+ --value;
        System.out.println(sum); // Output: 9
        System.out.println(value); // Output: 2
        int data= --value + ++value/sum++ *value++ + ++sum % value--;
        System.out.println(data); // Output: 4
        int temp=9;
        data=8;
        System.out.println(temp & data); // Output: 16
        System.out.printf("%d",1);
        System.out.printf("%d",2);
    }
}
