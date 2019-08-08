package leetCode;


import java.util.HashMap;

import basic.TreeNode;
/**
 * 题目：437.路径总和III
 * 描述：二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 * 示例：root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *         10
 *        /  \
 *       5   -3
 *      / \    \
 *     3   2   11
 *    / \   \
 *   3  -2   1
 *   返回 3。和等于 8 的路径有:
 *   1.  5 -> 3
 *   2.  5 -> 2 -> 1
 *   3.  -3 -> 11
 *   
 *   思路1：以每个节点为根节点，都算一遍路径和为sum的有几条，然后加起来
 * @author yajie
 *
 */
public class PathSumIII {
	
	//方法1：以每个节点为根节点，都算一遍路径和为sum的有几条，然后加起来
	public static int pathSum1(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}
		
		return paths(root, sum) + pathSum1(root.left, sum )
		      + pathSum1(root.right, sum);
		
	}
	public static int paths(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}
		int cout = 0;
		if (root.val ==  sum) {
			cout += 1;
		}
		
		cout += paths(root.left, sum - root.val) ;
		cout += paths(root.right, sum - root.val);
		return cout;
	}
	
	//方法1有重复计算
	//方法2：类似前缀和的方式。
	public static int pathSum(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}
		
		//从根节点到当前节点的路径上，以根节点为起点，长为 key 的子序列的数量
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		return helper(root, sum, map, 0);
		
	}
	//对于树的话，采取DFS加回溯，每次访问到一个节点，把该节点加入到当前的pathSum中
	//然后判断是否存在一个之前的前n项和，其值等于pathSum与sum之差
	//如果有，就说明现在的前n项和，减去之前的前n项和，等于sum，那么也就是说，这两个点之间的路径和，就是sum
	public static int helper(TreeNode root, int sum, HashMap<Integer, Integer> map, int pathSum) {
		int res = 0;
		if (root == null) {
			return 0;
		}
		pathSum += root.val;
		//找到之前一个前项和s,使得 pathsum - s = sum，即表示有一个满足条件的路径。
		res += map.getOrDefault(pathSum - sum, 0);
		//将目前的前项和加入map
		map.put(pathSum, map.getOrDefault(pathSum, 0) + 1);
		//继续往这个root的左右子树前进。
		res = helper(root.left, sum, map, pathSum)
				+ helper(root.right, sum, map, pathSum)
				+ res;
		//回溯回去。
		map.put(pathSum, map.get(pathSum) - 1);
		return res;
				
		
	}
}
