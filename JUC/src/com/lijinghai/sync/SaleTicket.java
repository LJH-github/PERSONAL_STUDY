package com.lijinghai.sync;

/**
 * synchronized实现卖票
 *             自动上锁解锁
 */

/**
 * 1.创建资源类，定义属性和方法
 */
class Ticket {

    //票数
    private int number = 30;

    //卖票方法
    public synchronized void sale() {
        //判断是否还有票
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "=> 卖出:" + (number--) + "   剩余:" + number);
        }
    }

}

public class SaleTicket {

    public static void main(String[] args) {

        //创建Ticket对象
        Ticket ticket = new Ticket();

        Thread aa = new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "AA");

        aa.start();

        Thread bb = new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "BB");

        bb.start();

        Thread cc = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "CC");

        cc.start();
    }
}
