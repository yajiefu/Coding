package swordForOffer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

import basic.TreeNode;

/*
 * 题目：按之字形顺序打印二叉树
 * 描述：请实现一个函数按照之字形打印二叉树，
 *             即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 *             
 *思路：相较于[把二叉树打印成多行]，该题的奇数行从左往右，偶数行从右往左。上题用的是队列，本题我们用双端队列
 *
 *1.首先生成双端队列deque，将根节点从头部入队
 *2.如果是从左往右的过程：一律从头部弹出节点，如果有孩子，从尾部依次进入左孩子和右孩子：如第1,3,5,层
 *3.如果是从右往左的过程：一律从尾部弹出节点，如果有孩子，从头部依次进入右孩子和左孩子：如第2,4,6层
 *   
 */
public class Print2 {
	public static ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if (pRoot == null) {
			return result;
		}

		Deque<TreeNode> deque = new LinkedList<>();
		deque.addFirst(pRoot);
		// flag 为true是奇数行
		boolean flag = true;
		// 本行剩余个数
		int count = 1;
		// 下一行个数
		int nextCount = 0;
		while (!deque.isEmpty()) {
			// 如果是奇数行，从左往右打印，一律从头部弹出节点，如果有孩子，从尾部依次进入左孩子和右孩子
			if (flag) {
				TreeNode node = deque.pollFirst();
				list.add(node.val);
				count--;
				if (node.left != null) {
					deque.addLast(node.left);
					nextCount++;
				}
				if (node.right != null) {
					deque.addLast(node.right);
					nextCount++;
				}
			} else {
				// 如果是偶数行，从右往左打印，一律从尾部弹出节点，如果有孩子，从头部依次进入右孩子和左孩子
				TreeNode node = deque.pollLast();
				list.add(node.val);
				count--;
				if (node.right != null) {
					deque.addFirst(node.right);
					nextCount++;
				}
				if (node.left != null) {
					deque.addFirst(node.left);
					nextCount++;
				}

			}

			if (count == 0) {
				result.add(list);
				list = new ArrayList<>();
				flag = !flag;
				count = nextCount;
				nextCount = 0;
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
