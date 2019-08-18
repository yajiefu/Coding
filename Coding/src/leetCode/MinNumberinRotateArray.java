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
 * @author yajie
 *
 */
public class MinNumberinRotateArray {
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
			//因为不存在重复元素，所以下面这一段可以省略
//			if (nums[mid] == nums[left] && nums[mid] == nums[right]) {
//				//只能顺序寻找
//				int min = Integer.MAX_VALUE;
//				for(int i = left; i <= right; i++) {
//					min = Math.min(min, nums[i]);
//				}
//				return min;
//			}
			
			if (nums[mid] > nums[right]) {
				left = mid + 1;//左边界会收缩
			}else if (nums[mid] < nums[right]) {
				right = mid;
			}
		}
		return nums[left];
	}
	
	public static void main(String[] args) {
		int[] nums = {4,5,6,7,0,1,2};
		System.out.println(findMin(nums));
	}
}
