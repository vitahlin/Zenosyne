package com.vitah.islet.concurrency;

/**
 * @author vitah
 */
public class Synchronized2 implements Runnable {

    static Synchronized2 instance = new Synchronized2();
    static int i = 0;
    static final int COUNT = 10000000;

    private synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < COUNT; j++) {
            increase();
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
