package com.vitah.islet.concurrency;

/**
 * 虽然两个线程指向了不同的Runnable实例，但是increase是静态方法。
 * 作用于静态方法时，请求的是当前类的锁。
 *
 * @author vitah
 */
public class Synchronized4 implements Runnable {

    static int i = 0;
    static final int COUNT = 10000000;

    private static synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < COUNT; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Synchronized4());
        Thread t2 = new Thread(new Synchronized4());

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // 输出20000000
        System.out.println(i);
    }
}
