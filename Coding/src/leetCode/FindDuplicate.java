package leetCode;
/*
 * 题目：287.寻找重复数
 * 描述：给定一个包含 n+1个整数的数组 nums，其数字都在1到n之间（包括1和 n），可知至少存在一个重复的整数。
 * 假设只有一个重复的整数，找出这个重复的数。
 * 示例 1:
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 
 * 示例 2:
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 * 
 * 方法：二分，这道题的关键是对要定位的“数”做二分，而不是对数组的索引做二分。
 *             要定位的“数”根据题意在 1和 n之间，每一次二分都可以将搜索区间缩小一半。
 */
public class FindDuplicate {
	public static int findDuplicate(int[] nums) {
		int len = nums.length;
		int left = 1;// 最小的数
		int right = len - 1;// 最大的数

		while (left < right) {
			int mid = (left + right) >>> 1;
			// [left,mid]的数的个数如果大于mid-left+1,就说明重复的数字在[left,mid]之间
			int count = 0;
			for (int num : nums) {
				if (num >= left && num <= mid) {
					count++;
				}
			}
			if (count > mid - left + 1) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;

	}

	public static void main(String[] args) {
		int[] nums = { 1, 3, 4, 2, 2 };
		System.out.println(findDuplicate(nums));
	}
}
