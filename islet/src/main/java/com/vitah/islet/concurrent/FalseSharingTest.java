package com.vitah.islet.concurrent;


import sun.misc.Contended;

/**
 * 伪共享机制
 *
 * @author vitah
 */
public class FalseSharingTest {
    private static void testPointer(Pointer pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.x++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.y++;
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(System.currentTimeMillis() - start);
    }

    public static void main(String[] args) throws InterruptedException {
        testPointer(new Pointer());
    }
}

class Pointer {

    @Contended
    volatile long x;
    //long p1, p2, p3, p4, p5, p6, p7;
    

    volatile long y;
}
