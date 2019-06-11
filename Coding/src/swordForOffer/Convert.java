package swordForOffer;

import basic.TreeNode;

/*
 * 题目：二叉搜索树与双向链表
 * 描述：输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 *     要求不能创建任何新的结点，只能调整树中结点指针的指向。
 * 
 * 思路：二叉搜索树，左小于右。
 *     将二叉搜索树转换成排序双向链表，原先指向左子节点的指针调整为链表中指向前一个节点的指针，右-后。
 *     排好序的，因此是中序遍历。由于遍历和转换过程一样，那就用递归。
 *     
 *     难点：例如：根节点的前一个节点是它的左子树最后一个节点，如何做？
 *         可以定义一个指针pre指向每次排好序 的最后一个节点

 */
public class Convert {
	// 每个子树排序后的最后一个节点
	private TreeNode pre = null;
	// 排序的第一个节点
	private TreeNode head = null;

	public TreeNode convert(TreeNode pRootOfTree) {
		convertCore(pRootOfTree);
		return head;
	}

	public void convertCore(TreeNode curNode) {
		if (curNode == null) {
			return;
		}
		// 排序左子树
		convertCore(curNode.left);
		// 如果没有左子树,第一个节点就是它
		if (head == null) {
			pre = curNode;
			head = curNode;
		} else {
			// 如果有左子树,当前节点就和前一个已经排好序的节点相连
			pre.right = curNode;
			curNode.left = pre;
			// 在已经排好序的链表里，现在最后一个节点是cur了
			pre = curNode;
		}

		// 排序右子树
		convertCore(curNode.right);
	}

}
