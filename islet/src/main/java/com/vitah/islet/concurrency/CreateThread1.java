package com.vitah.islet.concurrency;

/**
 * 通过继承Thread来实现创建线程
 *
 * @author vitah
 */
public class CreateThread1 extends Thread {
    @Override
    public void run() {
        System.out.print("Create thread by extend Thread.");
    }

    public static void main(String[] args) {
        new CreateThread1().start();
    }
}
