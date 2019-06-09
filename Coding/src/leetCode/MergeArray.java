package leetCode;


/*
 * 题目：88.合并两个有序数组
 * 描述：给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。

 *  这道题的精髓在于：在合并两个数组（包括字符串时），如果从前往后复制每个数字（或字符）则需要重复移动数字（获字符）多次，
 *  那么我们可以考虑从后往前复制，这样可以减少移动的次数，从而提高效率。
 */
public class MergeArray {

	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		if (nums2 == null || n <= 0) {
			return;
		}
		// 指向合并后数组的指针
		int indexMerge = m + n - 1;
		// 执行数组num1的指针
		int index1 = m - 1;
		// 执行数组num2的指针
		int index2 = n - 1;
		while (index1 >= 0 && index2 >= 0) {
			if (nums1[index1] <= nums2[index2]) {
				nums1[indexMerge--] = nums2[index2--];
			} else {
				nums1[indexMerge--] = nums1[index1--];
			}
		}
		while (index1 >= 0) {
			nums1[indexMerge--] = nums1[index1--];
		}
		while (index2 >= 0) {
			nums1[indexMerge--] = nums2[index2--];
		}
	}

	public static void main(String[] args) {
		int[] nums1 = { 1, 5, 7, 9, 0, 0, 0, 0, 0, 0 };
		int m = 4;
		int[] nums2 = { 2, 3, 4, 6, 8, 10 };
		int n = 6;
		merge(nums1, m, nums2, n);
	}
}
