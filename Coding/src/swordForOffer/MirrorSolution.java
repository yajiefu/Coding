package swordForOffer;

import basic.TreeNode;

/*
 * 题目：二叉树的镜像
 * 描述：操作给定的二叉树，将其变换为源二叉树的镜像。
 * 思路：交换左右子树
 */
public class MirrorSolution {

	public void Mirror(TreeNode root) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			return;
		}
		
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		
		if (root.left != null) {
			Mirror(root.left);
		}
		if (root.right != null) {
			Mirror(root.right);
		}
		

	}

	public static void main(String[] args) {
		TreeNode aNode = new TreeNode(8);
		TreeNode bNode = new TreeNode(6);
		TreeNode cNode = new TreeNode(10);
		TreeNode dNode = new TreeNode(5);
		TreeNode eNode = new TreeNode(7);
		TreeNode fNode = new TreeNode(9);
		TreeNode gNode = new TreeNode(11);

		aNode.left = bNode;
		aNode.right = cNode;
		bNode.left = dNode;
		bNode.right = eNode;
		cNode.left = fNode;
		cNode.right = gNode;
//			preOrderTraverse1(aNode);
//			mirrorTree(aNode);
//			System.out.println();
//			preOrderTraverse1(aNode);
	}
}
