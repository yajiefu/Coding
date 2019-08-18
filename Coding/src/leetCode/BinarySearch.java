package leetCode;
/**
 * 题目：704.二分查找
 * 描述：给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target，写一个函数搜索 nums中的 target，如果目标值存在返回下标，否则返回 -1。
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * 
 * 示例 2:
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 * 提示：你可以假设 nums 中的所有元素是不重复的。n将在 [1, 10000]之间。nums 的每个元素都将在 [-9999, 9999]之间。
 * 
 * 
 * 二分查找的模板：https://leetcode-cn.com/problems/search-insert-position/solution/te-bie-hao-yong-de-er-fen-cha-fa-fa-mo-ban-python-/
 * @author yajie
 *
 */
public class BinarySearch {
	public static int search(int[] nums, int target) {
		if (nums == null || nums.length <= 0) {
			return -1;
		}
		int len = nums.length;
		int left = 0;
		int right = len - 1;

		while (left < right) {
			int mid = (left + right + 1) >>> 1;// 这里是否加1看下面的分支。+1表示取右中位数
			if (nums[mid] > target) {
				right = mid - 1;// 左边界排除中位数
			} else {
				left = mid;
			}
		}
		// 此时left==right
		if (nums[left] == target) {
			return left;
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] nums = { -1, 0, 3, 5, 9, 12 };
		int target = 9;
		System.out.println(search(nums, target));

	}
}
