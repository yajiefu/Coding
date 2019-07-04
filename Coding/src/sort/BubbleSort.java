package sort;

import java.util.Arrays;
/**
 * 冒泡排序 
 * 时间复杂度：T(n)=O(n2) (平均最好最坏) 
 * 时间复杂度：S(n)=O(1) 
 * 稳定性：稳定
 */
public class BubbleSort {


	// 普通冒泡排序
	public static int[] bubbleSort(int[] array) {
		if (array == null || array.length < 2) {
			return array;
		}
		for (int end = array.length - 1; end > 0; end--) {// 确定排序趟数，end是每一趟结束的位置。
			for (int i = 0; i < end; i++) {
				if (array[i] > array[i + 1]) {
					swap(array, i, i + 1);
				}
			}
		}
		return array;
	}

	// 优化冒泡排序？？？？？
	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void main(String[] args) {
		int[] array = { 3, 2, 4, 0, 7 };
		System.out.println(Arrays.toString(bubbleSort(array)));
	}

}
