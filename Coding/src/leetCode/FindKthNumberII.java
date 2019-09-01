package leetCode;


/**
 * 题目：378.有序矩阵中第k小的元素
 * 描述：给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
 * 请注意，它是排序后的第k小元素，而不是第k个元素。
 * 示例:
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]],
 *    k = 8,
 *    返回 13。
 *    说明: 
 *    你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n^2 。
 * 一般解法：大顶堆。复杂度高
 * 思路：二分查找。
 * 相似的题：668
 * @author yajie
 *
 */
public class FindKthNumberII {
	public static int kthSmallest(int[][] matrix, int k) {
		int n = matrix.length;
		int left = matrix[0][0];//最小的数
		int right = matrix[n-1][n-1];//最大的数
		while(left < right) {
			int mid = (left + right) >>> 1;//取左中位数
			int count = 0;
			for(int i = 0; i < n; i++) {
				count += countRow(matrix[i], mid);//计算数组中比mid小的个数有多少个。
			}
			if (count >= k) {
				right = mid;
			}else {
				left = mid + 1;
			}
		}
		return left;
		
	}
	//每一行里<=target的数的个数
	public static int countRow(int[] row, int target) {
		// 二分查找
		int left = 0;
		int right = row.length - 1;
		while (left < right) {
			int mid = (left + right + 1) >>> 1;
			if (row[mid] > target) {
				right = mid - 1;
			} else {
				left = mid;
			}
		}
		//这一步很重要！！！！请看第704题：二分查找
		if (row[left] <= target) {
			return left + 1;
		}
		return 0;

	}
	
	public static void main(String[] args) {
		int[][] matrix = {{1,5,9},{10,11,13},{12,13,15}};
		System.out.println(kthSmallest(matrix, 8));
	}
}
