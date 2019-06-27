package swordForOffer;

import basic.ListNode;

/*
 * 题目：链表中环的入口结点
 * 描述：给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 * 思路：有环，所以参考现实生活中绕着足球场跑步，跑的快的人会与慢的人相遇。相遇的点肯定在环里。
 *     1.找到相遇的点
 *     2.从相遇的点开始走，走到原位，即可得到环中一共有k节点
 *     3.指针1指向pHead,指针2比指针1快k步，然后同时往前走，当两者相遇时的节点就是入口节点
 */
public class EntryNodeOfLoop {
	public ListNode EntryNodeOfLoop(ListNode pHead) {
		if (pHead == null) {
			return null;
		}
		ListNode plow = pHead.next;
		if (plow == null) {
			return null;
		}
		// 记录快指针和慢指针相遇的节点
		ListNode flag = null;
		ListNode pfast = plow.next;
		while (pfast != null && plow != null) {
			if (pfast == plow) {
				flag = pfast;
				break;
			} else {
				plow = plow.next;
				pfast = pfast.next;
				if (pfast != null) {
					pfast = pfast.next;// pfast每次走两步
				}
			}
		}

		// 计算环的个数
		int count = 0;
		while (pfast.next != null) {
			count++;
			pfast = pfast.next;
			if (pfast == flag) {
				break;
			}

		}

		ListNode node1 = pHead;
		ListNode node2 = pHead;
		for (int i = 1; i <= count; i++) {
			node2 = node2.next;
		}

		while (node1 != node2) {
			node1 = node1.next;
			node2 = node2.next;
		}
		return node1;

	}
}
