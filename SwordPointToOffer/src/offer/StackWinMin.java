package offer;

import java.util.Stack;

public class StackWinMin {

	private Stack<Integer> stack = new Stack<>();
	// ¸¨ÖúÕ»
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
