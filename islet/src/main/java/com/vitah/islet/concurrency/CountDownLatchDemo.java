package com.vitah.islet.concurrency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计数器：CountDownLatch
 * 通常用来控制线程等待，可以让某一个线程等待直到倒计时结束，再开始执行
 *
 * @author vitah
 */
public class CountDownLatchDemo implements Runnable {

    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check complete");

            /**
             * 通知CountDownLatch，一个线程完成，倒计数器减1
             */
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            exec.submit(demo);
        }

        // 等待检查
        end.await();

        // 主线程在CountDownLatch上等待，所有检查任务全部完成后，主线程才可以执行
        System.out.println("Fire");

        exec.shutdown();
    }
}
