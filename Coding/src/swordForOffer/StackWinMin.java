package swordForOffer;

import java.util.Stack;
/*
 * ��Ŀ������min������ջ
 * ����������ջ�����ݽṹ�����ڸ�������ʵ��һ���ܹ��õ�ջ��������СԪ�ص�min������
 *     �ڸ�ջ�У�push,pop,min��ʱ�临�Ӷ�ӦΪO��1��
 */
public class StackWinMin {

	private Stack<Integer> stack = new Stack<>();
	// ����ջ
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
