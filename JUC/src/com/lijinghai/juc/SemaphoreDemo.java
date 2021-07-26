package com.lijinghai.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * JUC中常用的辅助类： Semaphore 信号灯类
 *
 * 举例：
 *      6辆车进入3个停车位
 */

public class SemaphoreDemo {

    public static void main(String[] args) {
        //设置3个许可
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i < 7; i++) {
            new Thread(() -> {
                try {
                    //获取到一个许可
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到停车位");
                    //设置随机的停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                    System.out.println("==>" + Thread.currentThread().getName() + "离开停车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
