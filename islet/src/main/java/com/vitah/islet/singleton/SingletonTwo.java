package com.vitah.islet.singleton;

/**
 * 单例模式-懒汉模式，线程不安全
 *
 * @author vitah
 */
public class SingletonTwo {

    private static SingletonTwo instance;

    private SingletonTwo() {
    }

    public static SingletonTwo getInstance() {
        if (instance == null) {
            instance = new SingletonTwo();
        }
        return instance;
    }
}
