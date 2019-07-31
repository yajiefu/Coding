package leetCode;

import java.util.Arrays;


/*
 * 题目：75.颜色分类
 * 描述： 给定一个包含红色、白色和蓝色，一共 n个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * 示例:
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 
 * 进阶：
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * 
 * 方法1：快排
 * 方法2：计数排序
 * 方法3："荷兰旗"问题：其主要思想是给每个数字设定一种颜色，并按照荷兰国旗颜色的顺序进行调整。
 * 我们用三个指针（p0, p2 和curr）来分别追踪0的最右边界，2的最左边界和当前考虑的元素。
 * 
 * 思路：本解法的思路是沿着数组移动 curr 指针，若nums[curr] = 0，则将其与 nums[p0]互换；若 nums[curr] = 2 ，则与 nums[p2]互换。
 * 
 * 算法
 * 初始化0的最右边界：p0 = 0。在整个算法执行过程中 nums[idx < p0] = 0.
 * 初始化2的最左边界 ：p2 = n - 1。在整个算法执行过程中 nums[idx > p2] = 2.
 * 初始化当前考虑的元素序号 ：curr = 0.
 * While curr <= p2 :
 *        若 nums[curr] = 0 ：交换第 curr个 和 第p0个 元素，并将指针都向右移。
 *        若 nums[curr] = 2 ：交换第 curr个和第 p2个元素，并将 p2指针左移 。
 *        若 nums[curr] = 1 ：将指针curr右移。
 * 
 * 时间复杂度 :由于对长度 n的数组进行了一次遍历，时间复杂度为O(n)
 * 空间复杂度 :由于只使用了常数空间，空间复杂度为O(1)
 */
public class SortColors {
	// 方法1：快排。时间复杂度：O(nlogn) 最坏：O(n^2)  空间复杂度：O(nlogn)
	public static void sortColors1(int[] nums) {

		if (nums == null || nums.length <= 1) {
			return;
		}
		quickSort(nums, 0, nums.length - 1);

	}


	public static void quickSort(int[] arr, int low, int high) {
		if (low >= high) {
			return;
		}
		int pivot = arr[low];
		int i = low;
		int j = high;

		while (i < j) {
			while (i < j && arr[j] > pivot) {
				j--;
			}
			if (i < j) {
				arr[i++] = arr[j];
			}

			while (i < j && arr[i] < pivot) {
				i++;
			}
			if (i < j) {
				arr[j--] = arr[i];
			}
		}

		arr[i] = pivot;
		quickSort(arr, low, i - 1);
		quickSort(arr, i + 1, high);
	}
	
	//方法2：计数排序。下面是通用的解法，可以根据题意修改：因为此题中已经确定是0,1,2了，可以直接设置helper的长度为3
	public static void sortColors2(int[] nums) {

		if (nums == null || nums.length <= 1) {
			return;
		}
		//计数排序
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < nums.length; i++) {
			if (nums[i] > max) {
				max = nums[i];
			}
			if (nums[i] < min) {
				min = nums[i];
			}
		}
		
		int[] helper = new int[max - min + 1];
		
		for(int i = 0; i < nums.length; i++) {
			helper[nums[i] - min]++;
		}
		
		for(int i = 0, index = 0; i < helper.length; i++) {
			int item = helper[i + min];
			while(item-- != 0) {
				nums[index++] = i + min;
			}
		}

	}
	
	//方法3：荷兰旗
	public static void sortColors(int[] nums) {
		if (nums == null || nums.length <= 1) {
			return;
		}
		
		int p0 = 0;
		int p2 = nums.length - 1;
		int cur = 0;
		while(cur <= p2) {
			if (nums[cur] == 0) {
				//和nums[p0]交换，指针都向右移
				int temp = nums[p0];
				nums[p0++] = nums[cur];
				nums[cur++] = temp;
			}else if (nums[cur] == 2) {
				//和nums[p2]交换，p2往前移
				int temp = nums[p2];
				nums[p2--] = nums[cur];
				nums[cur] = temp;
			}else {
				cur++;
			}
		}
		
	}
	public static void main(String[] args) {
		int[] nums = {2,0,1,2,0,0,1};
		sortColors(nums);
		System.out.println(Arrays.toString(nums));
	}

}
