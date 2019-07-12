package leetCode;
/**
 *题目：997.找到小镇的法官
 *描述：在一个小镇里，按从 1 到 N 标记了 N 个人。传言称，这些人中有一个是小镇上的秘密法官。
 *如果小镇的法官真的存在，那么：
 *   1.小镇的法官不相信任何人。
 *   2.每个人（除了小镇法官外）都信任小镇的法官。
 *   3.只有一个人同时满足属性 1 和属性 2 。
 *给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示标记为 a 的人信任标记为 b 的人。
 *如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的标记。否则，返回 -1
 *
 *思路：分别用两个数组存储，一个用来存储入度数，也就是相信你的人的个数，数组下标就是代表该人，1到N
 *           另外一个用来存储出度数，也就是你相信的人的个数
 *           入度数等于N-1，出度数为0，则这个人就是法官，否则无法确定法官。
 * @author yajie
 *
 */
public class FindJudge {
	public static int findJudge(int N, int[][] trust) {
		int[] trusts = new int[N + 1]; //出度
		int[] trusted = new int[N + 1];//入度
		
		for(int i = 0; i < trust.length; i++) {
			trusts[trust[i][0]] ++;//出度+1
			trusted[trust[i][1]] ++;//入度+1
		}
		for(int i = 1; i <= N; i++) {
			if (trusted[i] == N-1 && trusts[i] == 0) {
				return i;
			}
		}
		return -1;

	}
	public static void main(String[] args) {
		int[][] trust = {{1,3},{1,4},{2,3},{2,4},{4,3}};
		System.out.println(findJudge(4, trust));
		
	}
}
