package leetCode;

import basic.ListNode;

/**
 * 题目：148.排序链表
 * 描述：在 O(nlogn) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * 示例 1:
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 
 * 示例 2:
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * 
 * 思路：由于要求时间复杂度为O(nlogn)，自然想到归并排序，空间复杂度常数级，可见不能用递归。用bottom-to-up，这也是这题的难点
 * 归并排序需要找到中间值，我们可以用两个一快一慢的指针找到
 * 
 * @author yajie
 *
 */
public class SortList {
	//方法2：bottom-to-up。时间复杂度O(nlogn),空间复杂度O(1)
	public static ListNode sortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		//在很多链表的问题上，这个技巧得会
		ListNode pHead = new ListNode(0);
		pHead.next = head;
		//首先要知道链表的长度
		int len = 0;
		ListNode p = head;
		while(p != null) {
			len++;
			p = p.next;
		}
		
		
		int step = 1;
		while(step < len) {
			ListNode cur = pHead.next;
			ListNode tail = pHead;
			//一趟合并
			while(cur != null) {
				ListNode leftList = cur;
				ListNode rightList = cut(cur, step);
				cur = cut(rightList, step);
				
				tail.next = merge(leftList, rightList);
				while(tail.next != null) {
					tail = tail.next;
				}
			}
			step = step << 1;
		}
		return pHead.next;
	}
	
	/*
	 * cut(l,n):断链 将链表 l 切掉前 n个节点，并返回后半部分的链表头。
	 */
	
	public static ListNode cut(ListNode head, int  step) {
		
		ListNode p = head;
		while(--step > 0 && p != null) {
			p = p.next;
		}
		if (p == null) {
			return null;
		}
		ListNode next = p.next;//后半部分的链表头
		p.next = null;//前半部分断链
		return next;
	}
	
	
	
	
	//方法1：我们先用递归的方式实现。时间复杂度O(nlogn),空间复杂度O(nlogn)
	public static ListNode sortList1(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode fast = head.next;
		ListNode slow = head;
		
		while(fast.next != null) {
			slow = slow.next;
			fast = fast.next;
			if (fast.next != null) {
				fast = fast.next;
			}
		}
		//此时slow.next就是切割点
		ListNode mid = slow.next;
		slow.next = null;//断链
		
		ListNode leftList = sortList1(head);//左链
		ListNode rightList = sortList1(mid);
		//合并
		return merge(leftList, rightList);
		
	}
	
	public static ListNode merge(ListNode leftList, ListNode rightList) {
		ListNode head = new ListNode(0);
		ListNode res = head;
		while (leftList != null && rightList != null) {
			if (leftList.val < rightList.val) {
				head.next = leftList;
				leftList = leftList.next;
				
			}else {
				head.next = rightList;
				rightList = rightList.next;
			}
			head = head.next;
		}
		head.next = leftList == null ? rightList : leftList;
		return res.next;
	}
	
	public static void main(String[] args) {
		ListNode aListNode = new ListNode(4);
		ListNode bListNode = new ListNode(1);
		ListNode cListNode = new ListNode(3);
		ListNode dListNode = new ListNode(4);
		ListNode eListNode = new ListNode(7);
		ListNode fListNode = new ListNode(2);
		aListNode.next = bListNode;
		bListNode.next = cListNode;
		cListNode.next = dListNode;
		dListNode.next = eListNode;
		eListNode.next = fListNode;
		
		ListNode res = sortList(aListNode);
		while(res != null) {
			System.out.println(res.val);
			res = res.next;
		}
		
	}
}
