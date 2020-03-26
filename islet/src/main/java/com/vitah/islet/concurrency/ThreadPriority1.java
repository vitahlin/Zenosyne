package com.vitah.islet.concurrency;

/**
 * 线程优先级
 * <p>
 * 大部分情况下，高优先级总是比低优先级块，但是不能保证所有情况下都是如此。
 *
 * @author vitah
 */
public class ThreadPriority1 {

    public static class T1 extends Thread {
        static int count;

        @Override
        public void run() {
            while (true) {
                synchronized (ThreadPriority1.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("HighPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static class T2 extends Thread {
        static int count;

        @Override
        public void run() {
            while (true) {
                synchronized (ThreadPriority1.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("LowPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new T1();
        Thread t2 = new T2();

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

        t2.start();
        t1.start();
    }
}
