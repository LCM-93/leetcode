package easy;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn854d/"/>
 * 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * <p>
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class Question34 {

    public static void main(String[] args) {

    }


    /**
     * 数值较大时会超时
     * f(n) = f(n-1) + f(n -2)
     */
    public static int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * 使用尾递归方式
     */
    public static int climbStairs1(int n) {
        return climbStairs1(n, 1, 1);
    }

    public static int climbStairs1(int n, int a, int b) {
        if (n <= 1) return b;

        return climbStairs1(n - 1, a, a + b);
    }


    public static int climbStairs2(int n) {
        if (n <= 1) return 1;
        int dp[] = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int climbStairs3(int n) {
        if(n <=2) return n;
        int first = 1;
        int second = 2;
        int sum = 0;
        while (n-- >2){
            sum = first +second;
            first = second;
            second = sum;
        }
        return sum;
    }
}
