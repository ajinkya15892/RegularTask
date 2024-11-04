package com.regularTask.examples.inheritance;

class TestExample implements Runnable {

    public static void main(String[] args) throws InterruptedException {
Thread thread = new Thread(new TestExample());
thread.start();
System.out.printf("%d",1);
thread.join();
System.out.printf("%d",2);

    }

    @Override
    public void run() {
        System.out.printf("%d",3);
    }
}
