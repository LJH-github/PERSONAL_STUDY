package com.lijinghai.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * 线程池的七个参数：(new ThreadPoolExecutor())
 *      int corePoolSize：常驻线程数量（核心）
 *      int maximumPoolSize：最大线程数量
 *      long keepAliveTime：线程的存活时间：值
 *      TimeUnit unit：线程的存活时间：单位
 *      BlockingQueue<Runnable> workQueue：阻塞队列
 *      ThreadFactory threadFactory：线程工厂
 *      RejectedExecutionHandler handler：拒绝策略
 *
 */


public class ThreadPoolDemo2 {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        try {
            for (int i = 1; i <= 10; i++) {
                //执行线程
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" 办理业务");
                });
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }

    }
}
