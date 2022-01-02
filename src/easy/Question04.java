package easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x248f5/"/>
 * 存在重复元素
 * 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 *
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 *
 */
public class Question04 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,3,3,4,3,2,4,2};
        boolean result  = containsDuplicate2(nums);
        System.out.println(result);
    }

    public static boolean containsDuplicate(int[] nums){
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if(!set.add(num)){
                return true;
            }
        }
        return false;
    }

    public static boolean containsDuplicate1(int[] nums){
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i-1]){
                return true;
            }
        }
        return false;
    }

    //只能适用于32位之内的数字
    public static boolean containsDuplicate2(int[] nums){
        int shift = 0;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
         shift = 1<<nums[i];
         if((result & shift) >0){
             return true;
         }
         result |= shift;
        }
        return false;
    }
}
