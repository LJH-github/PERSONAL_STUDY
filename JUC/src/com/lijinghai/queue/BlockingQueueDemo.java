package com.lijinghai.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * BlockingQueue:阻塞队列
 *
 *      ==> 1.ArrayBlockingQueue(常用)
 *              基于数组实现的阻塞队列
 *      ==> 2.LinkedBlockingQueue(常用)
 *              基于链表实现的阻塞队列
 *      ==> 3.DelayQueue
 *              其中的元素只有到了指定的延迟时间，才能从队列中取到该元素
 *      ==> 4.PriorityBlockingQueue
 *              基于优先级的阻塞队列
 *      ==> 5.SynchronousQueue
 *              一个无缓冲的等待队列
 *      ==> 6.LinkedTransferQueue
 *              有链表结构组成的无界队列
 *      ==> 7.LinkedBlockingDeque
 *              有链表结构组成的双向阻塞队列
 *
 * 在多线程中：所谓的阻塞，即在某些情况下会挂起线程，当条件满足，被挂起的线程又会自动被唤起
 *
 */


public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        //创建基于数组结构的阻塞队列:大小为3
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

/*

        //1.方法类型：抛出异常

        //插入
        System.out.println(blockingQueue.add("a"));   //true
        System.out.println(blockingQueue.add("b"));   //true
        System.out.println(blockingQueue.add("c"));   //true
//        System.out.println(blockingQueue.add("d"));   //java.lang.IllegalStateException: Queue full

        //检查
        System.out.println(blockingQueue.element());

        //移除
        System.out.println(blockingQueue.remove());   //a
        System.out.println(blockingQueue.remove());   //b
        System.out.println(blockingQueue.remove());   //c
        System.out.println(blockingQueue.remove());   //java.util.NoSuchElementException
*/

/*
        //2.方法类型：特殊值

        //插入
        System.out.println(blockingQueue.offer("a"));  //true
        System.out.println(blockingQueue.offer("b"));  //true
        System.out.println(blockingQueue.offer("c"));  //true
        System.out.println(blockingQueue.offer("d"));  //false

        //检查
        System.out.println(blockingQueue.peek());
        //移除

        System.out.println(blockingQueue.poll());  //a
        System.out.println(blockingQueue.poll());  //b
        System.out.println(blockingQueue.poll());  //c
        System.out.println(blockingQueue.poll());  //null

        */
/*

        //3.方法类型：阻塞

        //插入
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
//        blockingQueue.put("d");  //阻塞状态

        //移除
        System.out.println(blockingQueue.take());  //a
        System.out.println(blockingQueue.take());  //b
        System.out.println(blockingQueue.take());  //c
        System.out.println(blockingQueue.take());  //阻塞状态

        */

        //4.方法类型：超时

        System.out.println(blockingQueue.offer("a"));  // true
        System.out.println(blockingQueue.offer("b"));  // true
        System.out.println(blockingQueue.offer("c"));  // true
        System.out.println(blockingQueue.offer("d",3L, TimeUnit.SECONDS)); //3秒内阻塞，然后自动退出

    }


}
