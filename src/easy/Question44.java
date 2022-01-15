package easy;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn1m0i/"/>
 * 位1的个数
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 * <p>
 *  
 * <p>
 * 提示：
 * <p>
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 *  
 * <p>
 * 示例 1：
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 * <p>
 * 示例 2：
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 * <p>
 * 示例 3：
 * 输入：11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 * <p>
 * 提示：
 * 输入必须是长度为 32 的 二进制串 。
 *  
 * 进阶：
 * 如果多次调用这个函数，你将如何优化你的算法？
 */
public class Question44 {

    public static void main(String[] args) {
        int i = hammingWeight1(-3);
        System.out.println("数字1的个数为：" + i);
    }

    public static int hammingWeight(int n) {
        String s = Integer.toBinaryString(n);
        char[] chars = s.toCharArray();
        int count = 0;
        for (char aChar : chars) {
            if (aChar == '1') {
                count++;
            }
        }
        return count;
    }

    public static int hammingWeight1(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if (((n >>> i) & 1) == 1) {  //位运算
                count++;
            }
        }
        return count;
    }

    public static int hammingWeigh2(int n) {
        int count = 0;
        while (n != 0) {  //位运算优化，当n=0了就没必要再计算了
            count += n & 1;
            n = n >>> 1;
        }
        return count;
    }

    /**
     * 原始数字不动  移动1
     */
    public static int hammingWeigh3(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * 在位运算中有这样一个操作，就是n&(n-1)可以把n最右边的1给消掉
     * <a href="https://mp.weixin.qq.com/s/wd3ZdWPtKS-b_4ReBCyfwQ"/>
     */
    public static int hammingWeigh4(int n){
        int count = 0;
        while (n != 0){
            n &= n-1;
            count++;
        }
        return count;
    }
}
