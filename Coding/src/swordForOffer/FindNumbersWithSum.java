package swordForOffer;

import java.util.ArrayList;

/*
 * 题目：和为S的两个数字
 * 描述：输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
 *     如果有多对数字的和等于S，输出两个数的乘积最小的。
 *     
 *     
 * 思路：方法1：遍历，T(n)=O(n2)
 *     方法2：两个指针low 和 high，先找出两个数，判断两数之和是大于还是小于S的。(数组是排序的)
 *     如果大于则我们需要更小点的数，所以我们将选择较大数前面的那个数。反之，选择较小数后面的数
 *     T(n)=O(n)
 *     另外：两个数离的越远，乘积越小。
 */
public class FindNumbersWithSum {
	public static ArrayList<Integer> findNumbersWithSum(int[] array, int sum) {
		ArrayList<Integer> result = new ArrayList<>();
		if (array == null || array.length <= 1) {
			return result;
		}
		int low = 0;
		int high = array.length - 1;
		while (low < high) {
			if (array[low] + array[high] == sum) {
				result.add(array[low++]);
				result.add(array[high--]);
			} else if (array[low] + array[high] > sum) {
				high--;
			} else {
				low++;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] array = { -2, -1, 2, 3, 4, 5, 6 };
		findNumbersWithSum(array, 4);
	}
}
