package basic;

public class TreeLinkNode {

	public int val;
	public TreeLinkNode left = null;
	public TreeLinkNode right = null;
	//ָ 指向父节点：牛客网上写的是next ，改成parent比较直观
	public TreeLinkNode parent = null;
	public TreeLinkNode(int val) {

		this.val = val;
	}
}