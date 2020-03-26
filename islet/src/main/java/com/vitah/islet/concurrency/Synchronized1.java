package com.vitah.islet.concurrency;

/**
 * @author vitah
 */
public class Synchronized1 implements Runnable {

    static Synchronized1 instance = new Synchronized1();
    static int i = 0;
    static final int COUNT = 10000000;

    @Override
    public void run() {
        for (int j = 0; j < COUNT; j++) {
            synchronized (instance) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // 输出20000000
        System.out.println(i);
    }
}
