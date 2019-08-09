package leetCode;

import java.util.Arrays;

/**
 * 题目：322.零钱兑换
 * 描述：给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 *           如果没有任何一种硬币组合能组成总金额，返回 -1
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3 
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 
 * 2019.8.8趋势提前批第一题：题目变种，硬币不是无限的，有个数限制。
 * @author yajie
 *
 */
public class CoinChange {
	// 1.二维动态规划：空间复杂度：O(MN)
	// 这是我第一遍自己做的时候写的，因为-1的存在所以写的略微繁琐
	public static int coinChange1(int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		}
		if (coins == null) {
			return -1;
		}

		int[][] dp = new int[coins.length][amount + 1];
		// 先填充第一行
		for (int j = 0; j <= amount; j++) {
			if (j < coins[0]) {
				dp[0][j] = -1;
			} else if (j == coins[0]) {
				dp[0][j] = 1;
			} else {
				dp[0][j] = dp[0][j - coins[0]] == -1 ? -1 : dp[0][j - coins[0]] + 1;
			}
		}
		for (int i = 1; i < coins.length; i++) {
			for (int j = 0; j <= amount; j++) {
				if (j < coins[i]) {
					dp[i][j] = dp[i - 1][j];
				} else if (j == coins[i]) {
					dp[i][j] = 1;
				} else {// 应该是两个一起对比dp[i][j-coins[i]] dp[i-1][j]
					if (dp[i][j - coins[i]] != -1 && dp[i - 1][j] != -1) {
						dp[i][j] = Math.min(dp[i][j - coins[i]] + 1, dp[i - 1][j]);
					} else if (dp[i][j - coins[i]] != -1) {
						dp[i][j] = dp[i][j - coins[i]] + 1;
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}
		}
		return dp[coins.length - 1][amount];
	}

	// 二维动态规划。修改了一下，主要是避免-1造成的困扰
	public static int coinChange2(int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		}
		if (coins == null) {
			return -1;
		}

		int[][] dp = new int[coins.length][amount + 1];
		// 先填充第一行
		for (int j = 1; j <= amount; j++) {// 不要填充j=0,当j-coins[i]==0时，需要用dp[0][0]+1的
			dp[0][j] = Integer.MAX_VALUE;
			if (j - coins[0] >= 0 && dp[0][j - coins[0]] != Integer.MAX_VALUE) {
				dp[0][j] = dp[0][j - coins[0]] + 1;
			}
		}
		for (int i = 1; i < coins.length; i++) {
			for (int j = 0; j <= amount; j++) {
				int temp = Integer.MAX_VALUE;// 以防止下面条件不成立
				if (j - coins[i] >= 0 && dp[i][j - coins[i]] != Integer.MAX_VALUE) {
					temp = dp[i][j - coins[i]] + 1;
				}
				dp[i][j] = Math.min(temp, dp[i - 1][j]);
			}
		}
		return dp[coins.length - 1][amount] == Integer.MAX_VALUE ? -1 : dp[coins.length - 1][amount];
	}

	// 一维动态规划
	public static int coinChange3(int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		}
		if (coins == null) {
			return -1;
		}

		// dp[j]表示，当amount为j时的最小组合数
		int[] dp = new int[amount + 1];
		// 先填充第一行
//		for (int j = 1; j <= amount; j++) {// 不要填充j=0,当j-coins[i]==0时，需要用dp[0]+1的
//			dp[j] = Integer.MAX_VALUE;
//			if (j - coins[0] >= 0 && dp[j - coins[0]] != Integer.MAX_VALUE) {
//				dp[j] = dp[j - coins[0]] + 1;
//			}
//		}
		// 如果是上面的代码：下面for循环里i=1开始
		Arrays.fill(dp, 1, amount + 1, Integer.MAX_VALUE);
		for (int i = 0; i < coins.length; i++) {
			for (int j = coins[i]; j <= amount; j++) {// j = coins[i]开始
				if (dp[j - coins[i]] != Integer.MAX_VALUE) {
					dp[j] = Math.min(dp[j - coins[i]] + 1, dp[j]);
				}

			}
		}
		return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];

	}

	public static void main(String[] args) {
		int[] coins1 = {1,2,5};
		int amount1 = 11;
		
		int[] coins2 = {186,419,83,408};
		int amount2 = 6249;
		
		int[] coins3 = { 2, 5, 10, 1 };
		int amount3 = 27;

		System.out.println(coinChange1(coins1, amount1));
		System.out.println(coinChange1(coins2, amount2));
		System.out.println(coinChange1(coins3, amount3));
		
		System.out.println(coinChange2(coins1, amount1));
		System.out.println(coinChange2(coins2, amount2));
		System.out.println(coinChange2(coins3, amount3));
		
		System.out.println(coinChange3(coins1, amount1));
		System.out.println(coinChange3(coins2, amount2));
		System.out.println(coinChange3(coins3, amount3));
	}
}
