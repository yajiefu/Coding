package swordForOffer;

import basic.ListNode;

/*
 * 题目：删除链表中重复的结点
 * 描述：在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 * 
 * 思路：遍历链表，找到重复的结点
 *     如果需要删除的结点位于链中
 *     如果需要删除的结点位于链尾
 * 
 * 
 */
public class DeleteDuplication {
	//
	public static ListNode deleteDuplication(ListNode pHead) {
		if (pHead == null || pHead.next == null) {
			return pHead;
		}
		//注意，该题是将重复的结点都删掉。
		//为了防止头节点可能就是重复节点，先设置一个新的头节点
		ListNode head = new ListNode(0);
		head.next = pHead;
		// pre指向的是不重复链表中最后一个节点
		ListNode pre = head;
		ListNode cur = pre.next;
		
		while(cur.next != null) {
			if (cur.next.val == cur.val) {
				// 此时不能将这两个节点删掉，因为cur.next.next有可能也是重复节点
				while(cur.next != null && cur.next.val == cur.val) {
					cur = cur.next;
				}
				// 此时，cur也是要删除的节点
				// 如果cur是尾节点的话
				if (cur.next == null) {
					pre.next = null;
				}
				//如果cur不是尾节点的话
				else {
					pre.next = cur.next;
					cur = pre.next;
				}
			}
			else {
				cur = cur.next;
				pre = pre.next;
			}
		}
		return head.next;
		
	}
	
	 public static void main(String[] args) {
		 ListNode aListNode = new ListNode(1);
			ListNode bListNode = new ListNode(2);
			ListNode cListNode = new ListNode(3);
			ListNode dListNode = new ListNode(4);
			ListNode eListNode = new ListNode(4);
			ListNode fListNode = new ListNode(6);
			ListNode gListNode = new ListNode(6);
			aListNode.next = bListNode;
			bListNode.next = cListNode;
			cListNode.next = dListNode;
			dListNode.next = eListNode;
			eListNode.next = fListNode;
			fListNode.next = gListNode;
			ListNode result = deleteDuplication(aListNode);
			while(result!= null) {
				System.out.println(result.val);
				result = result.next;
			}
	}
}
