package swordForOffer;

import basic.TreeNode;

/*
 * 题目：对称的二叉树
 * 描述：请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
public class IsSymmetrical {

	public static boolean isSymmetrical(TreeNode pRoot) {
		if (pRoot == null) {
			return true;
		}
		return isSymmetrical(pRoot.left, pRoot.right);

	}

	public static boolean isSymmetrical(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root1 == null || root2 == null) {
			return false;
		}
		if (root1.val != root2.val) {
			return false;
		}
		return isSymmetrical(root1.left, root2.right) && isSymmetrical(root1.right, root2.left);
	}

	public static void main(String[] args) {
		TreeNode aNode = new TreeNode(2);
		TreeNode bNode = new TreeNode(3);
		TreeNode cNode = new TreeNode(3);
		TreeNode dNode = new TreeNode(4);
		TreeNode eNode = new TreeNode(5);
		TreeNode fNode = new TreeNode(4);
		
		aNode.left = bNode;
		aNode.right = cNode;
		bNode.left = dNode;
		bNode.right = eNode;
		cNode.right = fNode;

	

		System.out.println(isSymmetrical(aNode));
	}
}
