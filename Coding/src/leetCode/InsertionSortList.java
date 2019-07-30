package leetCode;
/**
 * 题目：147.对链表进行插入排序
 * 描述：插入排序算法：
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * 示例 1：
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2：
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 */
import basic.ListNode;

public class InsertionSortList {
	public ListNode insertionSortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode pHead = new ListNode(-1);//表头指针
		pHead.next = head;
		ListNode pre = pHead;//每次比较开始的指针
		ListNode cur = head;//当前未排序的指针，在这之前都是已经排过序了
		while(cur != null) {
			ListNode temp = cur.next;//始终指向cur.next，保存这个节点
			//找到前面已经排过序的节点里比cur.val大的节点
			while(pre.next != null && (pre.next.val < cur.val)) {
				pre = pre.next;
			}
			//此时pre.next.val>=cur.val
			cur.next = pre.next;
			pre.next = cur;
			//此时已经排好序了。
			cur = temp;
			pre = pHead;//pre重新回到表头。
			
		}
		return pHead.next;

	}
}
