package easy;

import java.util.*;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2y0c2/"/>
 * 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *  
 * <p>
 * 说明：
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * <p>
 * 进阶：
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 */
public class Question06 {

    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 9, 5};
        int[] nums2 = new int[]{9, 4, 9, 8, 4};

        int[] result = intersect1(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }

    /**
     * 此方法适合两个数组相差比较大的情况
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int value : nums1) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }
        for (int value : nums2) {
            if (map.getOrDefault(value, 0) > 0) {
                list.add(value);
                map.put(value, map.get(value) - 1);
            }
        }
        int[] results = new int[list.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = list.get(i);
        }
        return results;
    }

    /**
     * 先排序 再使用双指针
     * 当指针对应值相等指针同时后移，不等情况下，值小的指针向后移动一位
     * 此方法适用两个数组相差不大的情况
     */
    public static int[] intersect1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i <nums1.length && j<nums2.length){
            if(nums1[i] == nums2[j]){
                list.add(nums1[i]);
                i++;
                j++;
            }else if(nums1[i] < nums2[j]){
                i++;
            }else if(nums1[i] > nums2[j]){
                j++;
            }
        }

        int[] results = new int[list.size()];
        for (int k = 0; k < results.length; k++) {
            results[k] = list.get(k);
        }
        return results;
    }
}
