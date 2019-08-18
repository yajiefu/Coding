package leetCode;
/**
 * 题目：153.寻找旋转排序数组中的最小值
 * 描述：假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 请找出其中最小的元素。
 * 你可以假设数组中不存在重复元素。
 * 示例 1:
 * 输入: [3,4,5,1,2]
 * 输出: 1
 * 示例 2:
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 * 
 *  思路：
 *  方法1：遍历O(n)，没有利用旋转数组的特性
 *  方法2：两个有序的子数组，而且前面的数组都大于后面的数组的元素。最小的元素还是分界点。二分查找。
 *  时间复杂度：和二分搜索一样 O(logN)
 *  空间复杂度：O(1)    
 * 
 * 
 * 方法3：分治法
 * @author yajie
 *
 */
public class MinNumberinRotateArray {
	//方法2：二分法
	public static int findMin(int[] nums) {
		if (nums == null || nums.length == 0) {
			throw new IllegalArgumentException("数组为空，无最小元素");
		}
		//二分查找模板
		int len = nums.length;
		int left = 0;
		int right = len - 1;
		while(left < right) {
			int mid = (left + right) >>> 1;//取左中位数
			if (nums[mid] > nums[right]) {
				left = mid + 1;//左边界会收缩
			}else {
				 // 因为题目中说：你可以假设数组中不存在重复元素。
                 // 此时一定有 nums[mid] < nums[right]
				right = mid;
			}
		}
		return nums[left];
	}
	
	
	//方法3：分治法
	public static int findMin1(int[] nums) {
		if (nums == null || nums.length == 0) {
			throw new IllegalArgumentException("数组为空，无最小元素");
		}
		int len = nums.length;
		return findMin1(nums, 0, len-1);
	}

	public static int findMin1(int[] nums, int left, int right) {
		// 或者left + 1 >= right
		if (left == right || left + 1 == right) {
			return Math.min(nums[left], nums[right]);
		}
		int mid = (left+right)>>>1;
		if (nums[mid] > nums[right]) {
			// 左边是顺序数组
			// 3 4 5 6 7 8 1 2
			return Math.min(findMin1(nums, mid + 1, right), nums[left]);
		}else {
			//nums[mid] < nums[right] ,右边是顺序数组
			//  8 9 1 2 3 4 5 6 7
			return Math.min(findMin1(nums, left, mid - 1), nums[mid]);
		}
	}

	public static void main(String[] args) {
		int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println(findMin(nums));
		System.out.println(findMin1(nums));
	}
}
