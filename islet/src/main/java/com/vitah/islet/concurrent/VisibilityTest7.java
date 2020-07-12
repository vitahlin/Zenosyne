package com.vitah.islet.concurrent;

/**
 * 缓存一致性协议测试
 *
 * @author vitah
 */
public class VisibilityTest7 {

    private static volatile int sum = 0;
    
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    sum++;
                }
            });
            thread.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(sum);
    }
}
