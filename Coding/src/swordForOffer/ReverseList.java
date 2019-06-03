package swordForOffer;

import basic.ListNode;
/*
 * 题目：反转链表
 * 描述：输入一个链表，反转链表后，输出新链表的表头。
 * 思路：方法1：递归
 *     方法2：设置三个指针从前往后反转链表
 */

 
public class ReverseList {

	// 方法1：递归。
	public static ListNode reverseList1(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		// 先反转后面的链表
		ListNode nextList = reverseList(head.next);
		// 将当前节点设置为后续链表的下一个节点。注意后面的链表，nextList指向的是新链表的表头，新链表中的最后一个节点是head.next
		// nextList.next = head; 这种写法是错误的
		head.next.next = head;
		head.next = null;
		return nextList;
		
	}
	
	// 方法2：从头到尾开始反转，假设前面是已经反转好的。
	
	public static ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		// 指向当前需要反转的节点的指针
		ListNode node = head;
		// 指向当前节点的前一个指针
		ListNode preNode = null;
		// 指向当前节点的下一个指针
		ListNode nextNode = node.next;
		//新链表
		ListNode newList = null;
		
		// 在当前节点不为空的情况下执行
		while(node != null) {
			nextNode = node.next;
			if (nextNode == null) {
				// 说明当前节点node是尾节点
				// 注意，此时node的下一个节点还未指向preNode,但是newList的指针已经指向node了。
				newList = node;
			}
			// 反转链表
			node.next = preNode;
			preNode = node;
			node = nextNode;
		}
		
		return newList;
		
	}
	public static void main(String[] args) {
		ListNode aListNode = new ListNode(0);
		ListNode bListNode = new ListNode(1);
		ListNode cListNode = new ListNode(2);
		ListNode dListNode = new ListNode(3);
		ListNode eListNode = new ListNode(4);
		ListNode fListNode = new ListNode(5);
		aListNode.next = bListNode;
		bListNode.next = cListNode;
		cListNode.next =dListNode;
		dListNode.next = eListNode;
		eListNode.next = fListNode;
		System.out.println(reverseList(aListNode).val);
	}
}
