package swordForOffer;

import java.util.Stack;

import basic.ListNode;

/*
 public class ListNode{
	public int val;
	public ListNode next = null;
	
	public ListNode(int val){
		this.val = val;
	}
}
 */
/*
 * 题目：两个链表的第一个公共结点
 * 描述：输入两个链表，找出它们的第一个公共结点。
 * 思路：可以先画出它们的结构，知道它们是Y字形结构。后面链表尾部是一样的。基于这种结构，可以发现
 * 方法1：利用栈
 * 
 */
public class FindFirstCommonNode {
	// 方法1：栈 时间复杂度O(m+n) ,空间复杂度O(m+n)
	public static ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
		if (pHead1 == null || pHead1 == null) {
			return null;
		}
		Stack<ListNode> stack1 = new Stack<>();
		Stack<ListNode> stack2 = new Stack<>();
		while (pHead1 != null) {
			stack1.push(pHead1);
			pHead1 = pHead1.next;
		}
		while (pHead2 != null) {
			stack2.push(pHead2);
			pHead2 = pHead2.next;
		}
		ListNode commonNode = null;
		while (!stack1.isEmpty() && !stack2.isEmpty() && stack1.peek() == stack2.peek()) {
			commonNode = stack1.peek();
			stack1.pop();
			stack2.pop();
		}
		return commonNode;

	}

	// 方法2:由于有共同的尾节点，所以我们可以先遍历得到他们的长度，然后长的先走，第一个得到的相同的节点就是第一个公共的节点
	// 时间复杂度O(m+n) ,空间复杂度O(1)
	public static ListNode findFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
		if (pHead1 == null || pHead1 == null) {
			return null;
		}
		ListNode p1 = pHead1;
		ListNode p2 = pHead2;
		int length1 = 0;
		int length2 = 0;
		while (p1 != null) {
			length1++;
			p1 = p1.next;
		}
		while (p2 != null) {
			length2++;
			p2 = p2.next;
		}
		p1 = pHead1;
		p2 = pHead2;
		boolean flag = length1 > length2;
		int steps = flag ? length1 - length2 : length2 - length1;
		while(steps > 0) {
			if (flag) {
				p1 = p1.next;
			}else {
				p2 = p2.next;
			}
			steps--;
		}
		ListNode commonNode = null;
		while (p1 != null && p2 != null) {
			if (p1 == p2) {
				commonNode = p1;
				break;
			}
			p1 = p1.next;
			p2 = p2.next;
		}
		return commonNode;

	}

	public static void main(String[] args) {
		ListNode aListNode = new ListNode(1);
		ListNode bListNode = new ListNode(2);
		ListNode cListNode = new ListNode(3);
		ListNode dListNode = new ListNode(4);
		ListNode eListNode = new ListNode(5);
		ListNode fListNode = new ListNode(6);
		ListNode gListNode = new ListNode(7);

		aListNode.next = bListNode;
		bListNode.next = cListNode;
		dListNode.next = eListNode;
		eListNode.next = fListNode;
		cListNode.next = fListNode;
		fListNode.next = gListNode;

		ListNode result = findFirstCommonNode(aListNode, dListNode);
		ListNode result2 = findFirstCommonNode2(aListNode, dListNode);

		System.out.println(result.val);
		System.out.println(result2.val);

	}
}
