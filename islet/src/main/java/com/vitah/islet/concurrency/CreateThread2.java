package com.vitah.islet.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 通过继承Callable来创建线程
 *
 * @author vitah
 */
public class CreateThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.print("Create thread implements Callable");
        return 1;
    }

    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(new CreateThread2());
        Thread t1 = new Thread(futureTask);
        t1.start();
    }
}
