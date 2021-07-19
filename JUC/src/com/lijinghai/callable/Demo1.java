package com.lijinghai.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方式：
 *      1.继承Thread
 *      2.实现Runnable接口
 *      3.实现Callable接口
 *      4.线程池方式
 * Runnable接口和Callable接口区别：
 *      1.是否有返回值
 *      2.是否抛出异常
 *      3.实现方法名称不同，一个run方法，一个call方法
 * 比较Runnable和Callable
 */

//实现Runnable接口
class MyThread1 implements Runnable{

    @Override
    public void run() {

    }
}

//实现Callable接口
class MyThread2 implements Callable{

    @Override
    public Integer call() throws Exception {
        return 1024;
    }
}


public class Demo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //Runnable接口创建线程
//        new Thread(new MyThread1(),"aa").start();


        //Callable接口创建线程
//        new Thread(new MyThread2(),"bb").start();  //报错，Callable接口不能通过这种方式创建线程

        /**
         * 由于Callable接口不能通过上述方式进行创建线程，因此需要借助中间类进行创建
         * 中间类：FutureTask
         */

        //方式一：
//        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
//        new Thread(futureTask,"bb").start();
//        //获取返回值
//        System.out.println(futureTask.get());


        //方式二
        //lambda表达式实现Callable
        FutureTask<Integer> futureTask2 = new FutureTask<>(()->{
            return 2048;
        });
        new Thread(futureTask2,"cc").start();
        //获取返回值
        System.out.println(futureTask2.get());


    }
}
