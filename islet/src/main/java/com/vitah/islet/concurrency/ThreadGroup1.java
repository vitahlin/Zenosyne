package com.vitah.islet.concurrency;

/**
 * 线程组的使用
 *
 * @author vitah
 */
public class ThreadGroup1 implements Runnable {

    @Override
    public void run() {
        String groupName = Thread.currentThread().getThreadGroup().getName()
            + "-" + Thread.currentThread().getName();

        while (true) {
            System.out.println("I am " + groupName);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("PrintGroup");

        Thread t1 = new Thread(threadGroup, new ThreadGroup1(), "T1");
        Thread t2 = new Thread(threadGroup, new ThreadGroup1(), "T2");

        t1.start();
        t2.start();
        System.out.println(threadGroup.activeCount());
        threadGroup.list();
    }
}
