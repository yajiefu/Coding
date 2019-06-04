package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
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
	public static void preOrderRecur(TreeNode root) {
		if (root != null) {
			System.out.print(root.val + " ");
			preOrderRecur(root.left);
			preOrderRecur(root.right);
		}
	}

	// 前序遍历-非递归1
	/*
	 * 1.初始化一个空栈stack，申请一个节点空间pNode指向根节点（当前节点） 2.申请一个节点空间qNode，用来存放栈顶弹出的元素。
	 * 3.当p非空或者stack非空时，循环执行下面的操作 如果p非空，访问该节点，并将节点p入栈，节点p再指向该节点的左孩子
	 * 如果p为空，则弹出栈顶元素，将p指向该栈顶元素的右孩子。
	 */
	public static void preOrderUnRecur1(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		TreeNode pNode = root;
		while (pNode != null || !stack.isEmpty()) {
			if (pNode != null) {
				System.out.print(pNode.val + " ");// 访问根节点
				stack.push(pNode); // 根节点入栈
				pNode = pNode.left; // 遍历左子树
			} else {
				// pNode == null && !stack.isEmpty()
				TreeNode qNode = stack.pop(); // 出栈
				pNode = qNode.right; // 遍历右子树
			}

		}
	}

	// 前序遍历-非递归2：比非递归1更好理解
	/*
	 * 1.初始化一个空栈stack，然后将头节点head压入栈中
	 * 2.从stack中弹出栈顶元素，记为cur,然后打印cur节点的值，再将cur的右孩子（不为空的话）压入栈中，最后将cur的左孩子压入栈中。
	 * 3.不断重复步骤2，直到stack为空，全部过程结束
	 */
	public static void preOrderUnRecur2(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode cur = stack.pop();
			System.out.print(cur.val + " ");
			// 注意：这里是将 右节点先入栈
			if (cur.right != null) {
				stack.push(cur.right);
			}
			if (cur.left != null) {
				stack.push(cur.left);
			}
		}
	}

	// 中序遍历-递归

	public static void inOrderRecur(TreeNode root) {
		if (root != null) {
			inOrderRecur(root.left);
			System.out.print(root.val + " ");
			inOrderRecur(root.right);
		}
	}

	// 中序遍历-非递归
	/*
	 * 1.申请一个新的栈stack，初始时，令变量cur=root
	 * 2.现将cur入栈，对于以cur为根节点的整棵子树来说，依次把左边界压入栈中，即不停的令cur = cur.left,然后重复步骤2
	 * 3.不断重复步骤2直到发现cur为空，此时从stack中弹出一个节点，记为node，打印node的值，并且让cur =
	 * node.right,然后重复步骤2
	 */
	public static void inOrderUnRecur(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<>();
		TreeNode cur = root;
		while (cur != null || !stack.isEmpty()) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			} else {
				// 当cur为空时，即左边界走到底了
				TreeNode node = stack.pop();
				System.out.print(node.val + " ");
				cur = node.right;
			}
		}

	}

	// 后序遍历-递归
	public static void postOrderRecur(TreeNode root) {
		if (root != null) {
			postOrderRecur(root.left);
			postOrderRecur(root.right);
			System.out.print(root.val + " ");
		}
	}

	/*
	 * 方法1：用两个栈实现后序遍历 1.申请一个栈stack1，然后将头节点root入栈stack1
	 * 2.从stack1中弹出来的节点记为cur,然后依次将cur的左孩子和右孩子入栈stack1
	 * 3.在整个过程中，每一次从stack1中弹出来的节点都放进stack2中 4.不断重复步骤2和3，直到stack1中为空，过程停止
	 * 5.从stack2总依次弹出节点，打印的顺序就是后序遍历的顺序
	 */
	// 后序遍历-非递归1
	public static void postOrderUnRecur1(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> stack1 = new Stack<>();
		Stack<TreeNode> stack2 = new Stack<>();
		stack1.push(root);
		while (!stack1.isEmpty()) {
			TreeNode cur = stack1.pop();
			stack2.push(cur);
			if (cur.left != null) {
				stack1.push(cur.left);
			}
			if (cur.right != null) {
				stack1.push(cur.right);
			}
		}
		while (!stack2.isEmpty()) {
			System.out.print(stack2.pop().val + " ");
		}
	}

	/*
	 * 方法2：只用一个栈 1.初始化一个空栈stack，将头节点root入栈，同时设置两个申请变量cur和pre，
	 * 在这个流程中cur代表stack的栈顶节点，pre代表最近一次弹出并打印的节点。初始时，均为null。 2.当stack非空时，循环执行下面的操作
	 * 将栈顶节点置为当前节点cur,但是不从stack中弹出。 如果当前节点没有左右孩子  或者
	 * 有左孩子或右孩子，但是已经被访问输出了：则访问该节点，并出栈，将其置为上一个访问的节点。 如果不满足上面的条件的话，依次将右孩子和左孩子入栈。
	 */
	// 后序遍历-非递归2
	public static void postOrderUnRecur2(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		// 存放栈顶元素
		TreeNode cur = null;
		TreeNode pre = null;
		while (!stack.isEmpty()) {
			cur = stack.peek();
			// 如果当前节点没有左右孩子  或者 有左孩子或右孩子，但是已经被访问输出了：则访问该节点，并出栈，将其置为上一个访问的节点。
			if ((cur.left == null && cur.right == null) || (pre != null && (cur.left == pre || cur.right == pre))) {
				System.out.print(stack.pop().val + " ");
				pre = cur;
			} else {
				// 依次将cur的右孩子和左孩子入栈
				if (cur.right != null) {
					stack.push(cur.right);
				}
				if (cur.left != null) {
					stack.push(cur.left);
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
		while (!queue.isEmpty()) {
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
		while (!queue.isEmpty()) {
			// 出队
			TreeNode pNode = queue.removeFirst();
			System.out.print(pNode.val + " ");
			toBePrint--;
			if (pNode.left != null) {
				// 入队
				queue.addLast(pNode.left);
				nextLevel++;
			}
			if (pNode.right != null) {
				// 入队
				queue.addLast(pNode.right);
				nextLevel++;
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
		while (!stackA.isEmpty() || !stackB.isEmpty()) {
			ArrayList<Integer> listB = new ArrayList<>();
			while (!stackB.isEmpty()) {
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
			if (!listB.isEmpty())
				result.add(listB);

			ArrayList<Integer> listA = new ArrayList<>();
			while (!stackA.isEmpty()) {
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

	// 之字形打印方法2
	/*
	 * 思路：相较于[把二叉树打印成多行]，该题的奇数行从左往右，偶数行从右往左。上题用的是队列，本题我们用双端队列
	 *
	 * 1.首先生成双端队列deque，将根节点从头部入队
	 * 2.如果是从左往右的过程：一律从头部弹出节点，如果有孩子，从尾部依次进入左孩子和右孩子：如第1,3,5,层
	 * 3.如果是从右往左的过程：一律从尾部弹出节点，如果有孩子，从头部依次进入右孩子和左孩子：如第2,4,6层
	 * 
	 */
	public static ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if (pRoot == null) {
			return result;
		}

		Deque<TreeNode> deque = new LinkedList<>();
		deque.addFirst(pRoot);
		// flag 为true是奇数行
		boolean flag = true;
		// 本行剩余个数
		int count = 1;
		// 下一行个数
		int nextCount = 0;
		while (!deque.isEmpty()) {
			// 如果是奇数行，从左往右打印，一律从头部弹出节点，如果有孩子，从尾部依次进入左孩子和右孩子
			if (flag) {
				TreeNode node = deque.pollFirst();
				list.add(node.val);
				count--;
				if (node.left != null) {
					deque.addLast(node.left);
					nextCount++;
				}
				if (node.right != null) {
					deque.addLast(node.right);
					nextCount++;
				}
			} else {
				// 如果是偶数行，从右往左打印，一律从尾部弹出节点，如果有孩子，从头部依次进入右孩子和左孩子
				TreeNode node = deque.pollLast();
				list.add(node.val);
				count--;
				if (node.right != null) {
					deque.addFirst(node.right);
					nextCount++;
				}
				if (node.left != null) {
					deque.addFirst(node.left);
					nextCount++;
				}

			}

			if (count == 0) {
				result.add(list);
				list = new ArrayList<>();
				flag = !flag;
				count = nextCount;
				nextCount = 0;
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
		preOrderRecur(aNode);
		System.out.println();
		System.out.print("前序遍历-非递归1：");
		preOrderUnRecur1(aNode);
		System.out.println();
		System.out.print("前序遍历-非递归2：");
		preOrderUnRecur2(aNode);
		System.out.println();
		System.out.print("中序遍历-递归 ：  ");
		inOrderRecur(aNode);
		System.out.println();
		System.out.print("中序遍历-非递归：");
		inOrderUnRecur(aNode);
		System.out.println();
		System.out.print("后序遍历-递归 ：  ");
		postOrderRecur(aNode);
		System.out.println();
		System.out.print("后序遍历-非递归1：");
		postOrderUnRecur1(aNode);
		System.out.println();
		System.out.print("后序遍历-非递归2：");
		postOrderUnRecur2(aNode);
		System.out.println();
		System.out.print("层次遍历：");
		levelOrderTraverse1(aNode);
		System.out.println();
		System.out.println("层次遍历-分行打印：");
		levelOrderTraverse2(aNode);
		System.out.println("层次遍历-之字形打印1：");
		ArrayList<ArrayList<Integer>> result = levelOrderTraverse3(aNode);

		for (ArrayList<Integer> arrayList : result) {
			for (Integer val : arrayList) {
				System.out.print(val + " ");
			}
			System.out.println();
		}

		System.out.println("层次遍历-之字形打印2：");
		ArrayList<ArrayList<Integer>> result1 = print(aNode);

		for (ArrayList<Integer> arrayList : result1) {
			for (Integer val : arrayList) {
				System.out.print(val + " ");
			}
			System.out.println();
		}

	}
}
