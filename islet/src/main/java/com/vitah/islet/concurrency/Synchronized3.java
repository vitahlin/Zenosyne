package com.vitah.islet.concurrency;

/**
 * 错误的同步方式，因为执行这段代码的两个线程指向了不同的Runnable实例
 *
 * @author vitah
 */
public class Synchronized3 implements Runnable {

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
        Thread t1 = new Thread(new Synchronized3());
        Thread t2 = new Thread(new Synchronized3());

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(i);
    }
}
