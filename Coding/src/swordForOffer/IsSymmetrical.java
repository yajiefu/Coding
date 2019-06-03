package swordForOffer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import basic.TreeNode;

/*
 * 题目：对称的二叉树
 * 描述：请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
public class IsSymmetrical {

	//方法1：递归
	public static boolean isSymmetrical(TreeNode pRoot) {
		if (pRoot == null) {
			return true;
		}
		return isSymmetrical(pRoot.left, pRoot.right);

	}

	public static boolean isSymmetrical(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root1 == null || root2 == null) {
			return false;
		}
		if (root1.val != root2.val) {
			return false;
		}
		return isSymmetrical(root1.left, root2.right) && isSymmetrical(root1.right, root2.left);
	}


	// 方法2：迭代

	/*
	 * DFS使用stack来保存成对的节点
	 *  1.出栈的时候也是成对成对的 ，
	 *     1.若都为空，继续；
	 *     2.一个为空，返回false;
	 *     3.不为空，比较当前值，值不等，返回false；
	 *  2.确定入栈顺序，每次入栈都是成对成对的，如left.left， right.right ;left.rigth,right.left
     */
	public static boolean isSymmetricalDFS(TreeNode root) {
		if (root == null) {
			return true;
		}
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root.left);
		stack.push(root.right);
		while(!stack.isEmpty()) {
			TreeNode right = stack.pop();
			TreeNode left = stack.pop();
			if (right == null && left == null) {
				continue;
			}
			if (right == null || left == null || right.val != left.val) {
				return false;
			}
			stack.push(left.left);
			stack.push(right.right);
			stack.push(left.right);
			stack.push(right.left);
			
		}
		return true;
	}
	
	/*
	 * BFS使用Queue来保存成对的节点，代码和上面极其相似 
	 * 1.出队的时候也是成对成对的
	 *     1.若都为空，继续； 
	 *     2.一个为空，返回false;
	 *     3.不为空，比较当前值，值不等，返回false； 
	 * 2.确定入队顺序，每次入队都是成对成对的，如left.left， right.right ;left.rigth,right.left
	 */
	public static boolean isSymmetricalBFS(TreeNode root) {
		if (root == null) {
			return true;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root.left);
		queue.add(root.right);
		while(!queue.isEmpty()) {
			TreeNode left = queue.poll();
			TreeNode right = queue.poll();
			if (left == null && right == null) {
				continue;
			}
			if (left == null || right == null || left.val != right.val) {
				return false;
			}
			
			queue.add(left.left);
			queue.add(right.right);
			queue.add(left.right);
			queue.add(right.left);
		}
		return true;
	}
	
	public static void main(String[] args) {
		TreeNode aNode = new TreeNode(2);
		TreeNode bNode = new TreeNode(3);
		TreeNode cNode = new TreeNode(3);
		TreeNode dNode = new TreeNode(4);
		TreeNode eNode = new TreeNode(5);
		TreeNode fNode = new TreeNode(4);
		
		aNode.left = bNode;
		aNode.right = cNode;
		bNode.left = dNode;
		bNode.right = eNode;
		cNode.right = fNode;

	

		System.out.println(isSymmetrical(aNode));
		System.out.println(isSymmetricalDFS(aNode));
		System.out.println(isSymmetricalBFS(aNode));
	}
}
