package leetCode;
/**
 * 题目：63.不同路径II
 * 相比62.不同路径 多了障碍物
 * 方法1：迷宫的通常解法：超过时间限制
 * 方法2：动态规划
 *    时间复杂度：O(mn)
 *    空间复杂度:O(mn)
 *    可以将obstacleGrid作为dp数组，空间复杂度O(1)
 * @author yajie
 *
 */
public class UniquePathsII {
	// 动态规划
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid == null) {
			return 0;
		}
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
			return 0;
		}

//		int[][] dp = new int[m][n];
//		dp[m-1][n-1] = 1;
		obstacleGrid[m - 1][n - 1] = 1;
		for (int i = m - 2; i >= 0; i--) {
//			dp[i][n-1] = obstacleGrid[i][n-1] == 0 && dp[i+1][n-1] == 1 ? 1:0;
			obstacleGrid[i][n - 1] = obstacleGrid[i][n - 1] == 0 && obstacleGrid[i + 1][n - 1] == 1 ? 1 : 0;

		}
		for (int j = n - 2; j >= 0; j--) {
//			dp[m-1][j] = obstacleGrid[m-1][j] == 0 && dp[m-1][j+1] == 1 ? 1:0;
			obstacleGrid[m - 1][j] = obstacleGrid[m - 1][j] == 0 && obstacleGrid[m - 1][j + 1] == 1 ? 1 : 0;
		}
		for (int i = m - 2; i >= 0; i--) {
			for (int j = n - 2; j >= 0; j--) {
				if (obstacleGrid[i][j] == 1) {
//					dp[i][j] = 0;
					obstacleGrid[i][j] = 0;
				} else {
//					dp[i][j] = dp[i+1][j] + dp[i][j+1];
					obstacleGrid[i][j] = obstacleGrid[i + 1][j] + obstacleGrid[i][j + 1];
				}
			}
		}
//		return dp[0][0];
		return obstacleGrid[0][0];
	}

	// 方法1：迷宫的通常解法：超过时间限制
	public static int uniquePathsWithObstacles1(int[][] obstacleGrid) {
		if (obstacleGrid == null) {
			return 0;
		}
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
			return 0;
		}

		int[][] cache = new int[m][n];
		int[][] direction = { { 1, 0 }, { 0, 1 } };

		return dfs(obstacleGrid, m, n, 0, 0, cache, direction);

	}

	public static int dfs(int[][] obstacleGrid, int m, int n, int i, int j, int[][] cache, int[][] direction) {
		if (i == m - 1 && j == n - 1) {
			cache[i][j] = 1;
			return cache[i][j];
		}
		if (cache[i][j] != 0) {
			return cache[i][j];
		}

		int ans = 0;
		for (int k = 0; k < direction.length; k++) {
			int x = i + direction[k][0];
			int y = j + direction[k][1];
			if (x >= 0 && x <= m - 1 && y >= 0 && y <= n - 1 && obstacleGrid[x][y] == 0) {
				ans += dfs(obstacleGrid, m, n, x, y, cache, direction);
			}
		}
		return cache[i][j] = ans;
	}

	public static void main(String[] args) {
//		int[][] map = {{0,0,0},{0,1,0},{0,0,0}};
//		int[][] map = {{0,0,0,0},{1,0,1,0},{0,0,0,0},{0,0,0,0}};
//		int[][] map = {{1}};
		int[][] map = { { 0 }, { 0 } };
//		int[][] map = {{0,0},{1,1},{0,0}};
		System.out.println(uniquePathsWithObstacles1(map));
		System.out.println(uniquePathsWithObstacles(map));

	}
}
