package com.vitah.islet.concurrency;

/**
 * 守护线程的使用
 * <p>
 * 如果用户线程全部结束，守护线程要守护的对象已经不存在了，那么整个应用程序就会结束。
 * <p>
 * t被设置为守护线程，系统中只有主线程main为用户线程，main线程在休眠2秒后退出，整个程序随之结束。
 * 但如果不把线程t设置为守护线程，那么main线程结束后，t线程还会不停地打印，永远不会结束。
 *
 * @author vitah
 */
public class DaemonThread1 {

    public static class T1 extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("I am alive");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new T1();
        t.setDaemon(true);
        t.start();

        Thread.sleep(2000);
    }
}
