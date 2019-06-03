package leetCode;

import java.util.LinkedList;
import java.util.Queue;

import basic.TreeNode;

/*
 * 题目：111.二叉树的最小深度
 * 描述：给定一个二叉树，找出其最小深度。
 *     最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *     说明: 叶子节点是指没有子节点的节点
 */
public class MinDepth {
	// 方法1：递归
	public int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = minDepth(root.left);
		int right = minDepth(root.right);

		return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
	}
	
	// 方法2：层次遍历
	public int minDepthBFS(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int depth = 1;
		// 本层还剩节点数
		int count = 1;
		// 下一层节点数
		int nextCount = 0;
		while (!queue.isEmpty()) {
			TreeNode cur = queue.poll();
			count--;
			if (cur.left == null && cur.right == null) {
				return depth;
			}
			if (cur.left != null) {
				queue.add(cur.left);
				nextCount++;
			}
			if (cur.right != null) {
				queue.add(cur.right);
				nextCount++;
			}
			if (count == 0) {
				depth++;
				count = nextCount;
				nextCount = 0;
			}
		}
		return depth;
	}
}
