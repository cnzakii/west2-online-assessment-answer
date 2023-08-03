package com.cnzakii.task2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 设计一个接收两个int数组的方法void, 使用多线程交替输出其中的元素<br/>
 * 如接收 arr1 = {1, 3, 5, 7, 9}, arr2 = {2, 4, 6, 8, 10}, 则输出 1 2 3 4 5 6 7 8 9 10
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-03
 **/
public class Solution {

    private static final Logger LOGGER = Logger.getLogger(Solution.class.getName());

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};

        alternateOutput(arr1, arr2);
    }


    /**
     * 使用多线程交替输出两个数组中的元素
     *
     * @param arr1 数组1
     * @param arr2 数组2
     */
    private static void alternateOutput(int[] arr1, int[] arr2) {
        Thread t1 = new Thread(() -> printArrayElements(arr1));
        Thread t2 = new Thread(() -> printArrayElements(arr2));

        // 设置优先级，确保线程1比线程2优先执行
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
    }

    /**
     * 打印数组元素
     *
     * @param arr 数组
     */
    private static void printArrayElements(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
            // 添加一些延时，模拟实际情况下线程切换的效果
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, "Thread interrupted", e);
            }
        }
    }
}
