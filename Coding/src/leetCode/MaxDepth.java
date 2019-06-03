package leetCode;

import java.util.LinkedList;
import java.util.Queue;

import basic.TreeNode;

public class MaxDepth {
	// 方法1：递归
	public int maxDepth1(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = maxDepth1(root.left);
		int right = maxDepth1(root.right);

		return Math.max(left, right) + 1;
	}

	// 方法2：层次遍历
	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int depth = 0;
		// 本层还剩节点数
		int count = 1;
		// 下一层节点数
		int nextCount = 0;
		while (!queue.isEmpty()) {
			TreeNode cur = queue.poll();
			count--;
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
