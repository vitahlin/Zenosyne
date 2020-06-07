package com.vitah.islet.concurrency;

/**
 * Java是单继承的，可以使用Runnable接口来代替类继承线程Thread，重写run的方法来定义线程。
 *
 * @author vitah
 */
public class CreateThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Create thread by implements Runnable");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new CreateThread());
        t1.start();
    }
}
