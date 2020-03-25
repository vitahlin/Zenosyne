package com.vitah.islet.concurrency;

/**
 * join表示无限等待，它会一直阻塞当前线程，直到目标线程执行完毕。
 *
 * @author vitah
 */
public class ThreadJoin {
    public static final int COUNT = 10000000;
    public volatile static int i = 0;
    public volatile static int j = 0;

    public static class T1 extends Thread {
        @Override
        public void run() {
            for (i = 0; i < COUNT; i++) {
                ;
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            for (j = 0; j < COUNT; j++) {
                ;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t2 = new T2();
        t2.start();
        System.out.println(j);

        Thread t1 = new T1();
        t1.start();
        t1.join();
        System.out.println(i);
    }
}
