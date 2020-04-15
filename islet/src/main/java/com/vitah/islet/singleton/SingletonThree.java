package com.vitah.islet.singleton;

/**
 * 单例模式-懒汉模式，线程安全
 *
 * @author vitah
 */
public class SingletonThree {

    private static SingletonThree instance;

    private SingletonThree() {
    }

    public static synchronized SingletonThree getInstance() {
        if (instance == null) {
            instance = new SingletonThree();
        }
        return instance;
    }
}
