package swordForOffer;

import basic.TreeLinkNode;
/*
 * 题目：二叉树的下一个节点
 * 描述：给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class GetNext {
/*
	
	public class TreeLinkNode {

		 int val;
		 TreeLinkNode left = null;
		 TreeLinkNode right = null;
		 //ָ 指向父节点：牛客网上写的是next ，改成parent比较直观
		 TreeLinkNode parent = null;
		 TreeLinkNode(int val) {

			this.val = val;
		}
	}
*/
	
	/*
	 * 分析：分几种情况
	 * 1.有右子树的，它的下一个是右子树的最左子节点。（一直沿着指向左子结点的指针找到的叶子节点即为下一个节点）
	 * 2.没有右子树的，且如果它是它父节点的左子节点的话，它的下一个是它的父节点
	 * 3.没有右子树的，且它是它父节店的右子节点的话，它的情况比较复杂，要沿着它父节点上去，直到找到它是它父节点的左子节点为止，那么下一个就是这个父节点。
	 */
	public static TreeLinkNode getNext(TreeLinkNode pNode)
    {
		if(pNode == null) {
			return null;
		}
		
		// 第1种情况
		if (pNode.right != null) {
			pNode = pNode.right;
			while(pNode.left != null) {
				pNode = pNode.left;
			}
			return pNode;
		}
		
		// 第2/3种情况
		
		while(pNode.parent != null) {
			// 父节点
			TreeLinkNode pRoot = pNode.parent;
			if (pNode == pRoot.left) {
				// 第2种情况
				return pRoot;
			}
			// 如果第2种情况还没达到，就继续向上遍历
			pNode = pNode.parent;
		}
		// 如果向上遍历到了根节点后，上一个就是尾节点了。
		return null;
        
    }
	
	public static void main(String[] args) {
		TreeLinkNode a = new TreeLinkNode(1);
		TreeLinkNode b = new TreeLinkNode(2);
		TreeLinkNode c = new TreeLinkNode(3);
		TreeLinkNode d = new TreeLinkNode(4);
		TreeLinkNode e = new TreeLinkNode(5);
		TreeLinkNode f = new TreeLinkNode(6);
		TreeLinkNode g = new TreeLinkNode(7);
		TreeLinkNode h = new TreeLinkNode(8);
		TreeLinkNode i = new TreeLinkNode(9);
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		e.left = h;
		e.right = i;
		c.left = f;
		c.right = g;
		b.parent = a;
		c.parent = a;
		d.parent = b;
		e.parent = b;
		h.parent  = e;
		i.parent = e;
		f.parent = c;
		g.parent = c;
		TreeLinkNode aLinkNode = getNext(d);
		System.out.println(aLinkNode.val);
		
	}
	
}
