package swordForOffer;


import basic.ListNode;

/**
 * 题目：链表中倒数第k个结点
 * 描述：输入一个链表，输出该链表中倒数第k个结点。
 * 思路：设置两个指针，第一个指针先移动k-1,然后两个指针同时移动，当第一个指针到达尾节点时，另一个指针刚好的在倒数第k个.
 * 注意：当k大于链表的长度时，我们返回的是null,如果题目要求可以循环，那就是k = k % len(链表的长度)，见注释的部分。
 *
 */
public class FindKthToTail {

	public static ListNode findKthToTail(ListNode head,int k) {
		if (head == null  || k <= 0 ) {
			return null;
		}
		// 计算链表的长度
//		int len = 0;
//		ListNode pHead = head;
//		while(pHead != null) {
//			len++;
//			pHead = pHead.next;
//		}
//		
//		int kth = k % len;
		
		// pA先于pB前k-1的位置，当pA在链尾的位置时，pB就位于倒数第k个位置
		ListNode pA = head;
		ListNode pB = head;
		while(k-- > 1) {
			if (pA.next != null) {
				pA = pA.next;
			}else {
				return null;
			}
		}
		
		while(pA.next != null) {
			pA = pA.next;
			pB = pB.next;
		}
		return pB;
	
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
		ListNode result = findKthToTail(aListNode, 8);
		if (result != null) {
			System.out.println(result.val);
		}else {
			System.out.println(result);
		}
		
		}
}
