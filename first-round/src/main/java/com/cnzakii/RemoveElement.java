package com.cnzakii;

/**
 * 任务3-完成[27. 移除元素 - 力扣（LeetCode）<br/>
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。<br/>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。<br/>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-07-31
 **/
public class RemoveElement {

    /**
     * 移除元素
     *
     * @param nums 目标数组
     * @param val  需要移除的数值
     * @return 移除后的数组长度
     */
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        int i = 0;
        while (i < len) {
            if (nums[i] == val) {
                if (i != len - 1) {
                    nums[i] = nums[len - 1];
                }
                len--;
            } else {
                i++;
            }
        }
        return len;
    }
}
