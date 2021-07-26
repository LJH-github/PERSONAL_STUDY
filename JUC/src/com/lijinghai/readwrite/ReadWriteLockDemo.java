package com.lijinghai.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock :读写锁
         ==>一个资源可以被多个线程访问，或者可以被一个写线程访问，但是不能同时存在读写线程
            读写互斥，读读共享的
 * 读写锁的演进：
 * 1.无锁
 *      ==>多线程抢夺资源
 *          乱
 * 2.添加锁
 *      ==>使用synchronized和ReentrantLock
 *          都是独占锁
 *          每次只能进行一个操作
 * 3.读写锁
 *      ==>ReentrantReadWriteLock
 *         读读 可以共享，提升性能
 *         同时多人进行读操作
 *      缺点：
 *          1）造成锁饥饿，一直读，没有写操作
 *          2）读时候，不能写，只有读完成之后，才可以写，写操作可以读
 *
 * 表锁：
 * 行锁：可能发生死锁
 *
 * 读锁；共享锁，发生死锁
 *
 * 写锁：独占锁，发生死锁
 *
 * 举例：
 *     模拟缓存过程
 */

//创建资源类
class Cache {

    private volatile Map<String, Object> map = new HashMap<>();

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //写数据
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();


        try {
            System.out.println(Thread.currentThread().getName() + "正在写操作" + key);
            map.put(key, value);
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + "写完了" + key);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    //取数据
    public Object get(String key) {
        readWriteLock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + "正在读操作" + key);
            TimeUnit.SECONDS.sleep(5);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读完了" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().lock();
        }
        return result;
    }
}

public class ReadWriteLockDemo {

    public static void main(String[] args) throws InterruptedException {
        Cache cache = new Cache();

        //创建线程，写数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                cache.put(num + "", num + "");
            }, String.valueOf(i)).start();
        }

        TimeUnit.SECONDS.sleep(5);

        //创建线程，读数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                cache.get(num + "");
            }, String.valueOf(i)).start();
        }
    }

}
