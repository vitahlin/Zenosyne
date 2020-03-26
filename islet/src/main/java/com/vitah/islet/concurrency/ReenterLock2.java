package com.vitah.islet.concurrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁
 * <p>
 * 对于synchronized来说，如果一个线程等待锁，那么结果只有两种情况，要么获得锁继续执行，要么保持等待。
 * 而使用重入锁，则线程可以被中断。就是在等待锁的过程中，程序可以根据需要取消对锁的要求。
 * <p>
 * 线程t1和t2启动后，t1先占用lock1，再占用lock2；t2先占用lock2，再占用lock1。
 * 因此很容易形成t1和t2之间相互等待。在这里，对锁的请求，使用lockInterruptibly()。
 * 这是一个可以对中断进行响应的锁申请动作，即在等待锁的过程中，可以响应中断。
 * <p>
 * 代码75行，主线程main处于休眠状态，此时两个线程处于死锁。然后在78行，t2线程被中断，
 * 故t2会放弃对lock1的申请，同时释放已获得的lock2。这个操作可以让线程t1顺利得到lock2而继续执行。
 *
 * @author vitah
 */
public class ReenterLock2 implements Runnable {

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public ReenterLock2(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }

            System.out.println(Thread.currentThread().getId() + ":线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock2 reenterLock1 = new ReenterLock2(1);
        ReenterLock2 reenterLock2 = new ReenterLock2(2);

        Thread t1 = new Thread(reenterLock1);
        Thread t2 = new Thread(reenterLock1);

        t1.start();
        t2.start();
        Thread.sleep(1000);

        //中断其中一个线程
        t2.interrupt();
    }
}
