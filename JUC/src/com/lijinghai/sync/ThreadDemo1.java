package com.lijinghai.sync;

/**
 * 两个线程对一个数字实现+1和-1方法
 *
 * ==> 线程间通信
 *
 * ==>当使用if判断时：若仅有两个线程对其实现加减操作则无问题
 *                  倘若大于两个线程对其实现操作则可能会出现“虚假唤醒”问题
 * ==>虚假唤醒：
 *          若使用if进行判断时，当线程不符合判断要求，它就会执行wait方法，此时通知其它线程，
 *          当其他线程执行完成，即会通知其他线程，此时进行wait方法的线程有可能获得，
 *          而wait方法有一个特点：哪里睡，哪里醒。因此它会直接往下执行，而不再进行判断
 *          ==>
 *              因此不能使用if判断，需要使用while循环即可避免出现虚假唤醒
 */

class Share {

    private int number = 0;

    //实现加1方法
    public synchronized void incr() throws InterruptedException {

        //判断number值
        while (number != 0) {
            this.wait();
        }

        //干活
        number++;
        System.out.println(Thread.currentThread().getName() + "::" + number);

        //通知其他线程
        this.notifyAll();
    }

    //实现减1方法
    public synchronized void decr() throws InterruptedException {

        //判断number值
        while (number != 1) {
            this.wait();
        }

        //-1
        number--;
        System.out.println(Thread.currentThread().getName() + "::" + number);

        //通知其他线程
        this.notifyAll();
    }
}

public class ThreadDemo1 {

    public static void main(String[] args) {
        //创建资源类对象
        Share share = new Share();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "aa").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "bb").start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "cc").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "dd").start();
    }
}
