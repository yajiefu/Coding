package leetCode;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 题目：225.用队列实现栈
 * 使用队列实现栈的下列操作：
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 * 注意:
 * 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
 * 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 * @author yajie
 * 
 * 
 * 思路1：利用两个队列q1,q2和一个指向top的指针
 * push:入队q1，top指向该元素
 * pop:将q1的元素都移到q2,直到只剩下最后一个元素时，这个就是pop的元素，然后交换这两个队列
 * top:就是top指向的数字
 * empty：只有queue1中有元素，所以只要判断queue1是否为空
 * 
 * 思路2：利用两个队列a,b
 * push:入队a,然后将b中的元素全部入队a,交换a和b,使得a没有在push的时候始终为空队列。此时b中的元素是栈的顺序
 * pop:b.poll()
 * top:b.peek()
 * empty:b.isEmpty()
 * 
 * 
 *
 */
public class MyStack {
	
//	private Queue<Integer> queue1;
//	private Queue<Integer> queue2;
//	private int top;
//
//	/** Initialize your data structure here. */
//	public MyStack() {
//		queue1 = new LinkedList<>();
//		queue2 = new LinkedList<>();
//
//	}
//
//	/** Push element x onto stack. */
//	public void push(int x) {//时间复杂度O(1)
//		queue1.add(x);
//		top = x;
//	}
//
//	/** Removes the element on top of the stack and returns that element. */
//	public int pop() {//时间复杂度O(N)
//		while(queue1.size() > 1) {
//			//queue2暂时存储queue1中移出的元素
//			top = queue1.poll();
//			queue2.add(top);
//		}
//		//此时queue1中只有一个元素，这个就是我们要返回是元素
//		int val = queue1.poll();
//		//交换两个队列
//		Queue<Integer> temp = queue1;
//		queue1 = queue2;
//		queue2 = temp;
//		return val;
//		
//	}
//
//	/** Get the top element. */
//	public int top() {//时间复杂度O(1)
//		return top;
//	}
//
//	/** Returns whether the stack is empty. */
//	public boolean empty() {//queue1中包含了栈里的所有元素，所以只要检查queue1就行了
//		return queue1.isEmpty();
//	}
	
	
	private Queue<Integer> queue1;
	private Queue<Integer> queue2;

	/** Initialize your data structure here. */
	public MyStack() {
		queue1 = new LinkedList<>();
		queue2 = new LinkedList<>();

	}

	/** Push element x onto stack. */
	public void push(int x) {//时间复杂度O(1)
		queue1.add(x);
		while(!queue2.isEmpty()) {
			queue1.add(queue2.poll());
		}
		//交换
		Queue<Integer> temp = queue1;
		queue1 = queue2;
		queue2 = temp;
		
	}

	/** Removes the element on top of the stack and returns that element. */
	public int pop() {//时间复杂度O(N)
		return queue2.poll();
		
	}

	/** Get the top element. */
	public int top() {//时间复杂度O(1)
		return queue2.peek();
	}

	/** Returns whether the stack is empty. */
	public boolean empty() {//queue1中包含了栈里的所有元素，所以只要检查queue1就行了
		return queue2.isEmpty();
	}
}

/**
 * Your MyStack object will be instantiated and called as such: MyStack obj =
 * new MyStack(); obj.push(x); int param_2 = obj.pop(); int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
