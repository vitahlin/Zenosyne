package com.vitah.islet.singleton;

/**
 * 单例模式-饿汉模式
 * 类加载时就完成了初始化，所以类加载较慢，但是获取速度快。
 * 基于类加载机制避免了多线程问题，但是也不能确定有其他的方式（或者其他静态方法）导致了类装载，
 * 这时候instance没有达到懒加载的效果。
 *
 * @author vitah
 */
public class SingletonOne {

    private static SingletonOne instance = new SingletonOne();

    private SingletonOne() {
    }

    public static SingletonOne getInstance() {
        return instance;
    }

    public void print() {
        System.out.print("Hello");
    }

    public static void main(String[] args) {
        SingletonOne.getInstance().print();
    }
}
