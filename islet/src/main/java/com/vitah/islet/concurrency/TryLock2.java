package com.vitah.islet.concurrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 先让t1获得lock1，再让t2获得lock2，接着做反向请求，让t1申请lock2，t2申请lock1。
 * 一般情况下，这会导致t1和t2相互等待，引起死锁。
 * <p>
 * 但是使用tryLock方法后，线程不会再等待，而是不停地尝试。因此，只要执行足够长的时间，
 * 线程总是会得到所有需要的资源，从而正常执行。
 *
 * @author vitah
 */
public class TryLock2 implements Runnable {

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public TryLock2(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if (lock == 1) {
            while (true) {
                if (lock1.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (lock2.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId() + ":My Job done");
                            } finally {
                                lock2.unlock();
                            }
                        }
                    } finally {
                        lock1.unlock();
                    }
                }
            }
        } else {
            while (true) {
                if (lock2.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (lock1.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId() + ":My Job done");
                            } finally {
                                lock1.unlock();
                            }
                        }
                    } finally {
                        lock2.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TryLock2 r1 = new TryLock2(1);
        TryLock2 r2 = new TryLock2(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }
}
