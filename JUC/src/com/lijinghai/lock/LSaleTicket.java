package com.lijinghai.lock;

import java.util.concurrent.locks.ReentrantLock;

class LTicket {

    //票数
    private int number = 30;

    //创建可重入锁
    private final ReentrantLock reentrantLock = new ReentrantLock();

    //卖票方法
    public void sale() {

        reentrantLock.lock();  //上锁
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "=> 卖出:" + (number--) + "   剩余:" + number);
            }
        } finally {
            reentrantLock.unlock();  //解锁
        }

    }
}

public class LSaleTicket {

    public static void main(String[] args) {
        // 创建LTicket对象
        LTicket lTicket = new LTicket();

        Thread aa = new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        }, "aa");
        aa.start();

        Thread bb = new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        }, "bb");
        bb.start();

        Thread cc = new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        }, "cc");
        cc.start();
    }
}
