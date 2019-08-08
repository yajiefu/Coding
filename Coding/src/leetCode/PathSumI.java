package leetCode;

/*
 * 题目：112.路径总和
 * 描述：给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 */
import basic.TreeNode;

public class PathSumI {
	public static boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        sum -= root.val;
        if(root.left == null && root.right == null){
            return sum == 0;
        }
        return hasPathSum(root.left, sum) || hasPathSum(root.right,sum);
    
    }
	public static void main(String[] args) {
		TreeNode aNode = new TreeNode(10);
		TreeNode bNode = new TreeNode(5);
		TreeNode cNode = new TreeNode(12);
		TreeNode dNode = new TreeNode(4);
		TreeNode eNode = new TreeNode(7);
		
		aNode.left = cNode;
		aNode.right = bNode;
		bNode.left = dNode;
		bNode.right = eNode;
		
		System.out.println(hasPathSum(aNode, 22));
	
	}		
}
