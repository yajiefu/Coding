package leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
/**
 * 题目：133.克隆图
 * 描述：给定无向连通图中一个节点的引用，返回该图的深拷贝（克隆）。图中的每个节点都包含它的值 val（Int） 和其邻居的列表（list[Node]）。
 * 
 * 提示：
 * 节点数介于 1 到 100 之间。
 * 无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
 * 由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
 * 必须将给定节点的拷贝作为对克隆图的引用返回。
 * 
 * 思路：这道题就是遍历整个图
 * 方法：DFS（递归+迭代）  BFS  
 * 为了复制两个节点之间边的关系，我们用一个map去记录原节点和新节点之间的对应关系，
 * 这样遍历节点的的时候我们就可以通过map查到复制节点，把他们的边复制上
 * @author yajie
 *
 */
class Node {
	public int val;
	public List<Node> neighbors;

	public Node() {

	}

	public Node(int _val, List<Node> _neighbors) {

		this.neighbors = _neighbors;
	}
}

public class CloneGraph {

	// 方法1：DFS 递归(超出时间限制)
	public static Node cloneGraph0(Node node) {
		// 原节点与新clone的节点的映射
		HashMap<Node, Node> lookup = new HashMap<>();
		return dfs(node, lookup);

	}

	public static Node dfs(Node node, HashMap<Node, Node> lookup) {
		if (node == null) {
			return null;
		}
		// 如果这个原节点已经复制了的话，就返回对应的clone节点
		if (lookup.containsKey(node)) {
			return lookup.get(node);
		}
		Node clone = new Node(node.val, new ArrayList<>());
		for (Node neighbor : node.neighbors) {
			clone.neighbors.add(dfs(neighbor, lookup));
		}
		return clone;

	}

	// 方法1：DFS 迭代
	public static Node cloneGraph1(Node node) {
		if (node == null) {
			return null;
		}
		// 原节点与新clone的节点的映射
		HashMap<Node, Node> lookup = new HashMap<>();

		Node clone = new Node(node.val, new ArrayList<>());
		lookup.put(node, clone);

		Stack<Node> stack = new Stack<>();
		stack.push(node);

		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			for (Node neighbor : cur.neighbors) {
				if (!lookup.containsKey(neighbor)) {
					lookup.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
					// 入栈
					stack.push(neighbor);
				}
				lookup.get(cur).neighbors.add(lookup.get(neighbor));
			}
		}
		return clone;

	}

	// 方法2：BFS
	public static Node cloneGraph(Node node) {
		if (node == null) {
			return null;
		}
		// 原节点与新clone的节点的映射
		HashMap<Node, Node> lookup = new HashMap<>();
		Node clone = new Node(node.val, new ArrayList<>());
		lookup.put(node, clone);
		// 队列
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(node);
		while (!queue.isEmpty()) {
			Node cur = queue.remove();
			// 遍历cur的邻居节点
			for (Node neighbor : cur.neighbors) {
				// 如果该邻居节点还没有复制
				if (!lookup.containsKey(neighbor)) {
					// 复制
					lookup.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
					// 并加入队列
					queue.offer(neighbor);
				}
				// 该邻居节点已经复制了
				lookup.get(cur).neighbors.add(lookup.get(neighbor));
			}

		}
		return clone;
	}
}
