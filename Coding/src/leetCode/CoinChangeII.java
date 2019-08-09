package leetCode;


/**
 * 题目：518.零钱兑换II
 * 描述：给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 * 示例 1:
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 示例 2:
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 * 示例 3:
 * 输入: amount = 10, coins = [10] 
 * 输出: 1
 * 注意:
 * 你可以假设：
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额) <= 5000
 * 硬币种类不超过 500 种
 * 结果符合 32 位符号整数
 * 
 * 动态规划：二维数组
 *  或者一维数组
 * @author yajie
 *
 */
public class CoinChangeII {
	//dp[j]表示凑成金额j的组合
	public static int change(int amount, int[] coins) {
		if (amount == 0) {
			return 1;
		}
		if (coins == null || coins.length == 0) {
			return 0;
		}
		int len = coins.length;
		int[] dp = new int[amount+1];
		dp[0] = 1;
 		for(int i = 0; i < len; i++) {
			for(int j = 0; j <= amount; j++) {
				if (j >= coins[i]) {
					dp[j] = dp[j] + dp[j - coins[i]];
				}
			}
		}
		return dp[amount];
	}
	//dp[][]
	public static int change1(int amount, int[] coins) {
		if (amount == 0) {
			return 1;
		}
		if (coins == null || coins.length == 0) {
			return 0;
		}
		int len = coins.length;

		// dp[i][j]：用硬币的前i个可以凑成金额j的个数
		int[][] dp = new int[len + 1][amount + 1];
		// 初始化第一行
		for (int j = 0; j <= amount; j++) {
			if (j % coins[0] == 0) {
				dp[0][j] = 1;
			} else {
				dp[0][j] = 0;
			}
		}
		// 初始化第一列，都应该是1，方便计算
		for (int i = 0; i < len; i++) {
			dp[i][0] = 1;
		}

		for (int i = 1; i < len; i++) {
			for (int j = 0; j <= amount; j++) {
				if (j < coins[i]) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = dp[i][j - coins[i]] + dp[i - 1][j];
				}

			}
		}
		return dp[len - 1][amount];
	}

	public static void main(String[] args) {
		int[] coins = {1,2,5};
		System.out.println(change(5, coins));
	}
}
