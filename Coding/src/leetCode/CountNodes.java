package leetCode;
import basic.TreeNode;

/**
 * 题目：222.完全二叉树的节点个数
 * 描述：给出一个完全二叉树，求出该树的节点个数。
 * 说明：完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
 *     若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 *     示例:
 *     输入: 
 *        1
 *       / \
 *      2   3
 *     / \  /
 *    4  5 6
 *    
 *    输出: 6
 * 
 * 思路：首先需要明确完全二叉树的定义：它是一棵空树或者它的叶子节点只出在最后两层，若最后一层不满则叶子节点只在最左侧。
 * 再来回顾一下满二叉的节点个数怎么计算，如果满二叉树的层数为h，则总节点数为：2^h - 1.
 * 那么我们来对root节点的左右子树进行高度统计，分别记为left和right,有以下两种结果：
 * left == right。这说明，左子树一定是满二叉树，因为节点已经填充到右子树了，左子树必定已经填满了。所以左子树的节点总数我们可以直接得到，是2^left - 1，加上当前这个root节点，则正好是2^left。再对右子树进行递归统计。
 * left != right。说明此时最后一层不满，但倒数第二层已经满了，可以直接得到右子树的节点个数。同理，右子树节点+root节点，总数为2^right。再对左子树进行递归查找。
 * @author yajie
 *
 */
public class CountNodes {
	public static int countNodes(TreeNode root) {
		if (root == null) {
			return 0;
		}
		//计算左右子树的深度
		int left = depth(root.left);
		int right = depth(root.right);
		//情况1：如果left == right，说明左子树是满二叉树，继续判断右子树
		if (left == right) {
			return (1 << left) + countNodes(root.right);
		}else {//left > right，说明右子树是满二叉树，继续判断左子树
			return (1 << right) + countNodes(root.left);
		}

	}
	//求完全二叉树的深度
	public static int depth(TreeNode root) {
		int depth = 0;
		while(root != null) {
			depth++;
			root = root.left;
		}
		return depth;
	}

	public static void main(String[] args) {
		TreeNode aNode = new TreeNode(1);
		TreeNode bNode = new TreeNode(2);
		TreeNode cNode = new TreeNode(3);
		TreeNode dNode = new TreeNode(4);
		TreeNode eNode = new TreeNode(5);
		TreeNode fNode = new TreeNode(6);
		aNode.left = bNode;
		aNode.right =cNode;
		bNode.left = dNode;
		bNode.right = eNode;
		cNode.right = fNode;
		System.out.println(countNodes(aNode));
		

	}

}
