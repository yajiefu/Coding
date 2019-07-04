package swordForOffer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 题目：最小的k个数
 * 描述：输入n个整数，找出其中最小的K个数。 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 * 
 * 
 * 思路： 
 * 方法0:：最直观的：排序整体排序，取前k个。比如快排思想里T(n)=O(nlogn)
 * 方法1：基于partition思想的方法。T(n)=O(n)
 *   有限制：需要修改输入的数组。
 * 方法2.一个容器放k个数。若不满，直接放；若满了，找出里面最大的值和待插入的比较（删除最大数，插入新值；或不插入）
 *   这个容器用什么数据结构？
 *   1.二叉树：logk时间内实现插入，总时间：nlogk
 *   2.每次都需要找到k个最小，想到堆。O(1)的时间找到最大（小）值，但删除和插入要logk
 *     T(n)=O(nlogk) 特别适合处理海量数据
 *  是小顶堆还是大顶堆？
 *               用大顶堆，保存k个较小的数，最顶端是k个数里面最大的。新增加的要先和堆顶比较，如果比堆顶大，就不用加入，如果比堆顶小，就删除堆顶。
 * 
 * Java的优先队列是基于堆实现的。默认是小顶堆。
 * 
 * 方法2较于方法1的优点：
 * 1. 没有修改输入的数据
 * 2. 适合海量数据。由于内存的大小是有限的，有可能不能把这些海量的数据一次性全部载入内存。
 *     这种方法只要求内存能够容纳maxHeap即可，最适合的情形是n很大并且k较小的情况
 * 
 * @author yajie
 *
 */
public class GetLeastNumbers_Solution {
	// 方法1：基于partition
	public static ArrayList<Integer> getLeastNumbers1(int[] input, int k) {
		ArrayList<Integer> result = new ArrayList<>();
		if (input == null || input.length <= 0 || k <= 0) {
			return result;
		}
		int len = input.length;
		int start = 0;
		int end = len - 1;
		int index = partition(input, start, end);
		while(index != k - 1) {
			if (index > k - 1) {
				end = index - 1;
				index = partition(input, start, end);
			}
			else {
				start = index + 1;
				index = partition(input, start, end);
			}
		}
		for(int i = 0; i < k; i++) {
			result.add(input[i]);
			System.out.println(input[i]);
		}
		return result;
	
	}
	public static int partition(int[] arr, int low, int high) {

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

	//方法2：大顶堆
	public static ArrayList<Integer> getLeastNumbers(int[] input, int k) {
		ArrayList<Integer> result = new ArrayList<>();
		if (input == null || input.length <= 0 || k <= 0) {
			return result;
		}
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
			// 调整为大顶堆
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		for (int i = 0; i < input.length; i++) {
			if (maxHeap.size() < k) {
				maxHeap.offer(input[i]);
			} else if (input[i] < maxHeap.peek()) {
				maxHeap.poll();
				maxHeap.offer(input[i]);
			}
		}
		while (!maxHeap.isEmpty()) {
			result.add(maxHeap.poll());
		}

		for (Integer integer : result) {
			System.out.println(integer);
		}
		return result;

	}

	public static void main(String[] args) {
		int[] input = { 1, 4, 2, 5, 7, 8, 3, 2 };
		getLeastNumbers1(input, 4);

	}
}
