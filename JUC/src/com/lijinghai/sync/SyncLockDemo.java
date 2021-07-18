package com.lijinghai.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized（隐式）和Lock（显示）均是可重入锁（又称为递归锁）
 *      ==>存在几个大门嵌套，可重入锁若能进入第一个大门，则内部的几个大门无障碍进入。
 *
 */

public class SyncLockDemo {

    public synchronized void add() {
        add();
    }

    public static void main(String[] args) {

        /**
         * synchronized
         */

//        new SyncLockDemo().add();  //java.lang.StackOverflowError ==>add方法实现了循环调用

//        Object o = new Object();
//        synchronized (o){
//            System.out.println(Thread.currentThread().getName()+" 外层");
//            synchronized (o){
//                System.out.println(Thread.currentThread().getName()+" 中层");
//                synchronized (o){
//                    System.out.println(Thread.currentThread().getName()+" 内层");
//                }
//            }
//        }

        /**
         * Lock方式实现可重入锁Reen
         */

        Lock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 外层");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " 内层");

                } finally {
                    /**
                     * 此处若不进行解锁，并不影响本线程的执行
                     * 若有其他线程存在，则会对其他线程产生影响
                     */
//                    lock.unlock();
                }

            } finally {
                lock.unlock();
            }
        },"aa").start();

        new Thread(()->{
            lock.lock();
            //此线程收aa线程的影响，因为aa线程中可重入锁未释放，因此处于等待状态
            System.out.println(Thread.currentThread().getName()+" bb");
            lock.unlock();
        },"bb").start();
    }

}
