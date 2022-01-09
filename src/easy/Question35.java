package easy;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn8fsh//>
 * 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * <p>
 * 示例 2：
 * <p>
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class Question35 {

    public static void main(String[] args) {
        int[] nums = new int[]{7,1,5,3,6,4};
        int i = maxProfit1(nums);
        System.out.println("最大利润是："+i);
    }

    /**
     * 只能选择一天购买股票！！
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int length = prices.length;
        int hold = -prices[0];//持有股票
        int noHold = 0;//不持有股票
        for (int i = 1; i < length; i++) {
            //递推公式
            noHold = Math.max(noHold, hold + prices[i]);
            hold = Math.max(hold, -prices[i]); //只能购买一次
        }
        //毋庸置疑，最后肯定是手里没持有股票利润才会最大，
        //也就是卖出去了
        return noHold;
    }


    /**
     * 双指针
     * @param prices
     * @return
     */
    public static int maxProfit1(int[] prices){
        if (prices == null || prices.length == 0)
            return 0;

        int min = prices[0];
        int max = 0;

        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min,prices[i]);
            max = Math.max(max,prices[i] -min);
        }
        return max;
    }
}
