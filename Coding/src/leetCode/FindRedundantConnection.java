package leetCode;
/**
 * 题目：684.冗余连接
 * 描述：在本问题中, 树指的是一个连通且无环的无向图。
 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。
 * 附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。
 * 答案边 [u, v] 应满足相同的格式 u < v。
 * 示例 1：
 * 输入: [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 解释: 给定的无向图为:
 *     1
 *    / \
 *   2 - 3
 *   示例 2：
 *   输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 *   输出: [1,4]
 *   解释: 给定的无向图为:
 *   5 - 1 - 2
 *       |   |
 *       4 - 3
 *    注意:
 *    输入的二维数组大小在 3 到 1000。
 *    二维数组中的整数在1到N之间，其中N是输入数组的大小。
 *    
 *    
 * 方法：并查集
 * 对于同一棵树的所有节点来说，都拥有共同的祖先节点。
 * 因此，判断冗余连接的条件即为，判断新加入的边，两个节点是否有共同的祖先。
 * （1）如果有共同的祖先，则说明这条边是冗余的边；
 * （2）如果没有共同的祖先，则说明这两条边并未加入树中，因此进行合并操作。循环边的记录，获取最后出现的冗余边，就是答案。
 * 
 * 方法2：DFS，对于每个边 (u, v)，用深度优先搜索遍历图，以查看是否可以将 u 连接到 v。如果可以，则它是重复边
 * @author yajie
 *
 */
public class FindRedundantConnection {
	// 方法1：并查集
	public static int[] findRedundantConnection(int[][] edges) {
		int[] res = new int[2];
		UnionFind unionFind = new UnionFind(edges.length);
		// 第一条边肯定不在树中，可以直接加入
		unionFind.union(edges[0][0] - 1, edges[0][1] - 1);
		for (int i = 1; i < edges.length; i++) {
			if (unionFind.find(edges[i][0] - 1) == unionFind.find(edges[i][1] - 1)) {
				res[0] = edges[i][0];
				res[1] = edges[i][1];
			} else {
				unionFind.union(edges[i][0] - 1, edges[i][1] - 1);
			}

		}
		return res;
	}

	public static void main(String[] args) {
		int[][] edges = { { 1, 2 }, { 1, 3 }, { 2, 3 } };
		int[] res = findRedundantConnection(edges);
		System.out.println(res[0] + " " + res[1]);
	}

	// 并查集
	public static class UnionFind {
		private int[] parent;
		private int[] rank;
		private int count;

		public UnionFind(int total) {
			this.count = total;
			this.parent = new int[total];
			this.rank = new int[total];
			for (int i = 0; i < total; i++) {
				parent[i] = i;
				rank[i] = 1;
			}
		}

		public int find(int i) {
			while (i != parent[i]) {
				i = parent[i];
			}
			return i;
		}

		public void union(int x, int y) {
			int xroot = find(x);
			int yroot = find(y);
			if (xroot == yroot) {
				return;
			}
			if (rank[xroot] > rank[yroot]) {
				parent[yroot] = xroot;
			} else if (rank[xroot] < rank[yroot]) {
				parent[xroot] = yroot;
			} else {
				parent[yroot] = xroot;
				rank[xroot] += 1;
			}
			count--;
		}

		public int getCount() {
			return this.count;
		}
	}

}
