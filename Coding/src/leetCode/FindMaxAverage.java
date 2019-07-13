package leetCode;
/**
 * 题目：643.子数组最大平均数I
 *描述：给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 *思路：滑动窗口
 * @author yajie
 *时间复杂度O(1) 空间复杂度O(1)
 */
public class FindMaxAverage {
	// 滑动窗口
	public static double findMaxAverage(int[] nums, int k) {

		int sum = 0;// 记录最大连续k数组的和
		// 滑动窗口在[0,k-1]
		for (int i = 0; i < k; i++) {
			sum += nums[i];
		}
		int temp = sum; // 记录连续k数组的和

		for (int i = 1; i + k - 1 < nums.length; i++) {
			temp = temp + nums[i + k - 1] - nums[i - 1];// 减去滑出的一个数字，加上滑进的一个数字
			if (temp > sum) {
				sum = temp;
			}
		}
		return sum / (double) k;

	}

	public static void main(String[] args) {
		int[] nums = { 1, 12, -5, -6, 50, 3 };
		int k = 4;
		System.out.println(findMaxAverage(nums, k));
	}
}
