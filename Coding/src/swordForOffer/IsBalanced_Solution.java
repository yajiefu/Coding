package swordForOffer;

import basic.TreeNode;
/*
 * 题目：平衡二叉树
 * 描述：输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 * 思路：方法1: 根据树的深度相差来做。但是不足是：我们首先遍历的是根节点的左右子树，重复的太多了。
 *     方法2： 每个结点只遍历一次。
 */
public class IsBalanced_Solution {
	// 方法1：根据树的深度相差来做。但是不足是：我们首先遍历的是根节点的左右子树，重复的太多了。
    public static boolean isBalanced(TreeNode root) {
    	if (root == null) {
			return true;
		}
    	int left = treeDepth(root.left);
    	int right = treeDepth(root.right);
    	if (Math.abs(left - right) > 1) {
			return false;
		}
    	// 在继续判断左右子树
    	return isBalanced(root.left) && isBalanced(root.right);
    }
    
    // 递归求二叉树的深度
    public static int treeDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = treeDepth(root.left);
		int right = treeDepth(root.right);
		return Math.max(left, right) + 1;
        
    }
    /* 方法1中，在判断上层结点的时候，会多次重复遍历下层结点，增加了不必要的开销。
     * 如方法2，如果改为从下往上遍历，如果子树是平衡二叉树，则返回子树的高度；如果发现子树不是平衡二叉树，则直接停止遍历，这样至多只对每个结点访问一次。
     */
    
    //方法2：整体思路是二叉树的后序遍历。对任何一个节点node来说，先遍历它的左子树，遍历过程中收集两个信息：1.它的左子树是否为平衡二叉树2.它的左子树最深的深度left是多少。
    // 如果node的左子树不是平衡二叉树，剪枝，无须再进行。如果是平衡二叉树，再遍历node的右子树，同理。
    // 如果左右子树都是平衡二叉树，就看左右子树的深度的绝对值是否大于1，如果大于1，说明整棵树不是平衡二叉树，如果不大于1,返回left和right较大的一个。
    // 如果返回值是-1，就表示不平衡。
    public static boolean isBalanced2(TreeNode root) {
    	return getDepth(root) != -1;
    }
    
    public static int getDepth(TreeNode root) {
    	if (root == null) {
			return 0;
		}
    	int left = getDepth(root.left);
    	// 剪枝
    	if (left == -1) {
			return -1;
		}
    	int right = getDepth(root.right);
    	// 剪枝
    	if (right == -1) {
			return -1;
		}
    	return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right); 
    }
    public static void main(String[] args) {
    	TreeNode aNode = new TreeNode(1);
		TreeNode bNode = new TreeNode(2);
		TreeNode cNode = new TreeNode(3);
		TreeNode dNode = new TreeNode(4);
		TreeNode eNode = new TreeNode(5);
		TreeNode fNode = new TreeNode(6);
		TreeNode gNode = new TreeNode(7);
		TreeNode hNode = new TreeNode(8);
		
		aNode.left = bNode;
		aNode.right = cNode;
		bNode.left = dNode;
		bNode.right = eNode;
		cNode.right = fNode;
		eNode.left = gNode;
		eNode.right = hNode;
		System.out.println(isBalanced2(aNode));
	}
}
