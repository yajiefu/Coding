package swordForOffer;

import java.util.Stack;

/*
 * ��Ŀ��ջ��ѹ�뵯������
 * ���������������������У���һ�����б�ʾջ��ѹ��˳�����жϵڶ��������Ƿ����Ϊ��ջ�ĵ���˳��
 *     ����ѹ��ջ���������־�����ȡ���������1,2,3,4,5��ĳջ��ѹ��˳������4,5,3,2,1�Ǹ�ѹջ���ж�Ӧ��һ���������У�
 *     ��4,3,5,1,2�Ͳ������Ǹ�ѹջ���еĵ������С�
 *    ��ע�⣺���������еĳ�������ȵģ�
 *    
 * ˼·���ܽ�һ����ջ��ջ�Ĺ��̣����ǿ����ҵ��ж�һ�������ǲ���ջ�ĵ������й��ɣ�
 *     �����һ�����������պ���ջ��Ԫ�أ�ֱ�ӵ�����������ǣ����ѹջ�����л�û����ջ��������ջ��ֱ������һ����Ҫ����������ѹ��ջ����
 *     ������е����ֶ���ջ����Ȼû���ҵ���һ�����������֣�������в���һ���������С�
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
