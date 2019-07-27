package swordForOffer;

import java.util.Stack;
/*
 * 题目：包含min函数的栈
 * 描述：定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数，
 *     在该栈中，push,pop,min的时间复杂度应为O（1）
 *     
 */
public class StackWinMin {
	private Stack<Integer> stack = new Stack<>();
	private Stack<Integer> minStack = new Stack<>();
	
	
	public void push(int node) {
		stack.push(node);
		if (minStack.isEmpty() || minStack.peek() >= node) {
			minStack.push(node);
		} else {
			minStack.push(minStack.peek());
		}
	}
     
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int min() {
        return minStack.peek();
    }
}
