package leetCode;
/**
 * 题目：654.最大二叉树
 * 描述：给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 *     二叉树的根是数组中的最大元素。
 *     左子树是通过数组中最大值左边部分构造出的最大二叉树。
 *     右子树是通过数组中最大值右边部分构造出的最大二叉树。
 *     通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 *     
 * 很简单，递归即可
 */
import basic.TreeNode;

public class ConstructMaximumBinaryTree {
	public TreeNode constructMaximumBinaryTree(int[] nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		return constructMaximumBinaryTree(nums, 0, nums.length - 1);
	}

	public TreeNode constructMaximumBinaryTree(int[] nums, int low, int high) {
		if (low > high) {
			return null;
		}
		
		int maxIndex = low;
		for (int i = low; i <= high; i++) {
			if (nums[i] > nums[maxIndex]) {
				maxIndex = i;
			}
		}
		TreeNode node = new TreeNode(nums[maxIndex]);
		node.left = constructMaximumBinaryTree(nums, low, maxIndex - 1);
		node.right = constructMaximumBinaryTree(nums, maxIndex + 1, high);

		return node;

	}
}
