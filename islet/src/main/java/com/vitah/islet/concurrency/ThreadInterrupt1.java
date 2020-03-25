package com.vitah.islet.concurrency;

/**
 * 线程中断并不会使线程立即退出，而是给线程发送一个通知，告知目标线程，有人希望你退出。
 * 至于目标线程收到通知后如何处理，则完全由目标线程自行决定。
 *
 * @author vitah
 */
public class ThreadInterrupt1 {
    public static void main(String[] args) throws InterruptedException {
        final Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    Thread.yield();
                    System.out.println("Thread is running");
                }
            }
        };

        t1.start();
        Thread.sleep(2000);

        /**
         * 线程进行了中断，但是t1中并没有中断处理的逻辑，因此即使t1被设置为中断状态，也不会发生任何作用
         */
        t1.interrupt();
    }
}
