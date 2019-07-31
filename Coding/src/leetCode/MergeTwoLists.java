package leetCode;
/**
 * 题目：21. 合并两个有序链表
 * 描述：将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
import basic.ListNode;

public class MergeTwoLists {
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

}
