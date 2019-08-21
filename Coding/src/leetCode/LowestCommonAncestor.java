package leetCode;


import basic.TreeNode;
/**
 * 
 * 题目：235.二叉搜索树的最近公共祖先
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 示例 1:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6 
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * 示例 2:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 * 
 * 
 * 思路：
 * 1.从根节点开始遍历树
 * 2.如果节点 p和节点 q都在右子树上，那么以右孩子为根节点继续 1 的操作
 * 3.如果节点 p和节点 q都在左子树上，那么以左孩子为根节点继续 1的操作
 * 4.如果条件 2 和条件 3都不成立，这就意味着我们已经找到节 p和节点 q的 LCA 了
 * 
 * 方法1：递归
 *    时间复杂度：O(n)
 *    空间复杂度：O(n)
 * 方法2：迭代
 *    时间复杂度：O(n)
 *    空间复杂度：O(1)
 * @author yajie
 *
 */
public class LowestCommonAncestor {
	//方法1：递归
	public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
		int parentVal = root.val;
		int pVal = p.val;
		int qVal = q.val;
		//两个都在左子树上
		if (pVal < parentVal && qVal < parentVal) {
			return lowestCommonAncestor1(root.left, p, q);
			
		}else if (pVal > parentVal && qVal > parentVal) {
		
			//都在右子树上
			return lowestCommonAncestor1(root.right, p, q);
		}else {
			//一左一右
			return root;
		}
	}
	
	//方法2：迭代
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode cur  = root;
		int pVal = p.val;
		int qVal = q.val;
		while(cur != null) {
			int parentVal = cur.val;
			if (pVal < parentVal && qVal < parentVal) {
				//左子树
				cur  = cur.left;
			}else if (pVal > parentVal && qVal > parentVal) {
				//右子树
				cur  = cur.right;
			}else {
				//一左一右
				return cur;
			}
		}
		return null;
	}
}
