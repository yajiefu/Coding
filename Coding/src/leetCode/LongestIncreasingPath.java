package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：329.矩阵中的最长递增路径
 * 描述：给定一个整数矩阵，找出最长递增路径的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 
 * 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 * 示例 1:
 * 输入: nums = 
 * [[9,9,4],
 *  [6,6,8],
 *  [2,1,1]] 
 *  输出: 4 
 *  解释: 最长递增路径为 [1, 2, 6, 9]。
 *  
 *  示例 2:
 *  输入: nums = 
 *  [[3,4,5],
 *   [3,2,6],
 *   [2,2,1]] 
 *   输出: 4 
 *   解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * 
 * 
 * 方法1：普通的DFS（朴素深度优先搜索）
 *      时间复杂度：O(2^(m+n))
 *      空间复杂度：O(mn) 对于每次深度优先搜索，系统栈需要O(h)空间，其中h为递归的最深深度。最坏情况下O(h)=O(mn)。
 *   该方法中有许多重复的计算
 * 方法2：优化的DFS，（记忆深度优先搜索）用一个集合来避免一次深度优先搜索中的重复访问
 *      时间复杂度:O(mn)
 *      空间复杂度：O(mn)，缓存决定了空间复杂度
 *
 * 方法3：动态规划（剥洋葱）
 * 每个细胞的结果只与相邻的结果相关，能否使用动态规划？
 * 如果我们定义从单元格(i,j) 开始的最长递增路径为函数 f(i,j)
 * 想要让动态规划有效，如果问题 B 依赖于问题 A 的结果，就必须确保问题 A 比问题 B先计算。这样的依赖顺序对许多问题十分简单自然。如著名的斐波那契数列
 * 因此，自然顺序就是正确的计算顺序。被依赖者总会先被计算。这种依赖顺序的术语是“拓扑顺序”或“拓扑排序”：
 * 
 * 在本问题中，拓扑顺序并不简单自然。没有矩阵的值，我们无法知道两个邻居 A 和 B 的依赖关系。
 * 作为预处理，我们必须显式执行拓扑排序。
 * 之后，我们可以按照存储的拓扑顺序使用状态转移函数动态地解决问题。
 * 有多种实现拓扑排序的方法。这里我们使用的是一种被称为“剥洋葱”的方法。
 * 
 * 其思路是在一个有向无环图中，会有一些不依赖于其他顶点的顶点，称为“叶子”。
 * 我们将这些叶子放在一个列表中（他们的内部排序不重要），然后将他们从图中移除。
 * 移除之后，会产生新的“叶子”。重复以上过程，就像一层一层一层地拨开洋葱的心。最后，列表中就会存储有效的拓扑排序。
 * 在本问题中，因为我们想要求出在整个图中最长的路径，也就是“洋葱”的层总数。因此，我们可以在“剥离”的期间计算层数，在不调用动态规划的情况下返回计数。
 * 
 */
public class LongestIncreasingPath {
	public static void main(String[] args) {
		int[][] matrix = { { 3, 4, 5 }, { 3, 2, 6 }, { 2, 2, 1 } };
		System.out.println(longestIncreasingPath(matrix));

		int[][] matrix1 = { { 9, 9, 4 }, { 6, 6, 8 }, { 2, 1, 1 } };
		System.out.println(longestIncreasingPath(matrix1));

		int[][] matrix2 = { { 1 } };
		System.out.println(longestIncreasingPath(matrix2));
	}

	// 方法1：DFS（超时）
	public static int longestIncreasingPath1(int[][] matrix) {
		if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
			return 0;
		}

		int rows = matrix.length;
		int cols = matrix[0].length;
		// 4个方向
		int[][] direction = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		int ans = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				ans = Math.max(ans, dfs1(matrix, rows, cols, i, j, direction));
			}
		}
		return ans;

	}

	
	public static int dfs1(int[][] matrix, int rows, int cols, int x, int y, int[][] direction) {
		int ans = 0;
		for (int i = 0; i < direction.length; i++) {
			int newX = x + direction[i][0];
			int newY = y + direction[i][1];
			// 判断是否能进入（newX,newY）
			if (newX >= 0 && newY >= 0 && newX < rows && newY < cols && matrix[newX][newY] > matrix[x][y]) {
				ans = Math.max(ans, dfs1(matrix, rows, cols, newX, newY, direction));
			}
		}
		return ++ans;
	}
	
	//方法2：DFS + 优化
	public static int longestIncreasingPath2(int[][] matrix) {
		if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
			return 0;
		}

		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] cache = new int[rows][cols];
		// 4个方向
		int[][] direction = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		int ans = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				ans = Math.max(ans, dfs(matrix, rows, cols, i, j, direction,cache));
			}
		}
		return ans;

	}

	
	public static int dfs(int[][] matrix, int rows, int cols, int x, int y, int[][] direction, int[][] cache) {
//		int ans = 0;
		if (cache[x][y] != 0) {
			return cache[x][y];
		}
		for (int i = 0; i < direction.length; i++) {
			int newX = x + direction[i][0];
			int newY = y + direction[i][1];
			// 判断是否能进入（newX,newY）
			if (newX >= 0 && newY >= 0 && newX < rows && newY < cols && matrix[newX][newY] > matrix[x][y]) {
				cache[x][y] = Math.max(cache[x][y], dfs(matrix, rows, cols, newX, newY, direction,cache));
			}
		}
		return ++cache[x][y];
	}
	
	
	//方法3：动态规划
	public static int longestIncreasingPath(int[][] matrix) {
		if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
			return 0;
		}

		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] direction = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		
		int[][] grid = new int[rows+2][cols+2];
		for(int i = 0; i < rows; i++) {
			System.arraycopy(matrix[i], 0, grid[i+1], 1, cols);
		}
		// calculate outdegrees
		int[][] outdegree = new int[rows+2][cols+2];
		for(int i = 1; i <= rows; i++) {
			for(int j = 1; j <= cols; j++) {
				for(int k = 0; k < direction.length; k++) {
					if (grid[i][j] < grid[i + direction[k][0]][j+direction[k][1]]) {
						outdegree[i][j]++;
					}
				}
			}
		}
		
		
		// find leaves who have zero out degree as the initial level
		rows += 2;
		cols += 2;
		List<int[]> leaves = new ArrayList<int[]>();
		for(int i = 1; i < rows - 1; i++) {
			for(int j = 1; j < cols - 1; j++) {
				if (outdegree[i][j] == 0) {
					leaves.add(new int[] {i,j});
				}
			}
		}
		
		// remove leaves level by level in topological order
		int height = 0;
		while(!leaves.isEmpty()) {
			height++;
			List<int[]> newLeaves = new ArrayList<int[]>();
			for (int[] node : leaves) {
				for(int k = 0; k < direction.length; k++) {
					int x = node[0] + direction[k][0];
					int y = node[1] + direction[k][1];
					if (grid[node[0]][node[1]] > grid[x][y]) {
						if (--outdegree[x][y] == 0) {
							newLeaves.add(new int[] {x,y});
						}
					}
				}
			}
			leaves = newLeaves;
		}
		
		return height;
	}
}
