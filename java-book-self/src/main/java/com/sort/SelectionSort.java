package com.sort;

/**
 * @author dongzonglei
 * @description
 * @date 2020/10/17 下午3:24
 */
public class SelectionSort {
    
    public int[] selectionSort(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (nums[j] < nums[minIndex]) { // 寻找最小的数
                    minIndex = j;               // 将最小数的索引保存
                }
            }
            int temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
        return nums;
    }
}
