package basic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：重建二叉树II 
 * 描述：输入某二叉树的中序遍历和后序遍历的结果，请重建出该二叉树。 假设输入的中序遍历的结果中都不含重复的数字。
 * 例如输入中序遍历序列{4,7,2,1,5,3,8,6}，后序遍历序列{7,4,2,5,8,6,3,1}，则重建二叉树并返回。
 * 
 * @author yajie
 *
 */
public class ReConstructBinaryTreeII {
	public static TreeNode reConstructBinaryTree(int[] in, int[] post) {
		TreeNode root = reConstructBinaryTree(in, 0, in.length - 1, post, 0, post.length - 1);
		return root;

	}

	private static TreeNode reConstructBinaryTree(int[] in, int startIn, int endIn, int[] post, int startPost,
			int endPost) {
		if (startIn > endIn || startPost > endPost) {
			return null;
		}
		TreeNode root = new TreeNode(post[endPost]);
		for (int i = startIn; i <= endIn; i++) {
			if (post[endPost] == in[i]) {
				root.left = reConstructBinaryTree(in, startIn, i - 1, post, startPost, i - 1 - startIn + startPost);
				root.right = reConstructBinaryTree(in, i + 1, endIn, post, i - startIn + startPost, endPost - 1);
				break;
			}
		}

		return root;

	}

	public static void main(String[] args) {

		int[] in = { 4, 7, 2, 1, 5, 3, 8, 6 };
		int[] post = { 7, 4, 2, 5, 8, 6, 3, 1 };
		TreeNode root = reConstructBinaryTree(in, post);
		// 层次遍历进行验证
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode cur = queue.poll();
			System.out.println(cur.val);
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}

	}
}
