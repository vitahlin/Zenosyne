package com.vitah.islet.concurrency;

/**
 * @author vitah
 */
public class ThreadInterrupt2 {
    public static void main(String[] args) throws InterruptedException {
        final Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    // 相应的中断处理代码
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted!");
                        break;
                    }
                    
                    System.out.println("Thread is running");
                    Thread.yield();
                }
            }
        };

        t1.start();
        Thread.sleep(2000);

        t1.interrupt();
    }
}
