package leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 题目：1042.不邻接植花
 * 描述：有 N 个花园，按从 1 到 N 标记。在每个花园中，你打算种下四种花之一。
 *    paths[i] = [x, y] 描述了花园 x 到花园 y 的双向路径。
 *             另外，没有花园有 3 条以上的路径可以进入或者离开。
 *             你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
 *             以数组形式返回选择的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。
 *             花的种类用  1, 2, 3, 4 表示。保证存在答案。
 *             
 *              示例 1：
 *              输入：N = 3, paths = [[1,2],[2,3],[3,1]]
 *              输出：[1,2,3]
 *              示例 2：
 *              输入：N = 4, paths = [[1,2],[3,4]]
 *              输出：[1,2,1,2]
 *              示例 3：
 *              输入：N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 *              输出：[1,2,3,4]
 *              
 *              提示：
 *              1 <= N <= 10000
 *              0 <= paths.size <= 20000
 *              不存在花园有 4 条或者更多路径可以进入或离开。
 *              保证存在答案。
 * @author yajie
 *
 *思路：题中限制一个节点的度最多就为3，而颜色一共有4种，也就是一定可以染上色，这样我们先把图构造出来，然后遍历每个节点的邻节点，当前节点+邻节点最多四个，
 *   这样就用邻节点没有用过的一个颜色将当前节点染色即可。
 */
public class GardenNoAdj {
	public static int[] gardenNoAdj(int N, int[][] paths) {
		//新建一个map，key存的是该花园，value存的是与之相邻的花园。
		Map<Integer, HashSet<Integer>> graph = new HashMap<>();
		for(int i = 0; i < N; i++) {
			graph.put(i, new HashSet<>());
		}
		for (int[] path : paths) {
			graph.get(path[0] - 1).add(path[1] - 1);
			graph.get(path[1] - 1).add(path[0] - 1);
		}
		int[] res = new int[N];
		return res;

	}
}
