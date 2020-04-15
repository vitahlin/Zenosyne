package com.vitah.islet.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例模式-使用容器
 *
 * @author vitah
 */
public class SingletonSeven {
    private static Map<String, Object> objectMap = new HashMap<>();

    private SingletonSeven() {
    }

    public static Object getService(String key) {
        return objectMap.get(key);
    }

    public static void registerService(String key, Object instance) {
        if (!objectMap.containsKey(key)) {
            objectMap.put(key, instance);
        }
    }
}
