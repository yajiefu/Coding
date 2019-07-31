package swordForOffer;

import basic.ListNode;
/*
 * 题目：合并两个排序的链表
 * 描述：输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 * 思路：递归与非递归
 */


public class MergeList {
	// 方法1：递归
	public static ListNode Merge1(ListNode list1,ListNode list2) {
		if (list1 == null) {
			return list2;
		}
    	if (list2 == null) {
			return list1;
		}
    	// 合并的新链表的表头
    	ListNode newList = null;
    	if(list1.val <= list2.val) {
    		newList = list1;
    		newList.next = Merge1(list1.next, list2);
    	}else {
    		newList = list2;
    		newList.next = Merge1(list1, list2.next);
		}
    	return newList;
	}
	// 方法2：非递归
    public static ListNode Merge(ListNode list1,ListNode list2) {
    	if (list1 == null) {
			return list2;
		}
    	if (list2 == null) {
			return list1;
		}
    	// 合并的新链表的表头
    	ListNode newList = null;
    	// 新链表中的当前最新的节点
    	ListNode curNode = null;
    	while(list1 != null && list2 != null) {
    		if (list1.val <= list2.val) {
    			if (newList == null) {
					newList = curNode = list1;
				}else {
					curNode.next = list1;
					curNode = curNode.next;
				}
    			list1 = list1.next;
			}else {
				if (newList == null) {
					newList = curNode = list2;
				}else {
					curNode.next = list2;
					curNode = curNode.next;
				}
    			list2 = list2.next;
			}
    	}
    	if (list1 != null) {
			curNode.next = list1;
		}
    	if (list2 != null) {
			curNode.next = list2;
		}
		return newList;
    
        
    }
    //方法2的另一种写法。将表头和中间的节点统一处理。
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}
		
		ListNode p1 = l1;
		ListNode p2 = l2;
		//新链表的表头
		ListNode head = new ListNode(0);
		ListNode cur = head;
		while(p1 != null && p2 != null) {
			if (p1.val <= p2.val) {
				cur.next = p1;
				p1 = p1.next;
			}else {
				cur.next = p2;
				p2 = p2.next;
			}
			cur = cur.next;
		}
		
		while(p1 != null) {
			cur.next = p1;
			cur = cur.next;
			p1 = p1.next;
		}
		
		while(p2 != null) {
			cur.next = p2;
			cur = cur.next;
			p2 = p2.next;
		}
		return head.next;
	}
    public static void main(String[] args) {
    	ListNode aListNode = new ListNode(0);
		ListNode bListNode = new ListNode(5);
		ListNode cListNode = new ListNode(6);
		ListNode dListNode = new ListNode(20);
		ListNode eListNode = new ListNode(33);
		ListNode fListNode = new ListNode(65);
		aListNode.next = bListNode;
		bListNode.next = cListNode;
		cListNode.next =dListNode;
		dListNode.next = eListNode;
		eListNode.next = fListNode;
		
		ListNode aaListNode = new ListNode(1);
		ListNode bbListNode = new ListNode(2);
		ListNode ccListNode = new ListNode(4);
		ListNode ddListNode = new ListNode(31);
		ListNode eeListNode = new ListNode(34);
		ListNode ffListNode = new ListNode(67);
		aaListNode.next = bbListNode;
		bbListNode.next = ccListNode;
		ccListNode.next = ddListNode;
		ddListNode.next = eeListNode;
		eeListNode.next = ffListNode;
		
		ListNode result = Merge1(aListNode, aaListNode);
		while(result != null) {
			System.out.println(result.val);
			result = result.next;
		}
		
	}
}
