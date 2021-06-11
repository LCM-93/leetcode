package easy;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2skh7/
 * 旋转数组
 * <p>
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * <p>
 *  
 * <p>
 * 进阶：
 * <p>
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 * <p>
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 */
public class Question03 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotate2(nums, 3);
        System.out.println(Arrays.toString(nums));
    }

    //自己的想法，通过一个临时数组转换，思维局限在题意里
    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 0) return;
        k = k % nums.length;
        int[] temp = new int[k];
        int tempLength = temp.length;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (tempLength > 0) {
                temp[--tempLength] = nums[i];
            }
            nums[i] = i - k >= 0 ? nums[i - k] : temp[i];
        }
    }


    //通过三次翻转，不适用额外数组空间
    public static void rotate1(int[] nums, int k) {
        if (nums == null || nums.length <= 0) return;
        k = k % nums.length;
        if (k == 0) return;
        reverse(nums, 0, nums.length - 1); //先翻转全部元素
        reverse(nums, 0, k - 1);  //再翻转前K个元素
        reverse(nums, k, nums.length - 1); //接着翻转剩余的
    }
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[end];
            nums[end--] = nums[start];
            nums[start++] = temp;
        }
    }


    //使用临时数组
    public static void rotate2(int[] nums, int k){
        int length = nums.length;
        int[] temp =  Arrays.copyOf(nums,length);
        for (int i = 0; i < temp.length; i++) {
            nums[(i+k)%length] = temp[i];
        }
    }
}
