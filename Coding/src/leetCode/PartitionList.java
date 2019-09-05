package leetCode;

import basic.ListNode;
/**
 * 题目：725.分隔链表
 * 描述：给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 * 示例:
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 * @author yajie
 *
 */
public class PartitionList {
	public static ListNode partition(ListNode head, int x) {
		ListNode dummy1 = new ListNode(0);
		ListNode dummy2 = new ListNode(0);
		ListNode small = dummy1;
		ListNode big = dummy2;

		ListNode curNode = head;
		while (curNode != null) {
			if (curNode.val < x) {
				small.next = curNode;
				small = small.next;
			} else {
				big.next = curNode;
				big = big.next;
			}
			curNode = curNode.next;
		}
		if (dummy1.next == null) {
			return dummy2.next;
		} else {
			big.next = null;
			small.next = dummy2.next;
			return dummy1.next;
		}

	}

	public static void main(String[] args) {
		ListNode aListNode = new ListNode(9);
		ListNode bListNode = new ListNode(6);
		ListNode cListNode = new ListNode(3);

		ListNode dListNode = new ListNode(7);
		ListNode eListNode = new ListNode(6);
		ListNode fListNode = new ListNode(5);
		ListNode gListNode = new ListNode(9);
		aListNode.next = bListNode;
		bListNode.next = cListNode;
		cListNode.next = dListNode;
		dListNode.next = eListNode;
		eListNode.next = fListNode;
		fListNode.next = gListNode;

		ListNode newH = partition(aListNode, 4);

		while (newH != null) {
			System.out.print(newH.val + " ");
			newH = newH.next;
		}

	}

}
