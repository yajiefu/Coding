package swordForOffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import basic.TreeNode;

/*
 * 题目：把二叉树打印成多行
 * 描述：从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 * 
 * 相较于[从上往下打印二叉树]，该题要分行打印
 */
public class Print {
	public static ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {

		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if (pRoot == null) {
			return result;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(pRoot);
		// 本行剩余个数
		int count = 1;
		// 下一行个数
		int nextCount = 0;
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			count--;
			list.add(node.val);
			if (node.left != null) {
				queue.add(node.left);
				nextCount++;
			}
			if (node.right != null) {
				queue.add(node.right);
				nextCount++;
			}
			if (count == 0) {
				// 本行结束
				result.add(list);
				count = nextCount;
				nextCount = 0;
				// 注意点
				list = new ArrayList<>();
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

		ArrayList<ArrayList<Integer>> result = print(aNode);
		for (ArrayList<Integer> arrayList : result) {
			for (Integer val : arrayList) {
				System.out.print(val);

			}
			System.out.println();
		}
	}

}
