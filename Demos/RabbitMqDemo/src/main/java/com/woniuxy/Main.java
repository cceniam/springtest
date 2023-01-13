package com.woniuxy;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        System.out.println(Thread.currentThread().getName());

        //开启一个新线程,异步执行任务
        new Thread(new Runnable() {
            @Override
            public void run() {
                //新线程中执行任务
                for (int i = 0; i < 100; i++) {
                    try {
                        System.out.println(Thread.currentThread().getName() + i);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }

            }
        },"subThread").start();

        //主线程里的for循环
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + i);
        }

    }
}