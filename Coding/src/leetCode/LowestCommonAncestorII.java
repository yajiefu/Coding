package leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import basic.TreeNode;

/**
 * 题目：236.二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x是 p、q 的祖先且 x的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 示例 1:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 
 * 示例 2:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 * 
 * 
 * 
 * 与235题不同的是，这个并不是二叉搜索树
 * 
 * 
 * 思路：我们可以用普通的树遍历来搜索这两个节点。一旦我们到达所需的节点p和q，我们就可以回溯并找到最近的公共祖先
 * 方法1：递归。
 * 1.从根节点开始遍历树
 * 2.如果当前节点本身是p或者q中的一个，我们将变量mid标记为1，并继续搜索左右分支中的另一个节点
 * 3.如果左分支或右分支中的任何一个返回1，则表示在下面找到了两个节点中的一个。
 * 4.如果在遍历的任何点上，左右中三个标志中的任意两个变为1，则意味着我们找到了p和q的最近公共祖先。
 * 时间复杂度：O(n)最坏情况下，我们需要访问二叉树的所有节点。
 * 空间复杂度：O(n)这是因为递归堆栈使用的最大空间位 n,斜二叉树的高度可以是 n
 * 
 * 方法2：既然可以采用递归，那么是否可以用迭代的方法呢？
 *   使用父指针迭代。
 *   用一个HashMap<key, value>,value是key的父节点
 *   用一个set保存p的祖先们，然后遍历q的祖先找到第一个共同的祖先
 *   1.从根节点开始遍历树：用栈
 *   2.在找到p和q之前，将每个节点的节点和它的父节点放入map中
 *   3.一旦我们找到了p和q，我们就可以在map中找到p的所有的祖先，并且把他们放到一个集合set中
 *   4.同样，我们遍历q的祖先。如果祖先存在于p设置的祖先中，这意味着这是p和q之间的第一个共同的祖先(同时向上遍历)
 * 时间复杂度：O(n)最坏情况下，我们需要访问二叉树的所有节点。
 * 空间复杂度：O(n)在堆栈使用的最坏情况下，每个节点的父指针字典和祖先集的空间为 n，斜二叉树的高度可能为n
 * 
 * 
 * 另外，如果是要找p和q的值最小的公共祖先。
 * 思路：遍历q的祖先，找到所有祖先里面和q相同的最小值
 * 
 * @author yajie
 *
 */
public class LowestCommonAncestorII {
	/*
	 *方法1：递归
	TreeNode ans = null;
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		dfs(root, p, q);
		return ans;
	}
	public int dfs(TreeNode cur, TreeNode p, TreeNode q) {
		if (cur == null) {
			return 0;
		}
		int left = dfs(cur.left, p, q);
		int right = dfs(cur.right, p, q);
		int mid = (cur == p || cur == q) ? 1 : 0;
		if ((left + mid + right) > 1) {
			ans = cur;
		}
		return (left + mid + right) > 0 ? 1 : 0;
	}
	*/

	// 方法2：使用父指针迭代。
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		HashMap<TreeNode, TreeNode> parent = new HashMap<>();
		parent.put(root, null);
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);

		// 在找到p和q之前，将每个节点的节点和它的父节点放入map中
		while (!parent.containsKey(p) || !parent.containsKey(q)) {
			TreeNode cur = stack.pop();
			if (cur.left != null) {
				parent.put(cur.left, cur);
				stack.push(cur.left);
			}

			if (cur.right != null) {
				parent.put(cur.right, cur);
				stack.push(cur.right);
			}
		}
		// 此时，p和q都在map中了。
		// 将p的祖先都放在set中
		Set<TreeNode> ancestors = new HashSet<>();
		while (p != null) {
			ancestors.add(p);// 也要将p放进去
			p = parent.get(p);
		}

		// 遍历q的祖先，直到从set里面找第一个公共祖先
		while (!ancestors.contains(q)) {
			q = parent.get(q);
		}
		return q;
	}
}
