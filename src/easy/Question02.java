package easy;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2zsx1/"/>
 * 买卖股票的最佳时机
 * <p>
 * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: prices = [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 * <p>
 * 输入: prices = [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * <p>
 * 输入: prices = [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *  
 * 提示：
 * <p>
 * 1 <= prices.length <= 3 * 104
 * 0 <= prices[i] <= 104
 */
public class Question02 {

    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};

        int max = maxProfit1(prices);
        System.out.println("最大利润："+max);
    }

    /**
     * 动态规划算法
     */
    public static int maxProfit(int[] prices) {
        int hold = -prices[0];   //代表手上持有股票时最大收益
        int noHold = 0;    //代表手上没有股票时最大收益
        for (int i = 1; i < prices.length; i++) {
            hold = Math.max(hold, noHold - prices[i]);   //当天手上持有股票，要么前一天就持有，今天未卖出，要么前一天未持有，今天买入
            noHold = Math.max(noHold, hold + prices[i]);  //当天未持有股票，要么前一天就未持有，今天也未买入，要么前一天持有，今天卖出
        }
        return noHold;
    }


    /**
     * 贪心算法
     */
    public static int maxProfit1(int[] prices){
        int result = 0;
        int index = 0;
        int length = prices.length;
        while (index < length){
            //如果股票下跌就一直找，直到找到股票上涨为止
            while (index < length-1 && prices[index] >= prices[index+1])
                index++;

            //股票开始上涨的值
            int start = prices[index];

            //一直找到股票上涨的最大值
            while (index < length-1 && prices[index] <= prices[index+1])
                index++;

            result += prices[index++] - start;
        }

        return result;
    }

    /**
     * 贪心算法优化版，相邻两天的收益相减，只取正数的值
     */
    public static int maxProfit2(int[] prices){
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result += Math.max(prices[i]-prices[i-1],0);
        }
        return result;
    }
}
