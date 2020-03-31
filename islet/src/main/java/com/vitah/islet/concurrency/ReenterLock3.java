package com.vitah.islet.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁申请等待限时
 *
 * @author vitah
 */
public class ReenterLock3 implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();

    private static final int WAIT_TIME = 5;

    @Override
    public void run() {
        try {
            // 线程在这个锁请求中最多等待5秒，超过5秒返回false
            if (lock.tryLock(WAIT_TIME, TimeUnit.SECONDS)) {
                Thread.sleep(6000);
            } else {
                System.out.println("get lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock3 lock3 = new ReenterLock3();
        Thread t1 = new Thread(lock3);
        Thread t2 = new Thread(lock3);
        t1.start();
        t2.start();
    }
}
