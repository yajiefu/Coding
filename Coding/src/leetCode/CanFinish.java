package leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 题目：207.课程表
 * 描述：现在你总共有 n 门课需要选，记为 0 到 n-1。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
 * 示例 1:
 * 输入: 2, [[1,0]] 
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 * 
 * 说明:
 * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法https://blog.csdn.net/woaidapaopao/article/details/51732947。
 * 你可以假定输入的先决条件中没有重复的边。
 * 提示:
 * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
 * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
 * 拓扑排序也可以通过 BFS 完成。
 * 
 * 拓扑排序：https://blog.csdn.net/dm_vincent/article/details/7714519
 * 方法1：Kahn算法
 *   1.int[] indegrees 记录每个顶点当前的入度
 *   2.Queue<Integer> queue 存储入度为0的顶点，如果不使用队列，要想得到当前入度为 00 的结点，就得遍历一遍入度数组。使用队列即用空间换时间。
 *   3.List<Integer> res 存储拓扑排序结果
 *   
 *   每一次都从图中删除没有前驱的顶点，这里并不需要真正的做删除操作，我们可以设置一个入度数组，每一轮都输出入度为0的结点，并移除它、修改它指向的结点的入度（−1即可），
 *   依次得到的结点序列就是拓扑排序的结点序列。如果图中还有结点没有被移除，则说明“不能完成所有课程的学习”。
 *   拓扑排序保证了每个活动（在这题中是“课程”）的所有前驱活动都排在该活动的前面，并且可以完成所有活动。拓扑排序的结果不唯一。拓扑排序还可以用于检测一个有向图是否有环。相关的概念还有 AOV 网，这里就不展开了。
 *   流程：
 *   1、在开始排序前，扫描对应的存储空间（使用邻接表，题目给了二维数组），将入度为 0的结点放入队列。
 *   2、只要队列非空，就从队首取出入度为 00 的结点，将这个结点输出到结果集中，并且将这个结点的所有邻接结点（它指向的结点）的入度减 11，在减 11 以后，如果这个被减 11 的结点的入度为 00 ，就继续入队。
 *   3、当队列为空的时候，检查结果集中的顶点个数是否和课程数相等即可。
 *   时间复杂度：O(E+V)初始化入度为 0的集合需要遍历整张图，具体做法是检查每个结点和每条边，因此复杂度为O(E+V)，
 *        然后对该集合进行操作，又需要遍历整张图中的每个结点和每条边，复杂度也为 O(E+V)；
 *  空间复杂度：：O(V)
 * 
 * 
 * 方法2：DFS
 * 这里要使用逆邻接表。其实就是检测这个有向图中有没有环，只要存在环，这些课程就不能按要求学完。
 * 具体方法是：
 * 第 1 步：构建逆邻接表；
 * 第 2 步：递归处理每一个还没有被访问的结点，具体做法很简单：对于一个结点来说，先输出指向它的所有顶点，再输出自己。
 * 第 3 步：如果这个顶点还没有被遍历过，就递归遍历它，把所有指向它的结点都输出了，再输出自己。注意：当访问一个结点的时候，应当先递归访问它的前驱结点，直至前驱结点没有前驱结点为止。
 * @author yajie
 *
 */
public class CanFinish {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		if (numCourses <= 0) {
			return false;
		}

		int len = prerequisites.length;
		if (len == 0) {
			return true;
		}
		
		int[] indegrees = new int[numCourses];// 记录每个顶点当前的入度
		Queue<Integer> queue = new LinkedList<Integer>();// 存储入度为0的顶点
		ArrayList<Integer> res = new ArrayList<>();// 存储拓扑排序结果

		//计算入度
		for (int i = 0; i < len; i++) {
			indegrees[prerequisites[i][0]]++;
		}

		// 存储入度为0的顶点  
		for (int i = 0; i < numCourses; i++) {
			if (indegrees[i] == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			Integer num = queue.remove();
			// 加入拓扑排序结果集
			res.add(num);

			// 把全部临边遍历一下
			for (int[] p : prerequisites) {
				if (p[1] == num) {
					indegrees[p[0]]--;
					if (indegrees[p[0]] == 0) {
						queue.add(p[0]);
					}
				}
			}
		}
		return res.size() == numCourses;
	}
	//方法2：DFS
	/*
	 *  第 1 步：构建逆邻接表；
	 *   第 2 步：递归处理每一个还没有被访问的结点，具体做法很简单：对于一个结点来说，先输出指向它的所有顶点，再输出自己。
	 *   第 3 步：如果这个顶点还没有被遍历过，就递归遍历它，把所有指向它的结点都输出了，再输出自己。
	 *     注意：当访问一个结点的时候，应当先递归访问它的前驱结点，直至前驱结点没有前驱结点为止。

	 */
	public boolean canFinish1(int numCourses, int[][] prerequisites) {
		if (numCourses <= 0) {
			return false;
		}

		int len = prerequisites.length;
		if (len == 0) {
			return true;
		}

		// 1表示正在访问，2表示已经访问完了
		int[] marked = new int[numCourses];

		// 初始化有向图
		HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
		for (int i = 0; i < numCourses; i++) {
			graph.put(i, new HashSet<>());
		}

		// 有向图的key是前驱结点，value是后继结点
		for (int[] p : prerequisites) {
			graph.get(p[1]).add(p[0]);
		}

		// 递归处理每一个结点
		for (int i = 0; i < numCourses; i++) {
			if (dfs(i, graph, marked)) {
				// 如果存在返回true，表示途中存在环，表示课程任务不能完成
				return false;
			}
		}
		// 在遍历的过程中，一直 dfs 都没有遇到已经重复访问的结点，就表示有向图中没有环
		// 所有课程任务可以完成，应该返回 true
		return true;
	}

	public static boolean dfs(int i, HashMap<Integer, HashSet<Integer>> graph, int[] marked) {
		// 如果访问过了就不再访问了
		if (marked[i] == 1) {
			// 正在访问中，就表示遇到了环
			return true;

		}
		if (marked[i] == 2) {
			// 表示再访问中没有遇到环，这个结点访问过了
			return false;
		}
		// 走到这里，表示还没有被访问，此时marked[i]=0
		// 表示正在访问
		marked[i] = 1;
		// 后继结点的集合
		HashSet<Integer> nodes = graph.get(i);

		for (Integer n : nodes) {
			// 递归判断
			if (dfs(n, graph, marked)) {
				// 如果层层递归为true，表示正在访问，表示途中存在环
				return true;
			}
		}
		// i 的所有后继结点都访问完了都不存在环，则这个结点可以被标记为已经访问结束
		marked[i] = 2;
		// false表示途中不存在环
		return false;

	}
}
