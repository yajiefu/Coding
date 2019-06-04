package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import basic.TreeNode;
/*
 * 题目：102.二叉树的层次遍历
 * 描述：给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 */
public class LevelOrder {
	public List<List<Integer>> levelOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
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
}
