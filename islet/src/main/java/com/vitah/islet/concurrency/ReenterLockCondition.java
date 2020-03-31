package com.vitah.islet.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition与wait()和notify()的作用大致相同。但是wait()和notify()方法是与synchronized合作使用的，
 * 而Condition是与重入锁相关联的。通过lock接口的Condition new Condition()方法可以生成一个与当前重入锁
 * 绑定的Condition对象。利用Condition对象，就可以让线程在合适的时间等待，或者在某一个特定的时候得到通知，
 * 继续执行。
 *
 * @author vitah
 */
public class ReenterLockCondition implements Runnable {

    public static ReentrantLock lock1 = new ReentrantLock();

    // 生成一个与之绑定的Condition对象
    public static Condition condition = lock1.newCondition();

    @Override
    public void run() {
        try {
            lock1.lock();

            // 使线程等待，并且释放当前锁
            condition.await();
            System.out.println("Thread is going on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock1.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition r1 = new ReenterLockCondition();
        Thread t1 = new Thread(r1);
        t1.start();
        Thread.sleep(2000);

        // 通知线程t1继续执行
        lock1.lock();

        // 唤醒一个等待中的线程，在signal()调用时，要求线程先获得相关的锁
        condition.signal();
        lock1.unlock();
    }
}
