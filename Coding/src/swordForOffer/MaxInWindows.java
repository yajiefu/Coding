package swordForOffer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * 题目：滑动窗口的最大值
 * 描述：给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 
 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 * 
 * 方法1：蛮力法，时间复杂度O(nk)
 * 方法2：一个滑动窗口可以看成是一个队列，符合“先进先出”的特点，如果我们能从队列中找到它的最大值，本题就可以解决了。
 *    之前，我们可以用O(1)的时间得到最小值的栈，同样，我们也可以用O(1)的时间得到栈的最大值。另外，我们讨论过如何用两个栈实现一个队列。
 *    综合上面两个问题的解决方案，我们发现，如果把队列用两个栈实现，由于可以用O(1)的时间得到栈中的最大值，那么就可以用O（1）的时间得到队列的最大值
 *    因此总的时间复杂度就降到了O(n)
 *    
 * 方法3：滑动窗口应当是队列，但为了得到滑动窗口的最大值，队列可以从两端删除元素，因此使用双端队列。
 *     原则1：对新来的元素k，将其与双端队列中的元素相比较
 *     1）队列前面比k小的，直接移出队列（因为不再可能成为后面滑动窗口的最大值了!）,
 *     2）队列前面比k大的X，比较两者下标，判断X是否已不在窗口之内，不在了，直接移出队列
 *    原则2：对新来的元素k，其下标都要入队
 *    原则3：队列的第一个元素是滑动窗口中的最大值
 * 
 */
public class MaxInWindows {

	public static ArrayList<Integer> maxInWindows(int[] num, int size) {
		ArrayList<Integer> result = new ArrayList<>();
		if (num == null || size == 0) {
			return result;
		}
		Deque<Integer> indexDeque = new LinkedList<>();
		for (int i = 0; i < size - 1; i++) {
			while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
				indexDeque.removeLast();
			}
			// 注意，这里是添加下标
			indexDeque.addLast(i);
		}

		for (int i = size - 1; i < num.length; i++) {
			while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
				indexDeque.removeLast();
			}
			// 注意，这里是添加下标
			indexDeque.addLast(i);
			if (i - indexDeque.getFirst() + 1 > size) {
				// 最大值已经移出窗口
				indexDeque.removeFirst();
			}
			result.add(num[indexDeque.getFirst()]);
		}
		return result;
	}

	public static void main(String[] args) {
		int[] num = { 16, 14, 12, 10, 8, 6, 4 };
		ArrayList<Integer> result = maxInWindows(num, 3);

		for (Integer integer : result) {
			System.out.print(integer + " ");
		}
	}

}
