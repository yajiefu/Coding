package packageProblems;
/**
 * 第一讲：01背包问题           最基本的背包问题，每个物品最多只能放一次
 * 
 * 题目：有N件物品和一个容量为C(总重量)的背包。第i件物品的重量是w[i]，价值是v[i]。求解将哪些物品装入背包可使价值总和最大。
 * 
 * 特点：每种物品仅有一件，可以选择放或不放
 * @author yajie
 *
 */
public class ZeroOnePack {


	/*
	 * dp[i][j]定义为：前i个物品恰好放入一个容量为j的背包可以获得的最大价值
	 * “将前i件物品放入容量为c的背包中”这个子问题，若只考虑第i件物品的策略（放或不放），那么就可以转化为一个只牵扯前i-1件物品的问题。
	 * 如果不放第i件物品，那么问题就转化为“前i-1件物品放入容量为j的背包中”，价值为dp[i-1][j]；
	 * 如果放第i件物品，那么问题就转化为“前i-1件物品放入剩下的容量为j-w[i]的背包中”，此时能获得的最大价值就是dp[i-1][j-w[i]]再加上通过放入第i件物品获得的价值v[i]。
	 * 
	 * dp[i][j]的状态转移方程是： 
	 * (1)当j<w[i]时，dp[i][j] = dp[i-1][j]
	 * (2)当j>=w[i]时，dp[i][j] = max{dp[i-1][j], dp[i-1][j-w[i]]+v[i]}
	 * 
	 * 
	 * 时间复杂度: O(NC)
	 * 空间复杂度：O(NC)
	 */
	public static int package01_2(int n, int[] w, int[] v, int c) {
		int[][] dp = new int[n + 1][c + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= c; j++) {
				if (j < w[i]) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
				}
			}
		}
		return dp[n][c];
	}

	
	/*
	 * 二维的动态规划的时间复杂度和空间复杂度都为O(nc),其中时间复杂度基本不能优化，空间复杂度却可以优化到O(C)
	 * 先考虑上面讲的基本思路如何实现，肯定是有一个主循环i=1..c，每次算出来二维数组dp[i][0..c]的所有值。
	 * 那么，如果只用一个数组dp[0..c]，能不能保证第i次循环结束后dp[c]中表示的就是我们定义的状态dp[i][c]呢？
	 * 
	 * dp[i][c]是由dp[i-1][c]和dp[i-1][c-w[i]]两个子问题递推而来，
	 * 能否保证在推dp[i][c]时（也即在第i次主循环中推dp[c]时）能够得到dp[i-1][c]和dp[i-1][c-w[i]]的值呢？
	 * 事实上，这要求在每次主循环中我们以j=c..0的顺序推dp[c]，这样才能保证推dp[c]时dp[c-w[i]]保存的是状态dp[i-1][c-w[i]]的值。
	 * 如果将j的循环顺序从上面的逆序改成顺序的话，那么则成了dp[i][j]由dp[i][j-w[i]]推知
	 */
	public static int package01_1(int n, int[] w, int[] v, int c) {
		int[] dp = new int[c + 1];
		for (int i = 1; i <= n; i++) {
			//这里的j需要倒序
			for (int j = c; j >= 0; j--) {
				if (j < w[i]) {
					dp[j] = dp[j];
				} else {
					dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
				}
			}
		}
		return dp[c];
	}

	public static void main(String[] args) {
		int n = 5;
		int[] w = { 0, 2, 2, 6, 5, 4 };
		int[] v = { 0, 6, 3, 5, 4, 6 };
		int c = 10;

		int res1 = package01_1(n, w, v, c);
		System.out.println(res1);
		int res2 = package01_2(n, w, v, c);
		System.out.println(res2);
	}

}
