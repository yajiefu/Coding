package leetCode;

import basic.TreeNode;

/**
 * 题目：671.二叉树中第二小的节点
 * 描述：给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为2或0。
 * 如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。 
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 * 示例 1:
 * 输入: 
 *     2
 *    / \
 *   2   5
 *  / \
 * 5   7
 * 输出: 5
 * 说明: 最小的值是 2 ，第二小的值是 5 
 * 
 * 示例 2:
 * 输入: 
 *     2 
 *    / \
 *   2   2
 * 输出: -1
 * 说明: 最小的值是 2, 但是不存在第二小的值。
 * 
 * 
 * 思路：可以转换成求左右子树的最小值。如果左右子树的的根节点大于最小值后，就直接返回该根节点，否则(等于的情况)再求该根节点的左右子树的最小值
 * 如果左右子树的最小值都大于根节点，则返回左右子树的较大值，否则（也就是一个大于一个小于的情况）就返回较小值。
 *
 * 1.没有必要记录最小的值，因为最小的一定是根结点。 
 * 2.递归找到比根结点大的值时可以立即返回，不用再遍历当前节点下面的子节点，因为子节点的值不可能比它小。
 * @author yajie
 *
 */
public class FindSecondMinimumValue {
	
	public static int findSecondMinimumValue(TreeNode root) {
		return helper(root, root.val);
	}
	public static int helper(TreeNode root, int min) {
		if (root == null) {
			return -1;
		}
		if (root.val > min) {
			return root.val;
		}
		
		int left = helper(root.left, min);
		int right = helper(root.right, min);
		if (left > min && right > min) {
			return Math.min(left, right);
		}
		return Math.max(left, right);
		
	}
	public static void main(String[] args) {
		TreeNode aNode = new TreeNode(1);
		TreeNode bNode = new TreeNode(2);
		TreeNode cNode = new TreeNode(3);
		TreeNode dNode = new TreeNode(4);
		TreeNode eNode = new TreeNode(5);
		aNode.left = bNode;
		aNode.right =cNode;
		bNode.left = dNode;
		bNode.right = eNode;
		System.out.println(findSecondMinimumValue(aNode));
	}
	
}
