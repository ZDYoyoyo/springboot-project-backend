package com.zdy;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

//    @Test
    public void testThreadLocalSetAndGet(){
        // 提供一個ThreadLocal物件
        ThreadLocal tl = new ThreadLocal();

        // 開啟兩個執行緒
        new Thread(()->{
            tl.set("小明");
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
        },"綠色").start();

        new Thread(()->{
            tl.set("小王");
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
        },"藍色").start();
    }
}
