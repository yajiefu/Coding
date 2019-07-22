package leetCode;

import basic.ListNode;
/**
 * 题目：2.两数相加
 * 描述：给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 *            输出：7 -> 0 -> 8
 *            原因：342 + 465 = 807
 *            
 *思路：模拟两数相加的过程。
 * @author yajie
 *
 */
public class AddTwoNumbers {
	//方法1：模拟两数相加，和addTwoNumbers0思路一样
	//时间复杂度：O(max(m, n))，假设 m和 n分别表示 l1 和 l2 的长度
	//空间复杂度：O(max(m, n))， 新列表的长度最多为max(m,n) + 1
	public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		ListNode p = l1;
		ListNode q = l2;
		ListNode cur = head;
		
		int carry = 0;//保存进位
		int sum = 0;
		while(p != null && q != null) {
			sum = carry + p.val + q.val;
			carry = sum / 10;
			cur.next = new ListNode(sum % 10);
			cur = cur.next;
			p = p.next;
			q = q.next;
		}
		while(p != null) {
			sum = carry + p.val;
			carry = sum / 10;
			cur.next = new ListNode(sum % 10);
			cur = cur.next;
			p = p.next;
			
		}
		while(q != null) {
			sum = carry + q.val;
			carry = sum / 10;
			cur.next = new ListNode(sum % 10);
			cur = cur.next;
			q = q.next;
			
		}
		//可能最后一个节点也进位了。
		if (carry > 0) {
			cur.next = new ListNode(carry);
		}
		return head.next;

	}
	//方法1：超出时间限制
	public ListNode addTwoNumbers0(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		ListNode p = l1;
		ListNode q = l2;
		ListNode cur = head;
		
		int carry = 0;//保存进位
		int x = 0;
		int y = 0;
		int sum = 0;
		while(p != null || q != null) {
			x = p == null ? 0 : p.val;
			y = q == null ? 0 : q.val;
			sum = carry + x + y;
			carry = sum / 10;
			cur.next = new ListNode(sum % 10);
			cur = cur.next;
			if (p.next != null) {
				p = p.next;
			}
			if (p.next != null) {
				q = q.next;
			}
		}
		//可能最后一个节点也进位了。
		if (carry > 0) {
			cur.next = new ListNode(carry);
		}
		return head.next;

	}
}
