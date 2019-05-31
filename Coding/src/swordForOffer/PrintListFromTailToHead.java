package swordForOffer;
import basic.ListNode;
import java.util.ArrayList;
import java.util.Stack;

/**
 *   题目： 从尾到头打印链表
 *    描述: 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 * @author yajie
 *
 *
 * 思路：方法1：先进后出，栈
 *           方法2：既然想到了栈，而递归在本质上就是一个栈结构。故用递归实现。
 *      （有个问题：当链表非常长的时候，就会导致函数调用的层级很深，从而有可能导致函数调用栈溢出。因此方法1较好）
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
