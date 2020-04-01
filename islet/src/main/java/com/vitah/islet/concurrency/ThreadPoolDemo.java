package com.vitah.islet.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * newFixedThreadPool创建固定大小的线程池
 *
 * @author vitah
 */
public class ThreadPoolDemo {

    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask task = new MyTask();
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            es.submit(task);
        }
    }
}
