package com.zdy.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal 工具類
 */
@SuppressWarnings("all")
public class ThreadLocalUtil {
    // 提供ThreadLocal物件
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    // 根據鍵獲取值
    public static <T> T get(){
        return (T) THREAD_LOCAL.get();
    }
    // 存儲鍵值對
    public static void set(Object value){
        THREAD_LOCAL.set(value);
    }

    // 清除ThreadLocal 防止內存泄漏
    public static void remove(){
        THREAD_LOCAL.remove();
    }
}

