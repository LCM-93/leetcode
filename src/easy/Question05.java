package easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x21ib6/
 * 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 */
public class Question05 {
    public static void main(String[] args) {
        int[] nums = new int[]{4,1,2,1,2};
        int result = singleNumber2(nums);
        System.out.println(result);
    }

    // 这种加加减减的笨办法都能想到，也是神奇
    public static int singleNumber(int[] nums){
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = (i%2==0)? result+nums[i] :result-nums[i];
        }
        return result;
    }

    //异或运算，相同的抵消了
    public static int singleNumber1(int[] nums){
        int result= 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    //利用set集合特性
    public static int singleNumber2(int[] nums){
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if(!set.add(num)){
                set.remove(num);
            }
        }
        return (int) set.toArray()[0];
    }
}
