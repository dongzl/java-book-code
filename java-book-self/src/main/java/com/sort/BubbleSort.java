package com.sort;

/**
 * @author dongzonglei
 * @description
 * @date 2020/10/17 下午3:14
 */
public class BubbleSort {
    
    public int[] bubbleSort(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) { // 相邻元素两两对比
                    int temp = nums[j + 1];  // 元素交换
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }
}
