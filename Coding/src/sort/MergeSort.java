package sort;

import java.util.Arrays;

/**
 * 归并排序
 * 思想：归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）策略
 *         （分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之)。
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
 * @author yajie
 *
 */
public class MergeSort {
	public static int[] mergeSort(int[] array) {
		//先开辟一个和数组等长的临时数组
		int[] temp = new int[array.length];
		sort(array, 0, array.length - 1, temp);
		return temp;
		
	}
	
	public static void sort(int[] array, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2;
			sort(array, left, mid, temp);//左边归并排序，使得左子序列有序
			sort(array, mid + 1, right, temp);//右边归并排序，使得右子序列有序
			merge(array, left, mid, right, temp);//将两边数组进行合并
		}
	}
	
	public static void merge(int[] array, int left, int mid, int right, int[] temp) {
		int i = left;// 左序列指针
		int j = mid + 1;// 右序列指针
		int k = 0;//临时数组的指针
		while(i <= mid && j <= right) {
			if (array[i] <= array[j]) {
				temp[k++] = array[i++];
			}else {
				temp[k++] = array[j++];
			}
		}
		// 当两个数组中有一个数组的数全部放到新排序数组中时。可能存在某个数组还有数。
		// 将左边剩余元素都填充到tmp
		while(i <= mid) {
			temp[k++] = array[i++];
		}
		// 右边剩余元素都填充到tmp
		while(j <= right) {
			temp[k++] = array[j++];
		}
		k = 0;
		//将temp数组中的数拷贝到原数组中去
		while(left <= right) {
			array[left++] = temp[k++];
		}
	}
	
	
	
	public static void main(String[] args) {
		int[] array = {1,5,3,7,8,2,4,9,6};
		System.out.println(Arrays.toString(mergeSort(array)));
	}
	
}
