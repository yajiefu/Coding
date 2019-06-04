package leetCode;

import basic.ListNode;
/*
 * 题目：206.反转链表
 */
public class ReverseList {
	
    // 方法1：递归
    // 执行用时 : 1 ms, 在Reverse Linked List的Java提交中击败了63.05% 的用户
    // 内存消耗 : 35.5 MB, 在Reverse Linked List的Java提交中击败了58.54% 的用户
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode newList = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        
        return newList;
    }

    // 方法2：迭代：从头到尾开始反转，假设前面是已经反转好的。
    // 执行用时 : 1 ms, 在Reverse Linked List的Java提交中击败了63.05% 的用户
    // 内存消耗 : 35.1 MB, 在Reverse Linked List的Java提交中击败了74.24% 的用户
     public ListNode reverseList2(ListNode head) {
       
         // 前指针
         ListNode preNode = null;
         // 当前指针
         ListNode curNode = head;
         while(curNode != null){
             ListNode nextNode = curNode.next;
             curNode.next = preNode;
             preNode = curNode;
             curNode = nextNode;
         }
         return preNode;
     }
}
