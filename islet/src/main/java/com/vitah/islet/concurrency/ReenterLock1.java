package com.vitah.islet.concurrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁
 *
 * @author vitah
 */
public class ReenterLock1 implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();

    static int i = 0;
    static final int COUNT = 10000000;

    @Override
    public void run() {
        for (int j = 0; j < COUNT; j++) {
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock1 reenterLock1 = new ReenterLock1();
        Thread t1 = new Thread(reenterLock1);
        Thread t2 = new Thread(reenterLock1);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);

    }
}
