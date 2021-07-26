package com.lijinghai.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join：将一个大任务拆分成多个子任务进行并行处理，最后将子任务结果合并成最后的计算结果
 *
 *      Fork:把复杂任务进行拆分，大事化小
 *      Join:把拆分任务的结果进行合并
 *
 *  举例：1+2+...+100
 */

class MyTask extends RecursiveTask<Integer> {

    //拆分差值不能超过10，计算10以内的运算
    private static final Integer VALUE = 10;
    private int begin;// 拆分开始值
    private int end; //拆分结束值
    private int result;

    //创建有参构造
    public MyTask(int begin,int end){
        this.begin = begin;
        this.end = end;
    }

    //拆分合并过程
    @Override
    protected Integer compute() {

        //判断相加的两个数值是否大于10
        if ((begin-end)<=VALUE){
            //相加操作
            for (int i = begin; i <= end ; i++) {
                result = result + i;
            }
        } else {
            //获取中间值
            int middle = (begin+end)/2;
            //拆分左侧
            MyTask task = new MyTask(begin, middle);
            //拆分右侧
            MyTask task1 = new MyTask(middle + 1, end);

            //调用方法拆分
            task.fork();
            task1.fork();

            //合并结果
            result = task.join()+task1.join();
        }
        return result;
    }
}



public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建MyTask对象
        MyTask myTask = new MyTask(0, 100);
        //创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);

        //获取最终合并结果
        Integer result = forkJoinTask.get();
        System.out.println(result);

        //关闭吃对象
        forkJoinPool.shutdown();

    }
}
