package leetCode;

/*
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

给一个二叉树，求它的最小深度

剑指offer有一题是：求二叉树的深度
 */
import java.util.LinkedList;
import basic.TreeNode;

public class MinDepthOfBinaryTree {
	//  方法1：层次遍历，找到一个左右子树都为空的停止。
	public static int run(TreeNode root) {
	
		if (root == null) {
			return 0;
		}
		// level表示是当前是第几层
		int level = 1;
		// 该层剩余节点数
		int toBePrint = 1;
		// 下一层节点数
		int nextLevel = 0;
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			TreeNode qNode = queue.removeFirst();
			toBePrint--;
			if (qNode.left == null && qNode.right == null) {
				return level;
			}
			if (qNode.left != null) {
				queue.add(qNode.left);
				nextLevel++;
			}
			if (qNode.right != null) {
				queue.add(qNode.right);
				nextLevel++;
			}
			
			
			if (toBePrint == 0) {
				toBePrint = nextLevel;
				nextLevel = 0;
				level++;
			}
		}
		return level;
	}
	
	
	// 方法2：递归，求左右子树的深度
	public static int run1(TreeNode root){
	
		if (root == null) {
			return 0;
		}
		int left = run(root.left);
		int right = run(root.right);
		if (left == 0 || right == 0) {
			return left + right + 1;
		}
		return  Math.min(left, right) + 1;
		
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
			System.out.println(run(aNode));
	}
	 
}
