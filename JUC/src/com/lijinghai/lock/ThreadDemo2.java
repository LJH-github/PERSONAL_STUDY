package com.lijinghai.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程对一个数字实现+1和-1方法
 *
 * ==>线程间通信
 */

class LShare {

    private int number = 0;

    //可重入锁
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //+1
    public void incr() throws InterruptedException {

        lock.lock(); //上锁
        try {
            //判断number值
            while (number != 0) {
                condition.await();
            }

            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "::" + number);

            //通知其他线程
            condition.signalAll();
        } finally {
            lock.unlock();  //解锁
        }

    }

    //-1
    public void decr() throws InterruptedException {

        lock.lock();

        try {

            while (number != 1) {
                condition.await();
            }

            number--;
            System.out.println(Thread.currentThread().getName() + "::" + number);

            condition.signalAll();

        } finally {
            lock.unlock();
        }

    }

}

public class ThreadDemo2 {

    public static void main(String[] args) {

        //创建LShare对象
        LShare lShare = new LShare();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lShare.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "aa").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lShare.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "bb").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lShare.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "cc").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lShare.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "dd").start();

    }
}
