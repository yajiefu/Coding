package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：54.螺旋矩阵
 * 描述：给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 示例 1:
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * 输入：[
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 * @author yajie
 *
 */
public class SpiralOrder {
	List<Integer> list = new ArrayList<Integer>();
	public List<Integer> spiralOrder(int[][] matrix) {
		if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
			return list;
		}
		
		int m = matrix.length;
		int n = matrix[0].length;
		int min = Math.min(m, n);
		for(int start = 0; start * 2 < min; start++) {
			spiralOrder(matrix, m, n, start);
		}
		return list;
		

	}
	public List<Integer> spiralOrder(int[][] matrix, int m, int n, int start) {
		int endX = m - 1 - start;
		int endY = n - 1 - start;
		
		//上面一行，从左到右
		for(int i = start; i <= endY; i++) {
			list.add(matrix[start][i]);
		}
		//右边一列，从上到下,需要至少满足的条件：两行
		if (start < endX) {
			for(int i = start + 1; i <= endX; i++) {
				list.add(matrix[i][endY]);
			}
		}
		
		//下面一行，从右到左，需要至少满足的条件：两列两行
		if (start < endX && start < endY) {
			for(int i = endY - 1; i >= start; i--) {
				list.add(matrix[endX][i]);
			}
		}
		
		//左边一列，从下到上，至少需要满足的条件：三行两列
		if (start < endX - 1 && start < endY) {
			for(int i = endX - 1; i >= start + 1; i--) {
				list.add(matrix[i][start]);
			}
		}
		return list;
		
	}

}
