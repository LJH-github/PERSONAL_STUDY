package com.lijinghai.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture
 *      ==>可实现同步或者异步调用
 *
 */

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1.同步调用    void :表示没有返回值
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+":CompletableFuture1");
        });
        completableFuture1.get();




        //2.异步调用    ====>通常不是用这种方式进行异步调用，而是使用mq等消息队列
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+":CompletableFuture2");
            int i = 1 / 0;
            return 1024;
        });
        completableFuture2.whenComplete((t,u)->{
            System.out.println("==> t="+t);  //t:返回值
            System.out.println("==> u="+u);  //u:异常信息
        }).get();
    }

}
