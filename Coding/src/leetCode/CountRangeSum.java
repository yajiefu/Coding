package leetCode;
/**
 * 题目：327.区间和的个数
 * 描述：给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * 说明:最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
 * 示例:输入: nums = [-2,5,-1], lower = -2, upper = 2,输出: 3 
 * 解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
 * 
 */
import java.util.TreeMap;

public class CountRangeSum {
	// 方法1：暴力解法 时间复杂度 O(n2) (超出时间限制)
	public static int countRangeSum1(int[] nums, int lower, int upper) {
		if (nums == null || nums.length <= 0) {
			return 0;
		}
		int count = 0;
		long[] sum = new long[nums.length];
		sum[0] = 0;
		for (int i = 0; i < nums.length; i++) {
			sum[i + 1] = sum[i] + nums[i];
		}
		for (int i = 0; i < sum.length - 1; i++) {
			for (int j = i + 1; j < sum.length; j++) {
				if ((sum[j] - sum[i]) >= lower && (sum[j] - sum[i]) <= upper) {
					count++;
				}
			}
		}
		return count;
	}

	//方法2：超出时间限制
	public static int countRangeSum2(int[] nums, int lower, int upper) {
		if (nums == null || nums.length <= 0) {
			return 0;
		}
		int count = 0;
		long[] sum = new long[nums.length];
		long temp = 0;
		for (int i = 0; i < nums.length; i++) {
			// 单个数值在所给区间范围
			if (nums[i] >= lower && nums[i] <= upper) {
				count++;
			}
			temp += nums[i];
			sum[i] = temp;

		}

		for (int i = 1; i < nums.length; i++) {
			// [0,i]之和在所给区间范围,，不包括[0,0]，这个是单个数值，已经算过了
			if (sum[i] >= lower && sum[i] <= upper) {
				count++;
			}
			// [j,i]之和在所给区间范围
			for (int j = 0; j < i - 1; j++) {
				long dif = sum[i] - sum[j];
				if (dif >= lower && dif <= upper) {
					count++;
				}
			}
		}
		return count;
	}

	//使用TreeMap来保存每个前缀和的计数。treeMap查询的时间是logn,所以总时间复杂度为O(nlogn)
	public static int countRangeSum3(int[] nums, int lower, int upper) {
		if (nums == null || nums.length <= 0) {
			return 0;
		}
		long[] sum = new long[nums.length];
		sum[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			sum[i] = sum[i - 1] + nums[i];
		}
		int total = 0;
		TreeMap<Long, Integer> treeMap = new TreeMap<>();
		for (int i = 0; i < nums.length; i++) {
			// [0,i]区间之和在所给区间范围内
			if (sum[i] >= lower && sum[i] <= upper) {
				total++;
			}

			//sum[i] - x >= lower && sum[i] - x <= upper 即 sum[i] - upper <= x <=sum[i] - lower
			//这个x代表的是sum[j],其中j<i。上面的式子表达的是[j+1, i]区间和在[lower,upper]之间。
			for (Integer count : treeMap.subMap(sum[i] - upper, true, sum[i] - lower, true).values()) {
				total += count;
			}
			Integer count = treeMap.get(sum[i]);
			if (count == null) {
				count = 1;
			} else {
				count++;
			}
			treeMap.put(sum[i], count);
//			if (treeMap.containsKey(sum[i])) {
//				treeMap.put(sum[i], treeMap.get(sum[i]) + 1);
//			}
//			else {
//				treeMap.put(sum[i],1);
//			}
		}

		return total;
	}

	public static void main(String[] args) {
		int[] nums = { -2, 5, -1 };
		int lower = -2;
		int upper = 2;
		System.out.println(countRangeSum3(nums, lower, upper));

	}
}
