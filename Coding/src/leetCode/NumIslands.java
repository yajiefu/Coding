package leetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：200.岛屿数量
 * 描述：给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 *     一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 * 
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 * 
 * 方法1：DFS深度优先搜索
 * 线性扫描整个二维网格，如果一个结点包含 1，则以其为根结点启动深度优先搜索。
 * 在深度优先搜索过程中，每个访问过的结点被标记为 0。计数启动深度优先搜索的根结点的数量，即为岛屿的数量。
 * 
 * 时间复杂度 : O(M×N)，其中 M和 N分别为行数和列数。
 * 空间复杂度 : 最坏情况下为O(M×N)，此时整个网格均为陆地，深度优先搜索的深度达到M×N。
 * 
 * 
 * 方法2：BFS广度优先搜索
 * 线性扫描整个二维网格，如果一个结点包含 1，则以其为根结点启动广度优先搜索。将其放入队列中，并将值设为 0 以标记访问过该结点。
 * 迭代地搜索队列中的每个结点，直到队列为空。
 * 
 * 时间复杂度：O(M×N)，其中 M和 N分别为行数和列数。
 * 空间复杂度：O(min(M,N))
 * 
 * 方法3：并查集的一个超萌的解释：https://blog.csdn.net/niushuai666/article/details/6662911
 *      https://www.cnblogs.com/MrSaver/p/9607552.html
 *      
 *      优化：带有路径压缩的查找方法； 按秩合并；
 * 时间复杂度：O(M*N)  注意当使用路径压缩和排名（秩）结合并实现并查集时，并操作只需要消耗常数时间。
 * 空间复杂度：O(M*N)
 * 
 * 
 * 这一题中，推荐用深度，leetcode显示比较快。
 * @author yajie
 *
 */
public class NumIslands {
	//方法1：DFS深度优先搜索 
	public static int numIslands1(char[][] grid) {
		if (grid == null ||grid.length <= 0) {
			return 0;
		}
		int rows = grid.length;
		int cols = grid[0].length;
		int numOfLands = 0;
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				if (grid[row][col] == '1') {
					numOfLands++;
					dfs(grid, row, col);
				}
			}
		}
		return numOfLands;

	}
	
	public static void dfs(char[][] grid, int row, int col) {
		int rows = grid.length;
		int cols = grid[0].length;
		if (row < 0 || col < 0 || row >= rows || col >= cols || grid[row][col] == '0') {
			return;
		}
		//每个访问的节点都被标记为0
		grid[row][col] = '0';
		dfs(grid, row-1, col);
		dfs(grid, row+1, col);
		dfs(grid, row, col-1);
		dfs(grid, row, col+1);
	}
	
	//方法2：BFS广度优先搜索 
	public static int numIslands2(char[][] grid) {
		if (grid == null || grid.length <= 0) {
			return 0;
		}
		int rows = grid.length;
		int cols = grid[0].length;
		int numOfLands = 0;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (grid[row][col] == '1') {
					numOfLands++;
					grid[row][col] = '0';// 访问过的节点均置为0
					Queue<Integer> queue = new LinkedList<>();
					queue.add(row * cols + col);

					while (!queue.isEmpty()) {
						int id = queue.remove();
						int r = id / cols;
						int c = id % cols;

						if (r - 1 >= 0 && grid[r - 1][c] == '1') {
							queue.add((r - 1) * cols + c);
							grid[r - 1][c] = '0';
						}
						if (r + 1 < rows && grid[r + 1][c] == '1') {
							queue.add((r + 1) * cols + c);
							grid[r + 1][c] = '0';
						}
						if (c - 1 >= 0 && grid[r][c - 1] == '1') {
							queue.add(r * cols + c - 1);
							grid[r][c - 1] = '0';
						}
						if (c + 1 < cols && grid[r][c + 1] == '1') {
							queue.add(r * cols + c + 1);
							grid[r][c + 1] = '0';
						}
					}
				}

			}
		}
		return numOfLands;

	}
	
	//方法3：并查集 
	//主要有两个函数：
	//find():确定元素属于哪一个子集，这个方法就是不断的向上查找找到它的根节点，可以用来确定他们是否是同一个子集
	public static class UnionFind{
		int count;// 连同区域的个数
		int[] parent;
		int[] rank;
		//初始化：每一块为1的地方都设定为单独的个体，上级就是它自己。所有的节点rank刚开始都为0；
		public UnionFind(char[][] grid) {
			count = 0;
			int rows = grid.length;
			int cols = grid[0].length;
			parent = new int[rows * cols];
			rank = new int[rows * cols];
			for(int row = 0; row < rows; row++) {
				for(int col = 0; col < cols; col++) {
					if (grid[row][col] == '1') {
						parent[row * cols + col] = row * cols + col;
						count++;
					}
					rank[row * cols + col] = 0;
				}
			}
		}
		//带有路径压缩的查找操作:寻找掌门人，即寻找根节点
		//为了进一步减短查找路径，可以使查找路径中的每一个节点都指向根结点，这就是路径压缩
		public int find(int i) {
			if (parent[i] != i) {
				parent[i] = find(parent[i]);
			}
			return parent[i];
		}
		
		//合并。优化方法：按秩合并，即rank,总是将更小的树连接至更大的树上。单元素的树的秩定义为0，当两棵秩同为r的树联合时，它们的秩r+1
		public void union(int x, int y) {
			int rootx = find(x);
			int rooty = find(y);
//			parent[rootx] = rooty;//这个是最基础的合并。这里我们是做了优化：按秩优化
			if (rootx != rooty) {
				if (rank[rootx] > rank[rooty]) {
					parent[rooty] = rootx;
				}else if (rank[rootx] < rank[rooty]) {
					parent[rootx] = rooty;
				}else {
					parent[rooty] = rootx;
					rank[rootx] += 1;//rootx的等级上升一级
				}
				count--;//两个区域合并，区域个数减少1个。
			}
		}
		
		public int getCount() {
			return count;
		}
	}
	
	public static int numIslands(char[][] grid) {
		if (grid == null || grid.length <= 0) {
			return 0;
		}
		int rows = grid.length;
		int cols = grid[0].length;
		
		UnionFind uf = new UnionFind(grid);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (grid[r][c] == '1') {
					//注释掉的两个，其实和上一行的+1重复了。
//					if (r - 1 >= 0 && grid[r - 1][c] == '1') {
//						uf.union(r*cols+c, (r-1)*cols+c);//合并
//					}
					if (r + 1 < rows && grid[r + 1][c] == '1') {
						uf.union(r*cols+c, (r+1)*cols+c);//合并
					}
//					if (c - 1 >= 0 && grid[r][c - 1] == '1') {
//						uf.union(r*cols+c, r*cols+c-1);//合并
//					}
					if (c + 1 < cols && grid[r][c + 1] == '1') {
						uf.union(r*cols+c, r*cols+c+1);//合并
					}
				}
			}

		}
		return uf.getCount();
	}
	
	
	
	public static void main(String[] args) {
		char[][] grid = {{'1','1','0','1'},
				         {'1','1','0','1'},
				         {'0','0','0','0'},
				         {'1','0','1','0'},
				         {'1','0','1','1'}
				         };
		System.out.println(numIslands(grid));
	}
}
