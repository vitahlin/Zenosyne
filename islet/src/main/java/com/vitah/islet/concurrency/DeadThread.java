package com.vitah.islet.concurrency;

/**
 * 产生死锁的示例
 *
 * @author vitah
 */
public class DeadThread {

    private static final String A = "a";
    private static final String B = "b";

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            synchronized (A) {
                System.out.println(
                    "Thread A进入同步块A..."
                );

                try {
                    Thread.sleep(3000);
                    synchronized (B) {
                        System.out.println(
                            "Thread A进入同步块B..."
                        );
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "threadA");

        Thread threadB = new Thread(() -> {
            synchronized (B) {
                System.out.println(
                    "Thread B进入同步块B..."
                );

                try {
                    Thread.sleep(3000);
                    synchronized (A) {
                        System.out.println(
                            "Thread B进入同步块A..."
                        );
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "threadA");

        threadA.start();
        threadB.start();
    }
}
