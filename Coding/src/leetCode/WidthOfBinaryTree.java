package leetCode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import basic.TreeNode;


/**
 * 题目：662.二叉树最大宽度
 * 描述：给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。
 *           这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 *           每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 * 
 * 示例 1:
 * 输入: 
 * 
 *           1
 *         /   \
 *        3     2
 *       / \     \  
 *      5   3     9 
 *      
 *      输出: 4
 *      解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
 *      示例 2:
 *      输入: 
 *         1
 *        /  
 *       3    
 *      / \       
 *     5   3     
 *     
 *     输出: 2
 *     解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
 *     示例 3:
 *     输入: 
 *      1
 *     / \
 *    3   2 
 *   /        
 *  5      
 *  
 *  输出: 2
 *  解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
 *  示例 4:
 *  输入: 
 *       1
 *      / \
 *     3   2
 *    /     \  
 *   5       9 
 *  /         \
 * 6           7
 *输出: 8
 *解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
 *
 *思路：用两个队列分别保存节点和其下标。
 * @author yajie
 *
 */
public class WidthOfBinaryTree {
	public int widthOfBinaryTree(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode> nodesQueue = new LinkedList<TreeNode>();
		Deque<Integer> indexQueue = new LinkedList<>();
		nodesQueue.add(root);
		indexQueue.add(0);
		int maxLen = 1;
		
		while(!nodesQueue.isEmpty()) {
			//存放下一排的
			Queue<TreeNode> nextNodesQueue = new LinkedList<TreeNode>();
			Deque<Integer> nextIndexQueue = new LinkedList<>();
			while(!nodesQueue.isEmpty()) {
				TreeNode curNode = nodesQueue.poll();
				int i = indexQueue.poll();
				if (curNode.left != null) {
					nextNodesQueue.add(curNode.left);
					nextIndexQueue.add(2*i+1);
				}
				if (curNode.right != null) {
					nextNodesQueue.add(curNode.right);
					nextIndexQueue.add(2 * i +2);
				}
				
			}
			//计算下层的宽度
			int nextLen = 0;
			if (nextIndexQueue.size() > 0) {
				nextLen = nextIndexQueue.getLast() - nextIndexQueue.getFirst() + 1;
			}
			maxLen = Math.max(maxLen, nextLen);
			//更新
			nodesQueue = nextNodesQueue;
			indexQueue = nextIndexQueue;
		}
		return maxLen;
	}
	
	

}
