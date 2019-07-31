package sort;

import java.util.Arrays;


/**
 * 归并排序 
 * 算法思想：归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）策略
 * （分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之)。
 * 
 * 算法步骤：
 * 1.申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列； 
 * 2.设定两个指针，最初位置分别为两个已经排序序列的起始位置；
 * 3.比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置； 
 * 4.重复步骤 3 直到某一指针达到序列尾；
 * 5.将另一序列剩下的所有元素直接复制到合并序列尾。
 * 
 * 
 * 时间复杂度：T(n)=O(nlogn)（平均、最好、最坏）
 * 空间复杂度：S(n)=O(n) 
 * 稳定性：稳定
 * 
 * 
 * 但是,使用递归的归并排序需要深度为LogN的栈空间，虽然代码很简单易懂，但是会造成时间和空间上的性能损耗，为了优化归并排序，我们可以使用迭代代替递归
 * 
 * @author yajie
 *
 */
public class MergeSort {
	//递归的方式
	public static int[] mergeSort1(int[] array) {
		// 先开辟一个和数组等长的临时数组
		int[] temp = new int[array.length];
		sort(array, 0, array.length - 1, temp);
		return temp;

	}

	public static void sort(int[] array, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2;
			sort(array, left, mid, temp);// 左边归并排序，使得左子序列有序
			sort(array, mid + 1, right, temp);// 右边归并排序，使得右子序列有序
			merge1(array, left, mid, right, temp);// 将两边数组进行合并
		}
	}

	public static void merge1(int[] array, int left, int mid, int right, int[] temp) {
		int i = left;// 左序列指针
		int j = mid + 1;// 右序列指针
		int k = 0;// 临时数组的指针
		while (i <= mid && j <= right) {
			if (array[i] <= array[j]) {
				temp[k++] = array[i++];
			} else {
				temp[k++] = array[j++];
			}
		}
		// 当两个数组中有一个数组的数全部放到新排序数组中时。可能存在某个数组还有数。
		// 将左边剩余元素都填充到tmp
		while (i <= mid) {
			temp[k++] = array[i++];
		}
		// 右边剩余元素都填充到tmp
		while (j <= right) {
			temp[k++] = array[j++];
		}
		k = 0;
		// 将temp数组中的数拷贝到原数组中去
		while (left <= right) {
			array[left++] = temp[k++];
		}
	}
	
	
	
	
	//迭代的方式
	public static void mergeSort(int[] array) {
		int len = array.length;
		int step = 1;
		while(step < len) {
			mergePass(array, step, len);
			step *= 2;
		}

	}
	
	/*
	 * mergePass用于将数组中相邻的step个元素的子序列进行合并
	 */
	public static void mergePass(int[] array, int step, int len) {
		int i = 0;
		
		//从前往后,将2个长度为k的子序列合并为1个
		//i应该小于多少呢？看下面的式子
		//i + 2 * step - 1应该小于len，所以i < len - 2 * step + 1
		while(i < len - 2 * step + 1) {
			merge(array, i, i + step - 1, i + 2 * step - 1);
			i += 2 * step;
		}
		
		//可能会存在落单的元素。那些落单的长度不足两两merge的部分和前面的merge起来
		//i + step - 1 < len
		if (i <= len - step) {
			merge(array, i, i + step - 1, len - 1);
		}
	}

	/*
	 * merge函数实际上是将两个有序数组合并成一个有序数组
	 */
	public static void merge(int[] array, int left, int mid, int right) {
		//temp用于暂存合并的结果
		int[] temp = new int[right - left + 1];
		int i = left;
		int j = mid + 1;
		int k = 0;//合并后数组的指针
		
		while(i <= mid && j <= right) {
			if (array[i] <= array[j]) {
				temp[k++] = array[i++];
			}else {
				temp[k++] = array[j++];
			}
		}
		
		while(i <= mid) {
			temp[k++] = array[i++];
		}
		while(j <= right) {
			temp[k++] = array[j++];
		}
		k = 0;
		// 将temp数组中的数拷贝到原数组中去
		while (left <= right) {
			array[left++] = temp[k++];
		}
	}
	public static void main(String[] args) {
		int[] array = { 1, 5, 3, 7, 8, 2, 4, 9, 6, 4, 2 };
//		System.out.println(Arrays.toString(mergeSort1(array)));
		
		mergeSort(array);
		System.out.println(Arrays.toString(array));
	}

}
