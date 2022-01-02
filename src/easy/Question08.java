package easy;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2ba4i/"/>
 * 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class Question08 {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        moveZeroes2(nums);
        System.out.println(Arrays.toString(nums));
    }

    //虽然可行  效率太慢
    public static void moveZeroes(int[] nums) {
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = 0; j < nums.length - 1; j++) {
                if (nums[j + 1] == (nums[j + 1] ^ nums[j])) {
                    int temp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    public static void moveZeroes1(int[] nums) {
        int index= 0;
        //一次遍历，把非零的都往前挪
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) nums[index++] = nums[i];
        }
        //后面剩下的都是0
        while (index < nums.length){
            nums[index ++] = 0;
        }
    }


    public static void moveZeroes2(int[] nums) {
        int count = 0;  //统计前面0的个数
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 0){ //如果当前数字是0就不操作
                count ++;
            }else if(count != 0){
                //否则，把当前数字放到最前面那个0的位置，然后再把
                //当前位置设为0
                nums[i-count] = nums[i];
                nums[i] = 0;
            }
        }
    }
}
