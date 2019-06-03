package swordForOffer;

import java.util.LinkedList;
import java.util.Queue;

import basic.TreeNode;

/*
 * 题目：二叉树的深度
 * 描述：输入一棵二叉树，求该树的深度。
 *     从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 * 思路：方法1：递归，求左右子树的深度
 *     方法2：求二叉树的深度，可以用层次遍历
 */
public class TreeDepth { 
	// 方法1：递归
	public static int treeDepth1(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = treeDepth(root.left);
		int right = treeDepth(root.right);
		return Math.max(left, right) + 1;
        
    }
	// 方法2：层次遍历
	public static int treeDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int depth = 0;
		// 本层次剩余个数
		int count = 1;
		// 下一层个数
		int nextCount = 0;
		while(!queue.isEmpty()) {
			//出栈
			TreeNode node = queue.remove();
			count--;
			if (node.left != null) {
				queue.add(node.left);
				nextCount++;
			}
			if (node.right != null) {
				queue.add(node.right);
				nextCount++;
			}
			if (count == 0) {
				// 本层次输出完
				depth++;
				count = nextCount;
				nextCount = 0;
			}
		}
		return depth;
		
	}
	public static void main(String[] args) {
		TreeNode aNode = new TreeNode(1);
		TreeNode bNode = new TreeNode(2);
		TreeNode cNode = new TreeNode(3);
		TreeNode dNode = new TreeNode(4);
		TreeNode eNode = new TreeNode(5);
		TreeNode fNode = new TreeNode(6);
		TreeNode gNode = new TreeNode(7);
		TreeNode hNode = new TreeNode(8);
		
		aNode.left = bNode;
		aNode.right = cNode;
		bNode.left = dNode;
		bNode.right = eNode;
		cNode.right = fNode;
		eNode.left = gNode;
		eNode.right = hNode;
		
		System.out.println(treeDepth(aNode));
	}
}
