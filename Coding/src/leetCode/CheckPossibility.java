package leetCode;
/*
 * 题目：665非递减数列
 * 描述：给定一个长度为 n 的整数数组，你的任务是判断在最多改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，满足 array[i] <= array[i + 1]。
 * 
 * 输入: [4,2,3]输出: True 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。[3,4,2,3] false
 */
public class CheckPossibility {
	public static boolean checkPossibility(int[] nums) {
		if (nums.length < 3) {
			return true;
		}
		
		int changeCount = 0;
		if(nums[0] > nums[1]) {
			nums[0] = nums[1];
			changeCount++;
		}
		for(int i = 1; i < nums.length - 1; i++) {
			if (nums[i] > nums[i+1]) {
				// 两种改变情况
				//1.nums[i]变小
				//2.nums[i+1]变大
				// 1,4,3 只能改变nums[i]变小； 3,4,1 只能nums[i+1]变大
				// 如果是开始的前两个，最佳的方法是nums[0]变小
				changeCount++;
				if (changeCount > 1) {
					// 后面不用看了。
					return false;
				}
				int left = nums[i-1];
				int right = nums[i+1];
				// 主要是看左右两边的数字大小
				if (left >= right) {
					nums[i+1] = nums[i];
				}else {
					nums[i] = left;
				}
				
			}
		}
		return changeCount == 0 || changeCount == 1;
      
        
    }
	public static void main(String[] args) {
		int[] nums = {4,2,1};
		int[] nums1 = {4,2,3};
		int[] nums2 = {3,4,1,2};
		System.out.println(checkPossibility(nums));
		System.out.println(checkPossibility(nums1));
		System.out.println(checkPossibility(nums2));
	}
}
