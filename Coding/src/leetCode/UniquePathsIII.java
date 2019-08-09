package leetCode;

/**
 * 题目：980.不同路径III
 * 在二维网格 grid 上，有 4 种类型的方格：
 * 1 表示起始方格。且只有一个起始方格。
 * 2 表示结束方格，且只有一个结束方格。
 * 0 表示我们可以走过的空方格。
 * -1 表示我们无法跨越的障碍。
 * 返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目，每一个无障碍方格都要通过一次。
 * 
 * 示例 1：
 * 输入：[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * 输出：2
 * 解释：我们有以下两条路径：
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * 示例 2：
 * 输入：[[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * 输出：4
 * 解释：我们有以下四条路径： 
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * 示例 3：
 * 输入：[[0,1],[2,0]]
 * 输出：0
 * 解释：
 * 没有一条路能完全穿过每一个空的方格一次。
 * 请注意，起始和结束方格可以位于网格中的任意位置。 
 * 提示：1 <= grid.length * grid[0].length <= 20
 * 
 * 
 * 思路：回溯法（要掌握呀）
 * 时间复杂度：O(4^(mn))
 * 空间复杂度：O(mn)
 * 
 * 
 * 
 * @author yajie
 *
 */
public class UniquePathsIII {
	//回溯法
	public static int uniquePathsIII(int[][] grid) {
		if (grid == null) {
			return 0;
		}
		int m = grid.length;
		int n = grid[0].length;

		int[][] direction = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };
		// 起始位置
		int si = 0;
		int sj = 0;
		// 终点位置
		int ei = 0;
		int ej = 0;

		int todo = 0;//需要走的0的个数
		int count = 0;//已经走的0的个数
		// 找到起始方格
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					si = i;
					sj = j;
				} else if (grid[i][j] == 0) {
					todo++;
				} else if (grid[i][j] == 2) {
					ei = i;
					ej = j;
				} else {
					continue;
				}
			}
		}
		// 网格， 需要走0的个数，行，列，开始i,开始j,结束i,结束j,方向，已走0的个数
		count = dfs(grid, todo, m, n, si, sj, ei, ej, direction, 0);
		return count;

	}

	public static int dfs(int[][] grid, int todo, int m, int n, int i, int j, int ei, int ej, int[][] direction,
			int count) {
		int ans = 0;
		if (i == ei && j == ej) {// 到达终点
			return todo == count-1? 1 : 0;
		}
		// 走过i,j
		grid[i][j] = -1;// 3表明走过
		for (int k = 0; k < direction.length; k++) {
			int x = i + direction[k][0];
			int y = j + direction[k][1];
			if (x >= 0 && x < m && y >= 0 && y < n && (grid[x][y] == 0 || grid[x][y] == 2)) {
				// 可以进入该坐标
				ans += dfs(grid, todo, m, n, x, y, ei, ej, direction, count + 1);
			}
		}
		// 回溯
		grid[i][j] = 0;
		return ans;
	}


	public static void main(String[] args) {
		int[][] grid = { { 1, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 2, -1 } };
		System.out.println(uniquePathsIII(grid));
	}
}
