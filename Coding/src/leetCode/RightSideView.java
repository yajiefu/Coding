package leetCode;
/**
 * 题目：199.二叉树的右视图
 * 描述：给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 
 * 示例:
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *   
 * 思路：层序遍历，把每层最后一个放到结果里就可以了
 *   时间复杂度：O(n)
 *   空间复杂度：O(n)
 * @author yajie
 *
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import basic.TreeNode;
public class RightSideView {
	
	public static List<Integer> rightSideView(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}
		//层次遍历
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		int count = 1;//这一层的个数
		int next = 0;//下一层的个数
		while(!queue.isEmpty()) {
			//出队
			TreeNode node = queue.remove();
			count--;
			if (node.left != null) {
				queue.add(node.left);
				next++;
			}
			if (node.right != null) {
				queue.add(node.right);
				next++;
			}
			if (count == 0) {
				list.add(node.val);
				count = next;
				next = 0;
			}
			
		}
		return list;
	}
	
	public static void main(String[] args) {
		TreeNode aNode = new TreeNode(1);
		TreeNode bNode = new TreeNode(2);
		TreeNode cNode = new TreeNode(3);
		TreeNode dNode = new TreeNode(4);
		TreeNode eNode = new TreeNode(5);
		
		aNode.left = bNode;
		aNode.right = cNode;
		bNode.right = eNode;
		cNode.right = dNode;
		List<Integer> list = rightSideView(aNode);
		for (Integer integer : list) {
			System.out.println(integer);
		}
		
		
	}

}
