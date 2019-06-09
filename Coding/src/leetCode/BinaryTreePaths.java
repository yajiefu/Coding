package leetCode;

import java.util.ArrayList;
import java.util.List;

import basic.TreeNode;

/*
 * 题目:257.二叉树的所有路径
 * 描述：给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 * 输入:

   1
 /   \
2     3
 \
  5

输出: ["1->2->5", "1->3"]

 */
public class BinaryTreePaths {
	/*
	 * 执行用时 : 3 ms, 在Binary Tree Paths的Java提交中击败了97.39% 的用户 内存消耗 : 35.5 MB, 在Binary
	 * Tree Paths的Java提交中击败了98.81% 的用户
	 */
	public static List<String> binaryTreePaths(TreeNode root) {
		List<String> paths = new ArrayList<String>();
		if (root == null) {
			return paths;
		}
		binaryTreePaths(root, paths, "");
		return paths;

	}

	public static void binaryTreePaths(TreeNode root, List<String> paths, String path) {

		// 如果root是叶子节点
		if (root.left == null && root.right == null) {
			path += root.val;
			paths.add(path);
		}
		// 如果不是叶节点，再继续遍历它的子节点
		if (root.left != null) {
			binaryTreePaths(root.left, paths, path + root.val + "->");
		}
		if (root.right != null) {
			binaryTreePaths(root.right, paths, path + root.val + "->");
		}

	}

	public static void main(String[] args) {
		TreeNode aNode = new TreeNode(10);
		TreeNode bNode = new TreeNode(5);
		TreeNode cNode = new TreeNode(12);
		TreeNode dNode = new TreeNode(4);
		TreeNode eNode = new TreeNode(7);

		aNode.left = cNode;
		aNode.right = bNode;
		bNode.left = dNode;
		bNode.right = eNode;
		List<String> result = new ArrayList<>();
		result = binaryTreePaths(aNode);
		for (String string : result) {
			System.out.println(string);
		}

	}

}
