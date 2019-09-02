package basic;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest {
	public static void main(String[] args) {
		//构建小顶堆：PriorityQueue就是小顶堆实现的
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		int[] arr = {1,4,7,2,4,8,0,3,6,2};
		System.out.println("原数组：");
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+ " ");
			minHeap.offer(arr[i]);
		}
		System.out.println();
		//输出：0 1 2 2 3 4 4 6 7 8 
		System.out.println("默认小顶堆排序：");
		while(!minHeap.isEmpty()) {
			System.out.print(minHeap.poll()+" ");
		}
		System.out.println();
		
		
		
		//小顶堆的默认情况：return o1.compareTo(o2);
		PriorityQueue<Integer> minHeap2 = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				//我们先看小顶堆的默认情况是：返回o1.compareTo(o2);
				//返回值为负数意味着o1比o2小，否则返回为零意味着o1等于o2，返回为正数意味着o1大于o2
			//	System.out.println(o1 + " "+o2 +" "+ o1.compareTo(o2));//能够看出插入的过程
				/**
				 * o1表示是数组后一个元素，o2是前一个元素。新插入的元素先插在堆的最底下，然后依次向上比较。
				 * 比如我们构建的是小顶堆，o1比o2小的话，我们就往上调整，即返回-1。这就是为什么o1.compareTo(o2)
				 */
				return o1.compareTo(o2);
			}
		});
		
		for(int i = 0; i < arr.length; i++) {
			minHeap2.offer(arr[i]);
		}
		/*
		 * 最后全部放进去的堆是这样的：
		 * [0,2,1,3,2,8,7,4,6,4]
		 *            0
		 *         /    \ 
		 *        2      1
		 *      /   \   / \
		 *     3     2  8  7
		 *    / \   /
		 *   4   6 4
		 */
		System.out.println("自定义小顶堆排序：");
		while(!minHeap2.isEmpty()) {
//			System.out.print("抛出："+minHeap2.poll()+" ");//可以看出抛出的过程
			System.out.print(minHeap2.poll()+" ");
		}
		/*
		 * 抛出的过程：堆顶元素0移出，将最后一个节点4放在堆顶，然后向下移动，先比较4的左右子节点2,1，取最小值1，4再和这个1比较，如果4比1大，向下swap，
		 * 接着比较8,7的大小，取最小值7,4比7小，不用调整……依次类推。
		 */
		
		
		//构建大顶堆
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				//写法1：o2如果比o1小，就往上调整-1
//				return o2.compareTo(o1);
				//写法2：或者o1如果比o2大，就往上调整-1
				if (o1 > o2) {
					return -1;
				}else {
					return 1;
				}
				//写法2：
//				return o2 - o1;
			}

		});
		
		for(int i = 0; i < arr.length; i++) {
			maxHeap.offer(arr[i]);
		}
		System.out.println();
		System.out.println("自定义大顶堆排序：");
		while(!maxHeap.isEmpty()) {
			System.out.print(maxHeap.poll()+" ");
		}

	}

}
