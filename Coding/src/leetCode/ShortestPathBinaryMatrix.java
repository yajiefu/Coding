package leetCode;

import java.util.Deque;
import java.util.LinkedList;

/**
 *题目：1091.二进制矩阵中的最短路径
 *描述：在一个 N × N 的方形网格中，每个单元格有两种状态：空（0）或者阻塞（1）。
 *一条从左上角到右下角、长度为 k 的畅通路径，由满足下述条件的单元格 C_1, C_2, ..., C_k 组成：
 *相邻单元格 C_i 和 C_{i+1} 在八个方向之一上连通（此时，C_i 和 C_{i+1} 不同且共享边或角）
 *C_1 位于 (0, 0)（即，值为 grid[0][0]）
 *C_k 位于 (N-1, N-1)（即，值为 grid[N-1][N-1]）
 *如果 C_i 位于 (r, c)，则 grid[r][c] 为空（即，grid[r][c] == 0）
 *返回这条从左上角到右下角的最短畅通路径的长度。如果不存在这样的路径，返回 -1 。
 *示例 1：输入：[[0,1],[1,0]]输出：2
 *示例 2：输入：[[0,0,0],[1,1,0],[1,1,0]]输出：4
 *提示：
 *1 <= grid.length == grid[0].length <= 100
 *grid[i][j] 为 0 或 1
 *
 *
 *
 *思路：BFS
 *
 *疑问：为什么直接计算出来的就是最短路径呢？
 *因为我们用step[]存放了到达这个点的步数。
 *
 *0 0 0
 *1 1 0
 *1 1 0
 *当走到(0,1)时，队列中可以存放下一步可走的(0,2)和(1,2),步数都是3。
 *当我们先走(0,2)后，接下来，我们就不能把(1,2)放进去了。想想，如果进去的话，达到(1,2)的步数就是4了，明显这种方法路径更长。
 *
 *
 *拓展：怎么求最长路径.看329：矩阵中的最长递增路径
 */
public class ShortestPathBinaryMatrix {
	public static void main(String[] args) {
		int[][] grid = {{0,1,0,0,0},{1,0,1,1,0},{1,0,0,1,0}};
		System.out.println(shortestPathBinaryMatrix(grid));
	}
	public static int shortestPathBinaryMatrix(int[][] grid) {
		if (grid == null || grid.length <= 0 || grid[0].length <= 1) {
			return -1;
		}

		int rows = grid.length;
		int cols = grid[0].length;
		//如果左上角是1，直接不可达
		if (grid[0][0] == 1) {
			return -1;
		}
		
		Deque<Integer> deque = new LinkedList<Integer>();
		//添加第一个元素
		deque.addLast(0);//i*cols+j
		//
		int[][] step = new int[rows][cols];
		step[0][0] = 1;
		
		//8个方向(x,y)
		int[][] direction = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
		while(!deque.isEmpty()) {
			int temp = deque.removeFirst();
			int row = temp / cols;
			int col = temp % cols;
			
			//判断8个方向
			for(int i = 0; i < direction.length; i++) {
				int newRow = row + direction[i][0];
				int newCol = col + direction[i][1];
				//判断是否可以进入新坐标
				if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == 0 && step[newRow][newCol] == 0) {
					
					deque.addLast(newRow * cols + newCol);
					step[newRow][newCol] = step[row][col] + 1;
					//判断是否到达右下角
					if (newRow == rows - 1 && newCol == cols - 1) {
						return step[newRow][newCol];
					}
				}
				
			}
			
		}
		return -1;
	}
	
}
