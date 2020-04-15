package com.vitah.islet.singleton;

/**
 * 单例模式-静态内部类模式
 *
 * @author vitah
 */
public class SingletonFive {

    private SingletonFive() {
    }

    private SingletonFive getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final SingletonFive instance = new SingletonFive();
    }
}
