package packageProblems;
/**
 * 第二讲：完全背包问题   
 * 
 * 题目：有N种物品和一个容量(总重量)为C的背包，每种物品都有无限件可用。第i种物品的重量是w[i]，价值是v[i]。
 *            求解将哪些物品装入背包可使这些物品的总重量总和不超过背包容量，且价值总和最大。
 *            
 * 思路：完全背包问题和01背包问题的区别在于每种物品是有无限件的。也就是说与它的相关策略易并非取或不取两种，而是有0，1.……等很多种。
 *            如果仍然按照01背包的思路，令dp[i][j]表示前i种物品恰好放入一个容量为j的背包的最大价值。
 *   可以写出状态转移方程
 *   dp[i][j] = max{dp[i-1][j-k*w[i]]+k*v[i]}   0<=k*w[i]<=j
 *      
 * 0-1背包和完全背包的不同：
 * 从二维数组上区别0-1背包和完全背包也就是状态转移方程就差别在放第i中物品时，完全背包在选择放这个物品时，最优解是dp[i][j-w[i]]+v[i]即画表格中同行的那一个，而0-1背包比较的是dp[i-1][j-w[i]]+v[i]，上一行的那一个。
 * 从一维数组上区别0-1背包和完全背包差别就在循环顺序上，0-1背包必须逆序，因为这样保证了不会重复选择已经选择的物品，而完全背包是顺序，顺序会覆盖以前的状态，所以存在选择多次的情况，也符合完全背包的题意。状态转移方程都为dp[j] = max(dp[j],dp[j-w[i]]+v[i])。
 * 
 */
public class CompletePack {
	public static int completePackage1(int n, int[] w, int[] v, int c) {
		int[][] dp = new int[n + 1][c + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= c; j++) {
				for (int k = 0; k <= j / w[i]; k++) {
					if (j < w[i]) {
						dp[i][j] = dp[i - 1][j];
					} else {
						dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * w[i]] + k * v[i]);
					}
				}
			}
		}
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= c; j++) {
				System.out.print(dp[i][j] + "  ");
			}
			System.out.println();
		}
		return dp[n][c];

	}

	// 转化成01背包问题dp[][]
	public static int completePackage2(int n, int[] w, int[] v, int c) {
		int[][] dp = new int[n + 1][c + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= c; j++) {////注意此处，与0-1背包不同，这里为顺序，0-1背包为逆序
				if (j < w[i]) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i][j], dp[i][j - w[i]] + v[i]);
				}
			}
		}
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= c; j++) {
				System.out.print(dp[i][j] + "  ");
			}
			System.out.println();
		}
		return dp[n][c];

	}

	// 转化成01背包问题：dp[]
	public static int completePackage3(int n, int[] w, int[] v, int c) {
		int[] dp = new int[c + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= c; j++) {
				if (j < w[i]) {
					dp[j] = dp[j];
				} else {
					dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
				}
			}
		}
		for (int j = 0; j <= n; j++) {
			System.out.print(dp[j] + "  ");
		}
		System.out.println();
		return dp[c];

	}

	public static void main(String[] args) {
		int n = 5;
		int[] w = { 0, 2, 2, 6, 5, 4 };
		int[] v = { 0, 2, 3, 5, 4, 7 };
		int c = 10;
		System.out.println("***************************");
		int res1 = completePackage1(n, w, v, c);
		System.out.println(res1);
		System.out.println("***************************");
		int res2 = completePackage2(n, w, v, c);
		System.out.println(res2);
		System.out.println("***************************");
		int res3 = completePackage3(n, w, v, c);
		System.out.println(res3);
	}

}
