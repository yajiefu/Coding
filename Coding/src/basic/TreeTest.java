package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import basic.TreeNode;

/*
 public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;
	public TreeNode(int x) {
		val = x;
	}

}
 */
public class TreeTest {
	
	// 前序遍历-递归
	public static void preOrderTraverse1(TreeNode root) {
		if(root != null) {
			System.out.print(root.val + " ");
			preOrderTraverse1(root.left);
			preOrderTraverse1(root.right);
		}
	}
	// 前序遍历-非递归
		public static void preOrderTraverse2(TreeNode root) {
			Stack<TreeNode> stack = new Stack<>();
			TreeNode pNode = root;
			while(pNode != null || !stack.isEmpty()) {
				if (pNode != null) {
					System.out.print(pNode.val + " ");// 访问根节点
					stack.push(pNode); // 根节点入栈
					pNode = pNode.left; // 遍历左子树
				}
				else { 
					//pNode == null && !stack.isEmpty()
					TreeNode qNode = stack.pop(); // 出栈
					pNode = qNode.right; // 遍历右子树
				}
				
			}
		}
	
	// 中序遍历-递归
	public static void inOrderTraverse1(TreeNode root) {
		if (root != null) {
			inOrderTraverse1(root.left);
			System.out.print(root.val + " ");
			inOrderTraverse1(root.right);
		}
	}

	// 中序遍历-非递归
	public static void inOrderTraverse2(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		TreeNode pNode = root;
		while(pNode != null || !stack.isEmpty()) {
			if (pNode != null) {
//				System.out.print(pNode.val + " ");// 访问根节点
				stack.push(pNode); // 根节点入栈
				pNode = pNode.left; // 遍历左子树
			}
			else { 
				//pNode == null && !stack.isEmpty()
				TreeNode qNode = stack.pop(); // 出栈
				System.out.print(qNode.val + " ");
				pNode = qNode.right; // 遍历右子树
			}
			
		}
	}
	
	// 后序遍历-递归
	public static void postOrderTraverse1(TreeNode root) {
		if (root != null) {
			postOrderTraverse1(root.left);
			postOrderTraverse1(root.right);
			System.out.print(root.val + " ");
		}
	}
	// 后序遍历-非递归
	public static void postOrderTraverse2(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		// 当前节点
		TreeNode pNode = root;
		stack.push(pNode);
		// 存放栈顶弹出的元素
		TreeNode qNode = null;
		// 上一个访问的节点
		TreeNode preNode = null; 
		while(!stack.isEmpty()) {
			//栈顶元素置为当前元素，但是不移除
			pNode = stack.peek();
			if ((pNode.left == null && pNode.right == null) || 
					(preNode != null && (pNode.left == preNode || pNode.right == preNode))) {
				// 如果当前节点没有左右孩子  或者 有左孩子或右孩子，但是已经被访问输出了：
				// 则访问该节点，并出栈，将其置为上一个访问的节点。
				System.out.print(pNode.val + " ");
				qNode = stack.pop();
				preNode = pNode;
			}
			else {
				if (pNode.right != null) {
					stack.push(pNode.right);
				}
				if (pNode.left != null) {
					stack.push(pNode.left);
				}
			}
		}
	}
	
	// 层次遍历
	public static void levelOrderTraverse1(TreeNode root) {
		if (root == null) {
			return;
		}
		// 用LinkedList来实现一个双向队列，这里我们看成是一个单向的。
		
		
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.addLast(root);
		while(!queue.isEmpty()) {
			// 出队
			TreeNode pNode = queue.removeFirst();
			System.out.print(pNode.val + " ");
			if (pNode.left != null) {
				// 入队
				queue.addLast(pNode.left);
			}
			if (pNode.right != null) {
				// 入队
				queue.addLast(pNode.right);
			}
		}
		
	}
	// 层次遍历:分行打印
		public static void levelOrderTraverse2(TreeNode root) {
			if (root == null) {
				return;
			}
			// 用LinkenList来实现一个双向队列，这里我们看成是一个单向的。
			// 分行打印，设置两个变量分别保存，该行剩余节点数和下一行节点数
			int nextLevel = 0;
			int toBePrint = 1;
			
			LinkedList<TreeNode> queue = new LinkedList<>();
			queue.addLast(root);
			while(!queue.isEmpty()) {
				// 出队
				TreeNode pNode = queue.removeFirst();
				System.out.print(pNode.val + " ");
				toBePrint --;
				if (pNode.left != null) {
					// 入队
					queue.addLast(pNode.left);
					nextLevel ++;
				}
				if (pNode.right != null) {
					// 入队
					queue.addLast(pNode.right);
					nextLevel ++;
				}
				if (toBePrint == 0) {
					System.out.println();
					toBePrint = nextLevel;
					nextLevel = 0;
				}
			}
			
		}
		
		// 层次遍历:之字形打印
	public static ArrayList<ArrayList<Integer>> levelOrderTraverse3(TreeNode pRoot) {
		// 需要两个栈，A,B
		// 奇数层数字出栈后，先左后右保存在A
		// 偶数层数字出栈后，先右后左保存在B
		ArrayList<ArrayList<Integer>> result = new ArrayList<>(); 
		if (pRoot == null) {
			return result;
		}
		Stack<TreeNode> stackA = new Stack<>();
		Stack<TreeNode> stackB = new Stack<>();
		
		
		stackB.push(pRoot);
		while(!stackA.isEmpty() || !stackB.isEmpty()) {
			ArrayList<Integer> listB = new ArrayList<>();
			while(!stackB.isEmpty()) {
				TreeNode pNode = stackB.pop();
				
				listB.add(pNode.val);
//				System.out.print(pNode.val + " ");
				
				if (pNode.left != null) {
					stackA.push(pNode.left);
				}
				if (pNode.right != null) {
					stackA.push(pNode.right);
				}
			}
			if (!listB.isEmpty()) result.add(listB);
			
			ArrayList<Integer> listA = new ArrayList<>();
			while(!stackA.isEmpty()) {
				TreeNode pNode = stackA.pop();
				listA.add(pNode.val);
				if (pNode.right != null) {
					stackB.push(pNode.right);
				}
				if (pNode.left != null) {
					stackB.push(pNode.left);
				}
				
			}
			if (!listA.isEmpty()) {
				result.add(listA);
			}
		}
		
			return result;
			
		}
		

	
	public static void main(String[] args) {
		TreeNode aNode = new TreeNode(1);
		TreeNode bNode = new TreeNode(2);
		TreeNode cNode = new TreeNode(3);
		TreeNode dNode = new TreeNode(4);
		TreeNode eNode = new TreeNode(5);
		TreeNode fNode = new TreeNode(6);
		TreeNode gNode = new TreeNode(7);
		TreeNode hNode = new TreeNode(8);
		
		aNode.left = bNode;
		aNode.right = cNode;
		bNode.left = dNode;
		bNode.right = eNode;
		cNode.right = fNode;
		eNode.left = gNode;
		eNode.right = hNode;
		System.out.print("前序遍历-递归 ：   ");
		preOrderTraverse1(aNode);
		System.out.println();
		System.out.print("前序遍历-非递归：");
		preOrderTraverse2(aNode);
		System.out.println();
		System.out.print("中序遍历-递归 ：  ");
		inOrderTraverse1(aNode);
		System.out.println();
		System.out.print("中序遍历-非递归：");
		inOrderTraverse2(aNode);
		System.out.println();
		System.out.print("后序遍历-递归 ：  ");
		postOrderTraverse1(aNode);
		System.out.println();
		System.out.print("后序遍历-非递归：");
		postOrderTraverse2(aNode);
		System.out.println();
		System.out.print("层次遍历：");
		levelOrderTraverse1(aNode);
		System.out.println();
		System.out.println("层次遍历-分行打印：");
		levelOrderTraverse2(aNode);
		System.out.println("层次遍历-之字形打印：");
		ArrayList<ArrayList<Integer>> result = levelOrderTraverse3(aNode);

		for (ArrayList<Integer> arrayList : result) {
			for (Integer val : arrayList) {
				System.out.print(val + " ");
			}
			System.out.println();
		}
		
	}
}
