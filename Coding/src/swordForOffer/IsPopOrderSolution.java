package swordForOffer;

import java.util.Stack;

/*
 * 题目：栈的压入弹出序列
 * 描述：输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 *     假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 *     但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 *    （注意：这两个序列的长度是相等的）
 *    
 * 思路：总结一下入栈出栈的过程，我们可以找到判断一个序列是不是栈的弹出序列规律：
 *     如果下一个弹出的数刚好是栈顶元素，直接弹出；如果不是，则把压栈序列中还没有入栈的数字入栈，直到把下一个需要弹出的数字压入栈顶；
 *     如果所有的数字都入栈后仍然没有找到下一个弹出的数字，则该序列不是一个弹出序列。
 */
public class IsPopOrderSolution {
	public static boolean IsPopOrder(int [] pushA,int [] popA) {
		if (pushA.length <= 0 || popA.length <= 0 ) {
			return false;
		}
		
		Stack<Integer> stack = new Stack<>();
		int j = 0;
		for(int i = 0; i < pushA.length; i++) {
			stack.push(pushA[i]);
			while ((!stack.isEmpty()) && stack.peek() == popA[j]) {
				stack.pop();
				j++;
			}
		}
		
		return stack.empty();
		  
	}
	public static void main(String[] args) {
		int[] push = {1,2,3,4,5};
		int[] pop = {4,5,3,2,1};
		int[] popA = {4,3,5,1,2};
		System.out.println(IsPopOrder(push, pop));
		System.out.println(IsPopOrder(push, popA));
	}
}
