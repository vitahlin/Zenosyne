package com.vitah.islet.concurrency;

/**
 * 线程wait()和notify()的使用，一个对象调用了wait，当前线程就会在这个对象上等待，
 * 直到等到其他线程调用了这个对象的notify方法为止。
 *
 * <p>
 * 运行结果：
 * 1585129298447: T1 start
 * 1585129298447: T1 wait for obj
 * 1585129298447: T2 start, notify one thread
 * 1585129298447: T2 end
 * 1585129300449: T1 end
 * <p>
 * 注意运行时间戳，T2通知T1继续执行后，并不会立即执行，而是要等待T2释放obj的锁，并且重新获得锁后，
 * 才能继续执行。因此13行和12的运行结果差2秒。
 * <p>
 * wait方法不能乱用，必须包含在synchronized语句中，无论wait还是notify都需要首先获得目标对象的一个监视器。
 *
 * @author vitah
 */
public class ThreadWaitAndNotify {
    final static Object obj = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (obj) {
                System.out.println(System.currentTimeMillis() + ": T1 start");

                try {
                    System.out.println(System.currentTimeMillis() + ": T1 wait for obj");
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ": T1 end");
            }
        }
    }


    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (obj) {
                System.out.println(System.currentTimeMillis() + ": T2 start, notify one thread");
                obj.notify();
                System.out.println(System.currentTimeMillis() + ": T2 end");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();
    }
}
