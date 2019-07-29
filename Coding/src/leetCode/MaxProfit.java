package leetCode;
/**
 * 题目：121.买卖股票的最佳时机
 * 描述：给定一个数组，它的第 i个元素是一支给定股票第 i天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * 注意你不能在买入股票前卖出股票。
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * 
 * 
 * 思路：动态规划
 * 另外可以看看leetcode上一个解答【状态转移框架】：一个方法团灭6道股票问题
 * 
 * @author yajie
 *
 */
public class MaxProfit {
	//为什么每次我动态规划都是想到的是这种两层循环。超出内存限制。
	public static int maxProfit0(int[] prices) {
		if (prices == null || prices.length <= 0) {
			return 0;
		}
		int len = prices.length;
		int[][] dp = new int[len][len];
		for(int i = len - 1; i >= 0; i--) {
			dp[i][i] = 0;
			for(int j = i + 1; j < len; j++) {
				if (prices[j] > prices[i]) {
					int profit = prices[j] - prices[i];
					dp[i][j] = Math.max(profit, Math.max(dp[i+1][j], dp[i][j-1]));
				}else {
					dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
				}
			}
			
		}
		
		return dp[0][len-1];
	}
	//动态规划：一层for循环：因为我要用一个变量来存储目前见过的最低价格，每到一天都可以更新这个变量，有了这个最低价格我就知道每一天的最大可能盈利了
	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length <= 0) {
			return 0;
		}
		int minPrices = prices[0];
		int maxProfit = 0;
		for(int i = 0; i < prices.length; i++) {
			if (prices[i] < minPrices) {
				minPrices = prices[i];
			}
			if (prices[i] - minPrices > maxProfit) {
				maxProfit = prices[i] - minPrices;
			}
		}
		return maxProfit;
	}
	
	public static void main(String[] args) {
		int[] prices = {7,1,5,3,6,4};
		System.out.println(maxProfit(prices));
		
		int[] prices1 = {7,6,4,3,1};
		System.out.println(maxProfit(prices1));
		
		int[] prices2 = {1,8,1,9,8};
		System.out.println(maxProfit(prices2));
	}
}
