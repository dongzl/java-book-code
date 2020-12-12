package com.sort;

/**
 * @author dongzonglei
 * @description
 * @date 2020/10/17 下午3:39
 */
public class InsertionSort {
    
    public int[] insertionSort(int[] nums) {
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            int preIndex = i - 1;
            while (preIndex >= 0 && nums[preIndex] > nums[i]) {
                nums[preIndex + 1] = nums[preIndex];
                preIndex--;
            }
            nums[preIndex + 1] = nums[i];
        }
        return nums;
    }
}
