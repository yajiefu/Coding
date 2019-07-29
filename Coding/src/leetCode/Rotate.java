package leetCode;
/**
 * 题目：189.旋转数组
 * 描述：给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 
 * 说明:尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。要求使用空间复杂度为 O(1) 的 原地 算法。
 * 方法1：暴力解决:时间复杂度：O(k*n);空间复杂度：O(1)
 * 方法2：辅助一个新数组:时间复杂度：O(n);空间复杂度：O(n)
 * 方法3：反转数组：时间复杂度：O(n);空间复杂度：O(1)
 */
import java.util.Arrays;

public class Rotate {

	public static void rotate(int[] nums, int k) {
		if (nums == null || nums.length <= 1 || k == 0) {
			return;
		}
		int len = nums.length;
		k = k % len;
		reverse(nums, 0, len - k - 1);
		reverse(nums, len - k, len - 1);
		reverse(nums, 0, len - 1);

	}

	public static void reverse(int[] nums, int start, int end) {
		while(start < end) {
			int temp = nums[start];
			nums[start++] = nums[end];
			nums[end--] = temp;
		}

	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5, 6 };
		rotate(nums, 3);
		System.out.println(Arrays.toString(nums));
	}

}
