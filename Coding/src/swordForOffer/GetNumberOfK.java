package swordForOffer;


/*
 * 题目：数字在排序数组中出现的次数
 *描述：统计一个数字在排序数组中出现的次数。
 *
 *思路：
 *       方法1：排序，我们想到二分。但是前后可能都有，可能n个，所以，时间复杂度还是O(n)，不好
 *       方法2：分两步：分别找出first和last，还是采用二分的方法

 */
public class GetNumberOfK {
	public static int getNumberOfK(int[] array, int k) {
		int count = 0;
		int len = array.length;
		if (len > 0) {
			int first = getFirstK(array, 0, len - 1, k);
			int last = getLastK(array, 0, len - 1, k);
			if (first > -1 && last > -1) {
				return last - first + 1;
			}
		}
		return count;

	}

	public static int getFirstK(int[] array, int low, int high, int k) {
		if (low > high) {
			return -1;
		}
		int mid = (low + high) / 2;
		if (array[mid] == k) {
			if ((mid > 0 && array[mid - 1] != k) || mid == 0) {
				return mid;
			} else {
				high = mid - 1;
			}
		} else if (array[mid] > k) {
			high = mid - 1;
		} else {
			low = mid + 1;
		}
		return getFirstK(array, low, high, k);
	}

	public static int getLastK(int[] array, int low, int high, int k) {
		if (low > high) {
			return -1;
		}
		int mid = (low + high) / 2;
		if (array[mid] == k) {
			if ((mid < array.length - 1 && array[mid + 1] != k) || mid == array.length - 1) {
				return mid;
			} else {
				low = mid + 1;
			}
		} else if (array[mid] > k) {
			high = mid - 1;
		} else {
			low = mid + 1;
		}
		return getLastK(array, low, high, k);
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 2, 3, 3, 4, 5, 5, 5, 6, 6, 7, 8, 8 };
		System.out.println(getNumberOfK(arr, 1));
		System.out.println(getNumberOfK(arr, 2));
		System.out.println(getNumberOfK(arr, 4));
		System.out.println(getNumberOfK(arr, 5));
		System.out.println(getNumberOfK(arr, 8));
		System.out.println(getNumberOfK(arr, 9));
	}
}
