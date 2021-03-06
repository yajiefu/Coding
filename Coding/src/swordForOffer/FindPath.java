package swordForOffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import basic.TreeNode;

/*
 * 题目：二叉树中和为某一值的路径
 * 描述：输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class FindPath {

	public static ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<ArrayList<Integer>> listAll = new ArrayList<>();
		if (root == null) {
			return listAll;
		}
		findPathCore(root, target, list, listAll);
		// 如果要按照降序排序 则o1 小于o2，返回1（正数），相等返回0，01大于02返回-1（负数）
		Collections.sort(listAll, new Comparator<ArrayList<Integer>>() {
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				if (o1.size() < o2.size()) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		return listAll;
	}

	public static void findPathCore(TreeNode root, int target, ArrayList<Integer> list,
			ArrayList<ArrayList<Integer>> listAll) {
		
		list.add(root.val);
		target = target - root.val;

		// root是叶节点且此时target为0,说明该路径是正确的
		if (target == 0 && root.left == null && root.right == null) {
			listAll.add(new ArrayList<>(list));
		} 
		// 如果不是叶节点，再继续遍历它的子节点
		if (root.left != null) {
			findPathCore(root.left, target, list, listAll);
		}
		if (root.right != null) {
			findPathCore(root.right, target, list, listAll);
		}
		

		// 无论当前路径是否加出了target，必须去掉最后一个，然后返回父节点，去查找另一条路径
		list.remove(list.size() - 1);
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
		
		int target = 22;
	
		ArrayList<ArrayList<Integer>> result = findPath(aNode, target);
		for (ArrayList<Integer> arrayList : result) {
			for(Integer val: arrayList) {
				System.out.print(val + " ");
			}
			System.out.println();
		}
	}		
}
