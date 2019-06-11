package swordForOffer;

import basic.TreeNode;

/*
 * 题目：序列化二叉树
 * 描述：请实现两个函数，分别用来序列化和反序列化二叉树
 * 
 * 序列化:eg. 1,2,3,4,#,#,#,5,#,#,6,#,#
 */
public class SerializeAndDeserialize {

	StringBuilder sb = new StringBuilder();

	int index = -1;

	// 序列化二叉树：前序遍历
	public String Serialize(TreeNode root) {
		if (root == null) {
			sb.append("#,");
			return sb.toString();
		}
		sb.append(root.val + ",");
		Serialize(root.left);
		Serialize(root.right);
		return sb.toString();

	}

	// 反序列化二叉树
	public TreeNode Deserialize(String str) {
		index++;
		String[] string = str.split(",");
		TreeNode node = null;
		if (!string[index].equals("#")) {
			node = new TreeNode(Integer.parseInt(string[index]));
			node.left = Deserialize(str);
			node.right = Deserialize(str);
		}
		return node;
	}

}
