package swordForOffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import basic.TreeNode;
/*
 *  题目：从上往下打印二叉树
 *  描述：从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 */
public class PrintFromTopToBottom {

	
	public static ArrayList<Integer> printFromTopToBottom(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			// 出栈
			TreeNode node = queue.poll();
			result.add(node.val);
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
		return result;
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
		
		ArrayList<Integer> arrayList = printFromTopToBottom(aNode);
		for (Integer integer : arrayList) {
			System.out.println(integer);
		}
	}
}
