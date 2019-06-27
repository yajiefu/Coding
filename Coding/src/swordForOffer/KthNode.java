package swordForOffer;

import basic.TreeNode;

/*
 * 题目：二叉搜索树的第k个结点
 * 描述：给定一棵二叉搜索树，请找出其中的第k小的结点。
 *     例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
 * 思路：中序遍历。
 */
public class KthNode {
	int k;
	TreeNode kthNode(TreeNode pRoot, int k)
    {
		this.k = k;
		return inOrderReverse(pRoot);
        
    }
	
	public TreeNode inOrderReverse(TreeNode pRoot) {
		TreeNode temp = null;
		if (pRoot != null) {
			if ((temp = inOrderReverse(pRoot.left)) != null) {
				return temp;
			}
			if (--k == 0) {
				return pRoot;
			}
			if ((temp = inOrderReverse(pRoot.right)) != null) {
				return temp;
			}
		}
		return temp;
	}
}
