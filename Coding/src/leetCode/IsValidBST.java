package leetCode;

import java.util.Stack;

import basic.TreeNode;

/**
 * 题目：98.验证二叉搜索树
 * 描述：给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * 输入： 2
 *    / \
 *   1   3
 *   输出: true
 * 示例 2:
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 * 根节点的值为 5 ，但是其右子节点值为 4 。

 * @author yajie
 * 
 * 思路：只需要遍历整棵树，检查 node.right.val > node.val 和node.left.val < node.val 对每个结点是否成立
 * 但是其实并不是的。不仅右子结点要大于该节点，整个右子树的元素都应该大于该节点。
 * 这意味着我们需要在遍历树的同时保留结点的上界与下界，在比较时不仅比较子结点的值，也要与上下界比较
 *
 *方法1：递归
 *时间复杂度：O(n)
 *空间复杂度：O(n)
 *
 *方法2：中序遍历。看是不是从小到大的。
 *时间复杂度：O(n)
 *空间复杂度：O(n)
 *
 *方法3：
 *2.1 这个解法是基于二叉树的中序遍历
 *2.2 定义一个变量用于存储上一遍历结点的值
 *2.3 递归遍历二叉树
 *2.4 如果左子树返回true，对比根节点与缓存变量的值
 *2.5 如果2.4的对比成立，则将根节点的值赋给缓存变量
 *2.6 递归遍历右子树
 *时间复杂度：O(n)
 *空间复杂度：O(1)
 *
 */
public class IsValidBST {
	//方法1：递归
	public boolean isValidBST1(TreeNode root) {
		return helper(root, null, null);
	}

	public boolean helper(TreeNode node, Integer lower, Integer upper) {
		if (node == null) {
			return true;
		}
		//判断当前节点
		int val = node.val;
		if (lower != null && val <= lower) {
			return false;
		}
		if (upper != null && val >= upper) {
			return false;
		}
		//判断左子树
		if (!helper(node.left, lower, val)) {
			return false;
		}
		//判断右子树
		if (!helper(node.right, val, upper)) {
			return false;
		}
		return true;
	}
	
	//方法2：中序遍历，看当前元素是不是大于前一个元素，可以用迭代
	public boolean isValidBST2(TreeNode root) {

		if (root == null) {
			return true;
		}
		Stack<TreeNode> stack = new Stack<>();
		TreeNode curNode = root;
		double preVal = -Double.MAX_VALUE;
		while (curNode != null || !stack.isEmpty()) {
			while (curNode != null) {
				stack.push(curNode);
				curNode = curNode.left;
			}
			// 走到最左边
			curNode = stack.pop();
			if (curNode.val <= preVal) {
				return false;
			}
			preVal = curNode.val;
			curNode = curNode.right;
		}
		return true;
	}

	//方法3：
	double last = Double.MAX_VALUE;
	public boolean isValidBST(TreeNode root) {

		if (root == null) {
			return true;
		}
		if (isValidBST(root.left)) {//如果左子树返回true
			//对比根节点和缓存遍历的值
			if (root.val > last) {
				last = root.val;
				return isValidBST(root.right);
			}
		}
		return false;
	}

}
