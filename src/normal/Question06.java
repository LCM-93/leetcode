package normal;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvvuqg/"/>
 * 递增的三元子序列
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * <p>
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,4,5]
 * 输出：true
 * 解释：任何 i < j < k 的三元组都满足题意
 * <p>
 * 示例 2：
 * 输入：nums = [5,4,3,2,1]
 * 输出：false
 * 解释：不存在满足题意的三元组
 * <p>
 * 示例 3：
 * 输入：nums = [2,1,5,0,4,6]
 * 输出：true
 * 解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 */
public class Question06 {

    public static void main(String[] args) {
        int[] nums = new int[]{6,7,1,2};
        System.out.println(increasingTriplet(nums));

    }


    public static boolean increasingTriplet(int[] nums) {
        int small = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;

        for (int num : nums) {
            //最小值一定比中间值先赋值 所以在中间值之前一定有一个比它小的
            //出现匹配的最大值是   最小值当前记录的并不一定是符合三元组的  但前面一定有一个符合的值
            if(num < small){
                small = num;
            }else if(num < mid){
                mid = num;
            }else {
                return true;
            }
        }
        return false;
    }
}
