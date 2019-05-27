package swordForOffer;

import java.util.Stack;
/*
 * 题目：包含min函数的栈
 * 描述：定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数，
 *     在该栈中，push,pop,min的时间复杂度应为O（1）
 */
public class StackWinMin {

	private Stack<Integer> stack = new Stack<>();
	// 辅助栈
	private Stack<Integer> minStack = new Stack<>();
	private int min = Integer.MAX_VALUE;
	
	public void push(int node) {
	
		stack.push(node);
		if (node < min) {
			min = node;
			minStack.push(min);
		}
		else {
			minStack.push(min);
		}
		
	}
	    
	public void pop() {
	    stack.pop();
	    minStack.pop();
	}
	    
	public int top() {
		minStack.pop();
		return stack.pop();
	}
	    
	public int min() {
		return minStack.peek();
	}
	
}
