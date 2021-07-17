package com.lijinghai;

public class Main {

    public static void main(String[] args) {

        Thread aa = new Thread(() -> {

            // isDaemon方法用于判断线程是否为守护线程
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true) {

            }
        }, "aa");

        aa.setDaemon(true);   // 将线程设置为守护线程，必须在启动线程前设置
        aa.start();   // 启动线程，线程处于就绪状态，并没有直接运行，当该线程获得cpu时间片，即开始执行run方法。

        System.out.println(Thread.currentThread().getName() + "over");

    }

}
