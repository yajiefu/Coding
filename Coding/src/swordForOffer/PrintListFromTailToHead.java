package swordForOffer;
import basic.ListNode;
import java.util.ArrayList;
import java.util.Stack;

/**
 *   ��Ŀ�� ��β��ͷ��ӡ����
 *    ����: ����һ������������ֵ��β��ͷ��˳�򷵻�һ��ArrayList��
 * @author yajie
 *
 *
 * ˼·������1���Ƚ������ջ
 *           ����2����Ȼ�뵽��ջ�����ݹ��ڱ����Ͼ���һ��ջ�ṹ�����õݹ�ʵ�֡�
 *      ���и����⣺������ǳ�����ʱ�򣬾ͻᵼ�º������õĲ㼶����Ӷ��п��ܵ��º�������ջ�������˷���1�Ϻã�
 */
public class PrintListFromTailToHead {

	
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
 	    Stack<Integer> stack = new Stack<>();
    	
    	while(listNode.next != null) {
    		stack.push(listNode.val);
    		listNode = listNode.next;
    	}
    	
    	ArrayList<Integer> arrayList = new ArrayList<>();
    	while(! stack.isEmpty()) {
    		arrayList.add(stack.pop());
    	}
		return arrayList;
    }
    
    public static void main(String[] args) {
		ListNode a = new ListNode(0);
		ListNode b = new ListNode(1);
		ListNode c = new ListNode(2);
		ListNode d = new ListNode(3);
		ListNode e = new ListNode(4);
		ListNode f = new ListNode(5);
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		e.next = f;
		ArrayList<Integer> arrayList = new ArrayList<>();
		arrayList = printListFromTailToHead(a);
		for(int val : arrayList) {
			System.out.println(val);
		}
	}
}
