package swordForOffer;

import basic.TreeNode;

/*
 * 题目：树的子结构
 * 描述：输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 * 
 * 思路：
 * 1.在A中找到和B的根节点值一样的节点R
 * 2.判断以R为根节点的结构是否和B一样
 */
public class HasSubtree {
	public static boolean hasSubtree(TreeNode root1, TreeNode root2) {
		boolean result = false;
		if (root1 != null && root2 != null) {
			if (root1.val == root2.val) {
				 //继续判断后序的结构是否相同
				result = doesTree1HasTree2(root1, root2);
			}
			// 如果不相同，就依次比较左孩子和右孩子，看是否相同
			if (!result) {
				result = hasSubtree(root1.left, root2);
			}
			if (!result) {
				result = hasSubtree(root1.right, root2);
			}
		}
		return result;
	}

	public static boolean doesTree1HasTree2(TreeNode root1, TreeNode root2) {
		if (root2 == null) {
			return true;
		}
		if (root1 == null) {
			return false;
		}
		if (root1.val != root2.val) {
			return false;
		}
		return doesTree1HasTree2(root1.left, root2.left) && doesTree1HasTree2(root1.right, root2.right);

	}

	public static void main(String[] args) {
		TreeNode aNode = new TreeNode(8);
		TreeNode bNode = new TreeNode(8);
		TreeNode cNode = new TreeNode(7);
		TreeNode dNode = new TreeNode(9);
		TreeNode eNode = new TreeNode(2);
		TreeNode fNode = new TreeNode(4);
		TreeNode gNode = new TreeNode(7);

		aNode.left = bNode;
		aNode.right = cNode;
		bNode.left = dNode;
		bNode.right = eNode;
		eNode.left = fNode;
		eNode.right = gNode;

		TreeNode bbNode = new TreeNode(8);
		TreeNode ccNode = new TreeNode(9);
		TreeNode ddNode = new TreeNode(2);
		bbNode.left = ccNode;
		bbNode.right = ddNode;

		System.out.println(hasSubtree(aNode, bbNode));
	}
}
