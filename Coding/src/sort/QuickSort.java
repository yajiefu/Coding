package sort;

/*
 * 快速排序
 * 算法步骤：
 *1.从数列中挑出一个元素，称为 “基准”（pivot）;
 *2.重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 *3.递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
 *
 *时间复杂度：平均，最好：T(n)=O(nlogn)  最坏：T(n)=O(n2)
 *空间复杂度：S(n)=O(nlogn)
 *稳定性：不稳定
 */
import java.util.Arrays;

public class QuickSort {
	public static void quickSort(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return;
		}
		quickSort(arr, 0, arr.length - 1);
	}

	public static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int partitionIndex = partition(arr, low, high);
			quickSort(arr, low, partitionIndex - 1);
			quickSort(arr, partitionIndex + 1, high);
		}
	}

	// 我常用这个方法
	public static int partition(int[] arr, int low, int high) {
		// 基准是首位第一个元素。先从右到左找到比基准小的数放在基准的位置，j的位置为空
		// 再从左往右找到比基准大的数放在j的位置。
		int pivot = arr[low];
		int i = low;
		int j = high;
		while (i < j) {
			while (i < j && arr[j] >= pivot) {
				j--;
			}
			if (i < j) {// 这里要判断一下，否则当i和j相等的时候这里还要执行就错了。
				arr[i++] = arr[j];
			}
			while (i < j && arr[i] <= pivot) {
				i++;
			}
			if (i < j) {// 这里要判断一下，否则当i和j相等的时候这里还要执行就错了。
				arr[j--] = arr[i];
			}
		}
		// 此时i==j
		arr[i] = pivot;
		return i;
	}

	// 另一种写法
	public static int partition1(int[] arr, int low, int high) {
		// 设定基准值(pivot)
		int pivot = low;
		int index = pivot + 1;
		for (int i = index; i <= high; i++) {
			if (arr[i] < arr[pivot]) {
				swap(arr, i, index);
				index++;
			}
		}
		swap(arr, pivot, index - 1);
		return index - 1;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void main(String[] args) {
		int[] array = { 6, 8, 3, 2, 4, 0, 7 };
		quickSort(array);
		System.out.println(Arrays.toString(array));
	}
}
