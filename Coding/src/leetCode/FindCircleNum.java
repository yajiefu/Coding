package leetCode;


/**
 * 题目：547.朋友圈
 * 描述：班上有N名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A是 B的朋友，B是 C的朋友，那么我们可以认为 A也是 C的朋友。
 * 所谓的朋友圈，是指所有朋友的集合。给定一个N*N的矩阵M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i个和 j个学生互为朋友关系，
 * 否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 * 示例 1:
 * 输入: 
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 *  输出: 2 
 *  说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
 *  第2个学生自己在一个朋友圈。所以返回2。
 *  示例 2:
 *  输入: 
 *  [[1,1,0],
 *   [1,1,1],
 *   [0,1,1]]
 *  输出: 1
 *  说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
 *  注意：N 在[1,200]的范围内。对于所有学生，有M[i][i] = 1。如果有M[i][j] = 1，则有M[j][i] = 1。
 *  
 *  
 *  思路1：DFS
 *  DFS中的朋友圈问题，注意这是个N*N矩阵，可以使用一个visited数组, 依次判断每个节点（人）, 如果其未访问, 朋友圈数加1并对该节点进行dfs搜索标记所有访问到的节点。
 *  时间复杂度：O(N*N)
 *  空间复杂度：O(N)
 *  
 *  思路2：并查集
 * 并查集的特点是孩子结点指向父亲结点，两个结点连接在一起即它们有相同的根结点。下面是对编码的两点说明：
 * 1、这里使用了基于 rank 的结点指向策略，rank 的含义是以自己为根结点的树的高度。
 * 2、在 find 的过程中，实现了路径压缩算法，简而言之就在查询的过程中，修改结点的指向，将原本指向父亲结点修改成指向爷爷结点，以压缩这个多叉树的高度。

 * @author yajie
 *
 */


public class FindCircleNum {
	// 方法1：DFS
	public static int findCircleNum1(int[][] M) {
		int len = M.length;// 二维数组长度，即所有人的个数
		boolean[] visited = new boolean[M.length];// 访问标志，默认为false
		int count = 0;// 统计朋友圈个数
		for (int i = 0; i < len; i++) {// 对于每个人
			if (!visited[i]) {// 如果没有被访问
				count++;// 朋友圈个数+1
				dfs(M, i, visited);// 深度优先搜索，访问

			}
		}
		return count;

	}

	public static void dfs(int[][] M, int i, boolean[] visited) {
		visited[i] = true;// 访问过的置为true.
		for (int j = 0; j < M[i].length; j++) {
			if (visited[j] == false && M[i][j] == 1) {
				dfs(M, j, visited);
			}
		}

	}

	// 方法2：并查集
	public static int findCircleNum(int[][] M) {
		if (M == null || M.length <= 0) {
			return 0;
		}
		
		int n = M.length;
		UnionFind unionFind = new UnionFind(n);
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (M[i][j] == 1) {
					unionFind.union(i, j);
				}
			}
		}
		return unionFind.getCount();
	}

	public static void main(String[] args) {
		int[][] M = { { 1, 1, 1, 1 }, { 0,1, 1, 1 }, { 1, 1, 1, 1 },{1,1,1,1} };
		System.out.println(findCircleNum(M));
	}

}
//并查集
class UnionFind {
	private int count;
	private int[] parent;
	// 以索引为 i 的元素为根结点的树的深度（最深的那个深度）
	private int[] rank;

	public UnionFind(int n) {
		this.count = n;
		this.parent = new int[n];
		this.rank = new int[n];
		// 初始化：所有的元素只包含它自己，只有一个元素，所以 rank[i] = 1，上级就是它本身。
		for (int i = 0; i < n; i++) {
			this.parent[i] = i;
			this.rank[i] = 0;
		}
	}

	// 带有路径压缩的查找操作:寻找掌门人，即寻找根节点
	// 为了进一步减短查找路径，可以使查找路径中的每一个节点都指向根结点，这就是路径压缩
	public int find(int i) {
		if (parent[i] != i) {
			parent[i] = find(parent[i]);
		}
		return parent[i];
	}

	// 合并。优化方法：按秩合并，即rank,总是将更小的树连接至更大的树上。
	// 单元素的树的秩定义为1，当两棵秩同为r的树联合时，它们的秩r+1
	public void union(int x, int y) {
		int xroot = find(x);
		int yroot = find(y);
		if (xroot == yroot) {
			return;
		}
//		parent[rootx] = rooty;//这个是最基础的合并。这里我们是做了优化：按秩优化
		if (rank[xroot] > rank[yroot]) {
			parent[yroot] = xroot;
		} else if (rank[xroot] < rank[yroot]) {
			parent[xroot] = yroot;
		} else {
			parent[yroot] = xroot;
			rank[xroot] += 1;// rootx的等级上升一级
		}
		count--;// 两个区域合并，区域个数减少1个。

	}

	public int getCount() {
		return count;
	}
}
