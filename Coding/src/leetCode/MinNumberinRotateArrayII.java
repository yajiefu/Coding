package leetCode;
/*
 * 题目：154.寻找旋转排序数组中的最小值II
 * 描述：假设按照升序排序的数组在预先未知的某个点上进行了旋转。( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 请找出其中最小的元素。
 * 注意数组中可能存在重复的元素。
 * 示例 1：
 * 输入: [1,3,5]
 * 输出: 1
 * 示例 2：
 * 输入: [2,2,2,0,1]
 * 输出: 0
 * 说明：
 * 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
 * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 * 
 * 
 * 方法1：二分法
 * 方法2：分治法
 */
public class MinNumberinRotateArrayII {
	public static int findMin(int[] nums) {
		if (nums == null || nums.length == 0) {
			throw new IllegalArgumentException("数组为空，无最小元素");
		}
		int len = nums.length;
		int left = 0;
		int right = len - 1;
		//二分法模板
		while(left < right) {
			int mid = (left + right) >>> 1;
			if (nums[mid] > nums[right]) {//最小值一定在mid的右边
				left = mid + 1;
			}else if (nums[mid] < nums[right]) {//最小值一定在mid的左边或者就是它自己
				right = mid;
			}else {//nums[mid] == nums[right]
				//目标值可能在中间数的左边，也可能在中间数的右边，那么该怎么办呢？
				//很简单，此时你看到的是右边界，就把只右边界排除掉就好了。正是因为右边界和中间数相等，你去掉了右边界，中间数还在，就让中间数在后面的循环中被发现吧。
				right--;
			}
		}
		return nums[left];
	}
	
	public static int findMin1(int[] nums) {
		if (nums == null || nums.length == 0) {
			throw new IllegalArgumentException("数组为空，无最小元素");
		}
		int len = nums.length;
		return findMin1(nums, 0, len-1);
	}
	
	public static int findMin1(int[] nums, int left, int right) {
		if (left + 1 >= right) {
			return Math.min(nums[left], nums[right]);
		}
		if (nums[left] < nums[right]) {
			return nums[left];
		}
		int mid = (left + right)>>>1;
		return Math.min(findMin1(nums, left, mid), findMin1(nums, mid+ 1, right));
		
	}
	public static void main(String[] args) {
		int[] nums = {1,3,3};
		System.out.println(findMin(nums));
		System.out.println(findMin1(nums));
	}

}
