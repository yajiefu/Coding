package swordForOffer;
import java.util.Stack;
/*
 * 题目：用两个栈实现队列
 * 描述：用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * 
 * 思路：入栈：入stack1
 *     出栈：如果stack2中没有元素，将stack1中的元素依次出栈进入stack2中，返回stack2栈顶元素
 *         如果stack2中有元素，就返回stack2栈顶元素。
 */
public class QueueByStack {
	
	Stack<Integer> stack1 = new Stack<>();
	Stack<Integer> stack2 = new Stack<>();
	
	public void push(int node) {
		stack1.push(node);
	}
	
	public int pop() {
		if (stack2.isEmpty()) {
			while(!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
		return stack2.pop();
	}
	
}
