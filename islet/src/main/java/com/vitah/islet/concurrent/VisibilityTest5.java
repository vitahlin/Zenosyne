package com.vitah.islet.concurrent;

/**
 * 可见性测试
 *
 * @author vitah
 */
public class VisibilityTest5 {

    private boolean flag = true;

    public void refresh() {
        this.flag = false;
        System.out.println(Thread.currentThread().getName() + "修改flag");
    }

    public void load() {
        System.out.println(Thread.currentThread().getName() + "开始执行.....");
        int i = 0;
        while (flag) {
            i++;
            System.out.println("调用System.out.println");
        }
        System.out.println(Thread.currentThread().getName() + "跳出循环: i=" + i);
    }

    /**
     * 增加程序时间
     *
     * @param interval 纳秒
     */
    public static void shortWait(long interval) {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start + interval >= end);
    }

    public static void main(String[] args) {
        VisibilityTest5 test = new VisibilityTest5();
        new Thread(() -> test.load(), "threadA").start();
        try {
            Thread.sleep(2000);
            new Thread(() -> test.refresh(), "threadB").start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
