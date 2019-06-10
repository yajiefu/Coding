package swordForOffer;

/*
 * 题目：调整数组顺序使奇数位于偶数前面
 * 描述：输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * 
 * 
 * 思路：考虑相对位置-->稳定的算法：冒泡，插入，归并排序等。
 * 方法1：冒泡排序，前后就奇偶交换。时间复杂度O(n2)
 * 方法2：空间换时间
 * 
 */
public class ReOrderArray {

	// 方法1：冒泡排序，时间复杂度O(n2)
	public static void reOrderArray1(int[] array) {
		if (array.length <= 1) {
			return;
		}
		int lens = array.length;
		for (int i = 0; i < lens - 1; i++) {
			for (int j = 0; j < lens - 1 - i; j++) {
				// 前偶后奇就交换
				if (((array[j] & 1) == 0) && ((array[j + 1] & 1) == 1)) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

	// 方法2：空间换时间，新建一个数组，两次遍历原数组。时间复杂度O(n),空间复杂度O(n)
	public static void reOrderArray2(int[] array) {
		if (array.length <= 1) {
			return;
		}
		int len = array.length;
		int[] newArray = new int[len];
		int i = 0;
		// 将奇数放进新数组里
		for (int j = 0; j < len; j++) {
			if ((array[j] & 1) == 1) {
				newArray[i++] = array[j];
			}
		}
		// 将偶数放进新数组里
		for (int j = 0; j < len; j++) {
			if ((array[j] & 1) == 0) {
				newArray[i++] = array[j];
			}
		}
		// 将新数组拷贝到原数组里
		for (int j = 0; j < len; j++) {
			array[j] = newArray[j];
		}

	}

	public static void main(String[] args) {
		int[] array = { 4, 5, 1, 2, 4, 1, 3, 6, 7 };
		reOrderArray2(array);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}

	}
}
