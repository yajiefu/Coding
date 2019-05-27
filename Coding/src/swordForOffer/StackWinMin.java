package swordForOffer;

import java.util.Stack;
/*
 * ��Ŀ������min������ջ
 * ����������ջ�����ݽṹ�����ڸ�������ʵ��һ���ܹ��õ�ջ��������СԪ�ص�min������
 *     �ڸ�ջ�У�push,pop,min��ʱ�临�Ӷ�ӦΪO��1��
 */
public class StackWinMin {
	// ��һ������ջ������СԪ��
	private Stack<Integer> stack = new Stack<>();
	// ����ջ
	private Stack<Integer> minStack = new Stack<>();
	
	private int min = Integer.MAX_VALUE;
	
	// ��ջ
    public void push(int node) {
    	 stack.push(node);
    	 if (node < min) {
			min = node;
			minStack.push(min);
		}else {
			minStack.push(min);
		}
    }
     
    // ��ջ
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    
    // ջ��Ԫ��
    public int top() {
        return stack.peek();
    }
    
    // ��СԪ��
    public int min() {
        return minStack.peek();
    }
}
