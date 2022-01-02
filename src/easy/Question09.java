package easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2jrse/"/>
 * 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 */
public class Question09 {

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,4};
        int[] result = twoSum(nums,6);
        System.out.println(Arrays.toString(result));
    }

    //双指针解决
    public static int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = 1;
        int length = nums.length;
        while (left < length && target != nums[right] + nums[left]) {
            if (right >= length-1) {
                left++;
                right = left;
            }
            right++;
        }
        return new int[]{left,right};
    }

    //HashMap 能做好多事啊  很巧妙
    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.get(target-nums[i]) != null){
                return new int[]{map.get(target-nums[i]),i};
            }
            map.put(nums[i],i);
        }
        return new int[]{0,0};
    }
}
