package com.vitah.islet.singleton;

/**
 * 单例模式-双重检查模式DCL
 *
 * @author vitah
 */
public class SingletonFour {

    private volatile static SingletonFour instance;

    private SingletonFour() {
    }

    public static SingletonFour getInstance() {
        if (instance == null) {
            synchronized (SingletonFour.class) {
                if (instance == null) {
                    instance = new SingletonFour();
                }
            }
        }
        return instance;
    }
}
