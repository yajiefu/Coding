package leetCode;

import java.util.Arrays;


/*
 * 题目：62.不同路径
 * 描述：一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 * 
 * 思路：求(0,0)-->(m-1,n-1)的路径
 * 方法1：普通的DFS，超出时间限制，因为该方法有许多重复的计算
 *    时间复杂度：O(2^(m+n))
 *    空间复杂度：对于每次深度优先搜索，系统栈需要O(h)空间，其中h为递归的最深深度。最坏情况下O(h)=O(mn)
 *    
 * 方法2：优化的DFS  记忆深度优先搜索
 * 时间复杂度：O(mn)
 * 空间复杂度：O(mn)
 * (方法2通用解决迷宫问题)
 * 方法3：动态规划。根据方法2也能看得出来
 * dp[i][j]=dp[i+1][j]+dp[i][j+1]
 * 时间复杂度：O(mn)
 * 空间复杂度：O(mn)
 * 
 * 动态规划还可以优化
 */
public class UniquePaths {
	// 方法1：普通的DFS（超出时间限制）
	public static int uniquePaths1(int m, int n) {
		return dfs1(m, n, 0, 0);
	}

	public static int dfs1(int m, int n, int i, int j) {
		if (i == m - 1 && j == n - 1) {
			return 1;
		}
		int n1 = 0;
		int n2 = 0;
		// 向右探索
		if (i + 1 <= m - 1) {
			n1 = dfs1(m, n, i + 1, j);
		}
		// 向下探索
		if (j + 1 <= n - 1) {
			n2 = dfs1(m, n, i, j + 1);
		}
		return n1 + n2;
	}

	// 方法2：优化的DFS 记忆深度优先搜索
	// 移动方向我们可以改成二维数组direction来表示
	public static int uniquePaths2(int m, int n) {
		int[][] cache = new int[m][n];
		int[][] direction = { { 1, 0 }, { 0, 1 } };
		return dfs2(m, n, 0, 0, cache, direction);
	}

	public static int dfs2(int m, int n, int i, int j, int[][] cache, int[][] direction) {
		if (i == m - 1 && j == n - 1 && cache[i][j] == 0) {
			cache[i][j] = 1;
			return cache[i][j];
		}
		if (cache[i][j] != 0) {
			// 表示已经走过了
			return cache[i][j];
		}
		int ans = 0;
		for (int k = 0; k < direction.length; k++) {
			int x = i + direction[k][0];
			int y = j + direction[k][1];
			if (x >= 0 && x <= m - 1 && y >= 0 && y <= n - 1) {
				ans += dfs2(m, n, x, y, cache, direction);
			}

		}
		cache[i][j] = ans;
		return cache[i][j];
	}
	//方法3：动态规划
	public static int uniquePaths3(int m, int n) {
		int[][] dp = new int[m][n];
		for(int i = 0; i < m; i++) {
			dp[i][n-1] = 1;
		}
		for(int j = 0; j < n; j++) {
			dp[m-1][j] = 1;
		}
		
		for(int i = m - 2; i >= 0; i--) {
			for(int j = n - 2; j >= 0; j--) {
				dp[i][j] = dp[i+1][j] + dp[i][j+1]; 
			}
		}
		
		return dp[0][0];
	}
	//动态规划优化
	public static int uniquePaths4(int m, int n) {
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		
		for(int i = m - 2; i >= 0; i--) {
			for(int j = n - 2; j >= 0; j--) {
				dp[j] += dp[j+1]; 
			}
		}
		
		return dp[0];
	}
	public static void main(String[] args) {
		int m = 1;
		int n = 2;
		System.out.println(uniquePaths1(m, n));
		System.out.println(uniquePaths2(m, n));
		System.out.println(uniquePaths3(m, n));
		System.out.println(uniquePaths4(m, n));
	}
}
