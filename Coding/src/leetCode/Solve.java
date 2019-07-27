package leetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：130.被围绕的区域
 * 描述：给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 *     找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 示例:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 *     任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 *     如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *     
 *     
 * 方法1：DFS  时间复杂度：O(M*N),空间复杂度：O(1)
 * 方法2：BFS  时间复杂度：O(M*N),空间复杂度：O(min(M,N))
 * 方法3：并查集   时间复杂度：O(M*N),注意当使用路径压缩和排名（秩）结合并实现并查集时，并操作只需要消耗常数时间。
 *            空间复杂度：O(M*N))
 * 
 * 类似题：200.岛屿数量
 * @author yajie
 *
 */
public class Solve {
	//方法1：DFS
	public static void solve1(char[][] board) {
		if (board == null || board.length <= 0) {
			return;
		}
		int rows = board.length;
		int cols = board[0].length;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				// 从边界的O开始dfs
				boolean flag = (col == 0 || col == cols - 1 || row == 0 || row == rows - 1);
				if (flag && board[row][col] == 'O') {
					dfs(board, row, col);
				}
			}
		}

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (board[row][col] == 'O') {
					board[row][col] = 'X';
				}
				if (board[row][col] == '#') {
					board[row][col] = 'O';
				}
			}
		}

	}

	public static void dfs(char[][] board, int row, int col) {
		int rows = board.length;
		int cols = board[0].length;
		if (row < 0 || col < 0 || row >= rows || col >= cols || board[row][col] != 'O') {
			return;
		}
		// 将走过的'O'都置为'#'
		board[row][col] = '#';
		dfs(board, row - 1, col);
		dfs(board, row + 1, col);
		dfs(board, row, col - 1);
		dfs(board, row, col + 1);
	}

	//方法2：BFS
	public static void solve2(char[][] board) {
		if (board == null || board.length <= 0) {
			return;
		}
		int rows = board.length;
		int cols = board[0].length;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				Queue<Integer> queue = new LinkedList<Integer>();
				// 从边界的O开始dfs
				boolean flag = (col == 0 || col == cols - 1 || row == 0 || row == rows - 1);
				if (flag && board[row][col] == 'O') {
					queue.add(row * cols + col);
					board[row][col] = '#';
					while (!queue.isEmpty()) {
						int id = queue.remove();
						int r = id / cols;
						int c = id % cols;
						if (r - 1 >= 0 && board[r - 1][c] == 'O') {
							queue.add((r - 1) * cols + c);
							board[r - 1][c] = '#';
						}
						if (r + 1 < rows && board[r + 1][c] == 'O') {
							queue.add((r + 1) * cols + c);
							board[r + 1][c] = '#';
						}
						if (c - 1 >= 0 && board[r][c - 1] == 'O') {
							queue.add(r * cols + c - 1);
							board[r][c - 1] = '#';
						}
						if (c + 1 < cols && board[r][c + 1] == 'O') {
							queue.add(r * cols + c + 1);
							board[r][c + 1] = '#';
						}
					}
				}
			}
		}

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (board[row][col] == 'O') {
					board[row][col] = 'X';
				}
				if (board[row][col] == '#') {
					board[row][col] = 'O';
				}
			}
		}

	}

	// 方法3:并查集
	public static class UnionFind {
		int[] parent;
		int[] rank;

		public UnionFind(int totalNodes) {
			parent = new int[totalNodes];
			rank = new int[totalNodes];
			for (int i = 0; i < totalNodes; i++) {
				parent[i] = i;
				rank[i] = 0;
			}

		}

		public int find(int i) {
//			if(parent[i] != i) {
//				parent[i] = find(parent[i]);
//			}
			
			while(parent[i] != i) {
				parent[i] = parent[parent[i]];
				i = parent[i];
			}
			return i;
		}

		public void union(int x, int y) {
			int rootx = find(x);
			int rooty = find(y);
			if (rootx != rooty) {
//				parent[rootx] = rooty;//不用rank
				if (rank[rootx] > rank[rooty]) {
					parent[rooty] = rootx;
				} else if (rank[rooty] > rank[rootx]) {
					parent[rootx] = rooty;
				} else {
					parent[rooty] = rootx;
					rank[rootx] += 1;
				}
			}
		}
		
		public boolean isConnected(int node1, int node2) {
			return find(node1) == find(node2);
		}

	}

	public static void solve(char[][] board) {
		if (board == null || board.length <= 0) {
			return;
		}
		int rows = board.length;
		int cols = board[0].length;

		UnionFind uf = new UnionFind(rows * cols + 1);

		//使用一个虚拟节点，边界上的O都与这个虚拟节点相连。
		int dummyNode = rows * cols;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				// 从边界开始
				if (board[row][col] == 'O') {
					if (col == 0 || col == cols - 1 || row == 0 || row == rows - 1) {
						uf.union(row*cols+col, dummyNode);
					}else {
						if (row + 1 < rows && board[row + 1][col] == 'O') {
							uf.union(row * cols + col, (row + 1) * cols + col);
						}
						if (row - 1 >= 0 && board[row - 1][col] == 'O') {
							uf.union(row * cols + col, (row - 1) * cols + col);
						}
						if (col + 1 < cols && board[row][col + 1] == 'O') {
							uf.union(row * cols + col, row * cols + col + 1);
						}
						if (col - 1 >= 0 && board[row][col - 1] == 'O') {
							uf.union(row * cols + col, row * cols + col - 1);
						}
					}
				}

			}
		}
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (uf.isConnected(dummyNode, row*cols+col)) {
					board[row][col] = 'O';
				}else {
					board[row][col] = 'X';
				}
			}
		}

	}

	public static void main(String[] args) {
		char[][] board = { { 'O', 'X', 'O', 'O' }, 
		                   { 'O', 'X', 'O', 'X' }, 
		                   { 'O', 'O', 'X', 'O'} };

		solve(board);
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
	}
}
