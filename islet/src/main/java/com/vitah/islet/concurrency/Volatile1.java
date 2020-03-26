package com.vitah.islet.concurrency;

/**
 * volatile并不能保证原子性
 *
 * @author vitah
 */
public class Volatile1 {
    public static final int COUNT = 10000000;
    static volatile int i = 0;
    private static final int THREAD_COUNT = 10;

    public static class T1 implements Runnable {
        @Override
        public void run() {
            for (int j = 0; j < COUNT; j++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int j = 0; j < THREAD_COUNT; j++) {
            threads[j] = new Thread(new T1());
            threads[j].start();
        }

        for (int j = 0; j < THREAD_COUNT; j++) {
            threads[j].join();
        }

        System.out.println(i);
    }
}
