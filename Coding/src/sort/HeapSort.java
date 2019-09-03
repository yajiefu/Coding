package sort;

import java.util.Arrays;
/*
 * 堆排序
 * 1.如果要求从小到大排序，要构造大顶堆；
 * 2.如果要求从大到小排序，要构造小顶堆；
 * 
 * 题目：给定一个数组，要求从小到大排序。
 * 
 * 算法步骤
 * 1.将初始待排序关键字序列(R0,R2….Rn-1)构建成大顶堆，此堆为初始的无序区；
 *      构造过程：从最后一个非叶子节点开始调整
 * 2.将堆顶元素R[0]与最后一个元素R[n-1]交换，此时得到新的无序区(R0,R2,……Rn-2)和新的有序区(Rn-1),且满足R[1,2…n-2]<=R[n-1]；
 * 3.由于交换后新的堆顶R[0]可能违反堆的性质，因此需要对当前无序区(R0,R2,……Rn-2)调整为新堆，然后再次将R[0]与无序区最后一个元素交换，得到新的无序区(R0,R2….Rn-3)和新的有序区(Rn-2,Rn-1)。不断重复此过程直到有序区的元素个数为n，则整个排序过程完成。
 * 
 * 请特别特别注意: 初始化大顶堆时 是从最后非叶子开始往上调整最大堆。而堆顶元素(最大数)与堆最后一个数交换后，需再次调整成大顶堆，此时是从上往下调整的。
 * 时间复杂度：平均最好最坏：O(nlogn) 
 * 空间复杂度：O(1) 

 */
public class HeapSort {
	public static void heapSort(int[] arr) {
		//数组和二叉树转换
		if (arr == null || arr.length <= 1) {
			return;
		}
		int len = arr.length;
		
		//找到第一个非叶子节点开始调整，第一个非叶子节点的坐标是：len/2-1
		for(int i = len / 2 -1; i >= 0; i--) {
			adjustHeap(arr, i, len);
//			System.out.println(i+":"+Arrays.toString(arr));
		}
		//以上堆就建好了。现在就要从堆顶开始拿数据
//		System.out.println(Arrays.toString(arr));//[8, 7, 3, 6, 4, 1, 0, 2]
		
    	//注意堆的长度变小了。所以j要倒序
		for(int i = len - 1; i >= 0; i--) {
			//交换根节点和最后一个子节点，然后再从根节点开始调整大顶堆
			swap(arr, i, 0);
			//从根节点开始调整
			adjustHeap(arr, 0, i);
		}
		
	}
	 //调整堆,pos是调整的父节点
	public static void adjustHeap(int[] arr, int pos, int len) {
		//向下调整
		int left = pos * 2 + 1;
		int right = pos * 2 + 2;
		int largest = pos;
	 	//找到最大的数放在pos的位置
		if (left < len && arr[left] > arr[largest] ) {
			largest = left;
		}
		if (right < len && arr[right] > arr[largest]) {
			largest = right;
		}
		
		if (arr[largest] != arr[pos]) {
			//交换位置
			swap(arr, largest, pos);
			//然后largest的位置也要向下开始调整
			adjustHeap(arr, largest, len);
		}
		
	}
	
	
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	public static void main(String[] args) {
		int[] array = { 6, 8, 3, 2, 4, 1,0, 7 };
		heapSort(array);
		System.out.println(Arrays.toString(array));
	}
	
	

}
