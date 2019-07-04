package sort;

import java.util.Arrays;

/**
 * 选择排序（简单选择排序） 
 * 算法思想：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 *        然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。 以此类推，直到所有元素均排序完毕。 
 * 
 * 时间复杂度：T(n)=O(n2) (平均最好最坏) 
 * 空间复杂度：S(n)=O(1) 
 * 稳定性：不稳定
 * 
 * @author yajie
 *
 */
public class SelectionSort {
	public static int[] selectionSort(int[] array) {
		if (array == null || array.length < 2) {
			return array;
		}
		int len = array.length;
		for (int i = 0; i < len - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < len; j++) {
				if (array[j] < array[minIndex]) {
					minIndex = j;
				}
			}
			swap(array, i, minIndex);
		}
		return array;
	}

	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void main(String[] args) {
		int[] array = { 3, 2, 4, 0, 7 };
		System.out.println(Arrays.toString(selectionSort(array)));
	}
}
