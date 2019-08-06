package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 题目：56.合并区间
 * 描述：给出一个区间的集合，请合并所有重叠的区间。
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * @author yajie
 * 
 * 思路：[left,right],按照left从小到大排序。
 * 如果当前区间的右端点在后一个区间的左端点之前，它们不会重合，可直接将它放进list,如果重合，就更新right
 * 
 * 时间复杂度：O(nlogn)。排序O(nlogn)+一次线性扫描O(nlogn),所以主要是排序的O(nlogn)
 * 空间复杂度：O(n)，(or O(n))
 * 我们可以原地排序，就不需要额外的存储空间，否则需要一个线性大小的空间做intervals的备份。
 *
 */
public class Merge {
	
	public static int[][] merge(int[][] intervals) {
		if (intervals == null || intervals.length <= 0) {
			return intervals;
		}
		if (intervals[0].length != 2) {
			return null;
		}
		
		int n = intervals.length;
		ArrayList<int[]> list = new ArrayList<>();
		
		Arrays.sort(intervals,new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}

		});
		
		int i = 0;
		while(i < n) {
			int left = intervals[i][0];
			int right = intervals[i][1];
			while (i < n-1 && right >= intervals[i+1][0]) {
				right = Math.max(right, intervals[i+1][1]);
				i++;
			}
			list.add(new int[] {left, right});
			i++;
		}
		//将list转为数组
		return list.toArray(new int[list.size()][2]);
	}
	
	public static void main(String[] args) {
		int[][] arr = {{1,3},{8,10},{4,6},{15,18}};
		int[][] res = merge(arr);


		for (int[] is : res) {
			System.out.println(Arrays.toString(is));
		}
 	}

}
