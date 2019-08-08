package leetCode;

import java.util.ArrayList;
import java.util.List;

import basic.TreeNode;

/*
 * 题目：113.路径总和 II
 * 描述：给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 * 
 * 和剑指offer 二叉树中和为某一值的路径 一样
 */
public class PathSumII {
	public List<List<Integer>> pathSum(TreeNode root, int sum) {

		List<Integer> path = new ArrayList<>();
		List<List<Integer>> paths = new ArrayList<>();
		if (root == null) {
			return paths;
		}
		pathSumCore(root, sum, path, paths);
		return paths;
	}
	
	public void pathSumCore(TreeNode root, int sum, List<Integer> path, List<List<Integer>> paths) {

		path.add(root.val);
		sum -= root.val;
		// 如果是叶子节点
		if (root.left == null && root.right == null && sum == 0) {
			paths.add(new ArrayList<>(path));
		}
		if (root.left != null) {
			pathSumCore(root.left, sum, path, paths);
		}
		if (root.right != null) {
			pathSumCore(root.right, sum, path, paths);
		}
		
		// 无论当前路径是否加出了sum，必须去掉最后一个，然后返回父节点，去查找另一条路径
		path.remove(path.size() - 1);
	}
}
