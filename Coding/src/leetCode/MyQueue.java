package leetCode;
/**
 * 题目：232.用栈实现队列
 * 描述：使用栈实现队列的下列操作：
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 * 
 * 示例:
 * MyQueue queue = new MyQueue();
 * queue.push(1);
 * queue.push(2);  
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 * 
 * 说明:
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 */
import java.util.Stack;

public class MyQueue {
	private int front;
	Stack<Integer> stack1 = new Stack<>();
	Stack<Integer> stack2 = new Stack<>();
	  /** Initialize your data structure here. */
	public MyQueue() {
	}

	//时间复杂度：O(1)
	//空间复杂度：O(n) 需要额外的内存来存储队列元素
	   /** Push element x to the back of queue. */
	public void push(int x) {
		//要更新front.
		if (stack1.isEmpty()) {
			front = x;
		}
		stack1.push(x);

	}

	//时间复杂度： 摊还复杂度 O(1)，最坏情况下的时间复杂度 O(n)
	  //在最坏情况下，s2 为空，算法需要从 s1 中弹出 n个元素，然后再把这 n个元素压入 s2，在这里n代表队列的大小。这个过程产生了 2n步操作，时间复杂度为O(n)。
	  //但当 s2 非空时，算法就只有 O(1)的时间复杂度。
	//空间复杂度：O(1) 
	  /** Removes the element from in front of queue and returns that element. */
	public int pop() {
		if (stack2.isEmpty()) {
			while(!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
		return stack2.pop();

	}

	//我们定义了front 变量来保存队首元素，每次入队操作我们都会随之更新这个变量。
	//当 s2 为空，front 变量就是队首元素，当 s2 非空，s2 的栈顶元素就是队首元素。
	//时间复杂度：O(1)
	//空间复杂度：O(1) 
	   /** Get the front element. */
	public int peek() {
//		if (stack2.empty()) {
//			while(!stack1.isEmpty()) {
//				stack2.push(stack1.pop());
//			}
//		}
//		return stack2.peek();
		if (stack2.isEmpty()) {
			return front;
		}
		return stack2.peek();

	}
	//时间复杂度：O(1)
	//空间复杂度：O(1) 
	   /** Returns whether the queue is empty. */
	public boolean empty() {
		return stack1.isEmpty() && stack2.isEmpty();
	}

}
