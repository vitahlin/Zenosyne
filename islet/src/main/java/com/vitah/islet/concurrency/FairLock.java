package com.vitah.islet.concurrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁允许对其公平性进行设置。
 * 公平锁要求系统维护一个队列，因此公平锁的成本比较高，性能非常低下。
 * <p>
 * 代码设置为公平锁，输入的内容为
 * Thread1获得锁
 * Thread2获得锁
 * 交替
 *
 * @author vitah
 */
public class FairLock implements Runnable {

    public static ReentrantLock fairLock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                fairLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FairLock r1 = new FairLock();

        Thread t1 = new Thread(r1, "Thread1");
        Thread t2 = new Thread(r1, "Thread2");
        t1.start();
        t2.start();
    }
}
