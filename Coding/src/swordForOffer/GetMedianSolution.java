package swordForOffer;
/*
 * 题目：数据流中的中位数
 * 描述：如何得到一个数据流中的中位数？
 *     如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 *     如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *     我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 *     
 * 思路：数据是从一个数据流中读出来的，因此数据的数目随着时间的变化而增加。需要一个容器来保存，用什么数据结构呢？
 * 1.没有排序的数组。可以利用partition函数找到中位数。在没有排序的数组中插入一个数字O(1),找出中位数O(n).
 * 2.排序的数组：我们还可以往数组插入新数据时让数组保持有序，这时就要移动O(n)。因此插入数据需要O(n),找出中位数O(1)
 * 3.排序的链表：插入数据O(n),如果定义两个指针指向中间位置，能在O(1)时间内找到中位数，时间复杂度和排序的数组一样。
 * 4.二叉搜索树，插入新数据O(logn),但是如果二叉搜索树极度不平衡从而看起来像个排序的链表，插入数据仍是O(n)，为了得到中位数，我们可以在二叉树节点中添加一个表示节点数目的字段，有了这个字段，可以在评价O(logn)的时间内找到中位数，但最差仍是O(n)
 * 5.避免第4种情况，我们可以利用平衡二叉搜索树，AVL。一般平衡因子是左右子树的高度差，这时，我们可以改为左右子树节点的数目之差。添加数据O(logn),得到中位数O(1)(手撕AVL，难度有点大)
 * 6.用一个大顶堆实现左边的数据容器，右边用小顶堆。插入O(logn),得到中位数O(1)
 * 
 * 
 * 接下来考虑最大堆和最小堆的实现的细节：
 * 数据需要平均分配到两个堆中，因此两个堆中是数目差不能超过1。
 * 可以在总数目为偶数时，将新数据放进右边小顶堆，否则插入大顶堆。
 * 保证大顶堆里的数据都小于小顶堆里的数据。目前数据总数目是偶数，按照前面的分配，需要把新数据插入右边小顶堆，但是，该数据比大顶堆里的一些数据小，该怎么办？
 * 可以先将数据插入左边大顶堆，接着把最大堆中的最大的数字拿出来放入小顶堆。
 * 注意：这里将这样处理：并不是直接把新数据插入右边小顶堆，而是先放在左边大顶堆里，经过筛选后将大顶堆里的最大的数插入右边小顶堆里。这样我们就不用考虑新数据是不是比大顶堆里的某些数据小了。

 */

import java.util.Comparator;
import java.util.PriorityQueue;


public class GetMedianSolution {
	// 左边大顶堆
	PriorityQueue<Integer> maxHeap = new PriorityQueue<>(15, new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	});
	// 右边小顶堆:优先队列是基于小顶堆实现的
	PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	//记录奇偶数
	int count = 0;

	public void Insert(Integer num) {
		if ((count & 1) == 0) {//如果是偶数
			maxHeap.add(num);
			minHeap.add(maxHeap.poll());
		}
		else {
			minHeap.add(num);
			maxHeap.add(minHeap.poll());
			
		}
		count++;
	}

	public Double GetMedian() {
		if ((count & 1) == 1) {
			return (double)minHeap.peek();
		}
		return (maxHeap.peek() + minHeap.peek())/2.0;
	}
	

}
