package com.lijinghai.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 锁降级：将写入锁降级为读锁
 * jdk8 写锁降级为读锁
 *
 * 步骤：
 *      获取写锁==>获取读锁==>释放写锁==>释放读锁
 * ==> 读锁不能升级为写锁
 */

public class Demo1 {

    public static void main(String[] args) {

        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

        //锁降级


        //2.获取读锁
        readLock.lock();
        System.out.println("==>readLock");

        //1.获取写锁
        writeLock.lock();
        System.out.println("==>writeLock");


        //3.释放写锁
        writeLock.unlock();

        //4.释放读锁
        readLock.unlock();

    }
}
