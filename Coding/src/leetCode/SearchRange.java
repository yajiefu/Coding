package leetCode;



/**
 * 题目：34. 在排序数组中查找元素的第一个和最后一个位置
 * 描述：给定一个按照升序排列的整数数组 nums，和一个目标值 target。
 *   找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * 
 * 
 * 思路：二分法，用的还是二分法模板
 * @author yajie
 *
 */
public class SearchRange {
	public static int[] searchRange(int[] nums, int target) {
		if (nums == null || nums.length <= 0) {
			return new int[] { -1, -1 };
		}
		int[] res = { -1, -1 };
		res[0] = leftBound(nums, target);
		// 优化
		if (res[0] == -1) {
			return res;
		}
		res[1] = rightBound(nums, target);
		return res;

	}

	public static int leftBound(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		while (left < right) {// 退出循环的条件是left=right,这个时候还没有判断该处的值，所以后面要打个补丁。
			int mid = (left + right) >>> 1;// 根据分支逻辑，这里选择左中位数
			if (nums[mid] == target) {
				right = mid;
			} else if (nums[mid] > target) {
				right = mid - 1;
			} else if (nums[mid] < target) {
				left = mid + 1;
			}
		}
		// 补丁
		return nums[left] == target ? left : -1;

	}

	public static int rightBound(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		while (left < right) {// 退出循环的条件是left=right,这个时候还没有判断该处的值，所以后面要打个补丁。
			int mid = (left + right + 1) >>> 1;// 根据分支逻辑，这里选择右中位数
			if (nums[mid] == target) {
				left = mid;
			} else if (nums[mid] > target) {
				right = mid - 1;
			} else if (nums[mid] < target) {
				left = mid + 1;
			}
		}
		// 补丁
		return nums[right] == target ? right : -1;

	}

	public static void main(String[] args) {
		int[] nums = { 5, 7, 7, 8, 10 };
		int target = 6;
		int[] res = searchRange(nums, target);
		System.out.println(res[0] + " " + res[1]);

	}

}
