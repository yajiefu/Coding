package leetCode;
/*
 * 题目：57.插入区间
 * 描述：给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 示例 1:
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 * 示例 2:
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 */
import java.util.ArrayList;
import java.util.Arrays;

public class MergeInsert {
	public static int[][] insert(int[][] intervals, int[] newInterval) {
		ArrayList<int[]> list = new ArrayList<>();
		if (intervals == null || intervals.length == 0) {
			list.add(newInterval);
			return list.toArray(new int[list.size()][2]);
		}
		if (newInterval == null) {
			return intervals;
		}
		int n = intervals.length;
		int i = 0;
		//还存在一种情况就是插入的数组在最前面
		if (newInterval[1] < intervals[0][0]) {
			list.add(newInterval);
			for(int[] val : intervals) {
				list.add(val);
			}
			return list.toArray(new int[list.size()][2]);
		}
		
		while (i < n && intervals[i][0] < newInterval[0] && intervals[i][1] < newInterval[0]) {
			list.add(intervals[i]);
			i++;
		}
		if (i == n) {//说明所有不重合
			list.add(new int[] {newInterval[0], newInterval[1]});
			return list.toArray(new int[list.size()][2]);
		}
		//如果此时的intervals[i]与newInterval没有重合
		if (intervals[i][0] > newInterval[1]) {
			list.add(newInterval);
		}else {//如果重合了，转换成56题
			intervals[i][0] = Math.min(intervals[i][0], newInterval[0]);
			intervals[i][1] = Math.max(intervals[i][1], newInterval[1]);
		}
		

		//向下合并,跟56题一样
		while(i < n) {
			int left = intervals[i][0];
			int right = intervals[i][1];
			while(i < n - 1 && intervals[i+1][0] <= right) {
				right = Math.max(right, intervals[i+1][1]);
				i++;
			}
			list.add(new int[] {left, right});
			i++;
		}
		return list.toArray(new int[list.size()][2]);

	}
	
	public static void main(String[] args) {
		int[][]  intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
		int[] newInterval = {5,8};
		int[][] res = insert(intervals,newInterval);


		for (int[] is : res) {
			System.out.println(Arrays.toString(is));
		}
	}
}
