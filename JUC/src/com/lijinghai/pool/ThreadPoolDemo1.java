package com.lijinghai.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadPool:线程池
 *      ==>Executors.newFixedThreadPool(int)：一池N线程
 *         Executors.newSingleThreadExecutor()：一池一线程
 *         Executors.newCachedThreadPool()：线程池根据需求创建线程，遇强则强
 *      ==>底层都是使用 new ThreadPoolExecutor 创建线程池
 *
 * 举例:
 *      银行5个服务窗口，来10名客户
 */


public class ThreadPoolDemo1 {

    public static void main(String[] args) {

        //1.一池N线程
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        //2.一池一线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();

        //3.一池可扩容
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        try {
            for (int i = 1;i <= 20; i++){
                //执行线程
                threadPool3.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务！");
                });
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            //归还线程
            threadPool3.shutdown();
        }

    }
}
