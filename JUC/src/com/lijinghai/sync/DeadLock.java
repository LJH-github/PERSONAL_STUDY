package com.lijinghai.sync;

import java.util.concurrent.TimeUnit;

/**
 * 死锁：两个或两个以上的线程在执行过程中，因为相互争夺资源而造成互相等待的现象。
 *      ==>若无外部干涉，它们将无法进行下去
 *
 * 产生原因：
 *      1.系统资源不足
 *      2.进程运行顺序不合适
 *      3.资源分配不当
 *
 * ==>下面实现死锁现象
 *
 */

public class DeadLock {

    //必须是静态，不然main函数中访问不到
    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) {

        new Thread(()->{
            synchronized (o1){
                System.out.println(Thread.currentThread().getName()+":持有锁o1，试图获取锁o2!");
                //让其睡眠，不然可能就不会出现死锁现象
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (o2){
                    System.out.println(Thread.currentThread().getName()+":获取锁o2!");
                }
            }
        },"aa").start();

        new Thread(()->{
            synchronized (o2){
                System.out.println(Thread.currentThread().getName()+":持有锁o2，试图获取锁o1!");
                //让其睡眠，不然可能就不会出现死锁现象
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println(Thread.currentThread().getName()+":获取锁o1!");
                }
            }
        },"aa").start();

    }
}
