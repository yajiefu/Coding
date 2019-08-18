package other;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
/**
 * 题目：搭积木
 * https://www.nowcoder.com/questionTerminal/55371b74b2f243e3820e57ee4c7b5504?toCommentId=3564835
 * 小明有一袋子长方形的积木，如果一个积木A的长和宽都不大于另外一个积木B的长和宽，则积木A可以搭在积木B的上面。好奇的小明特别想知道这一袋子积木最多可以搭多少层，你能帮他想想办法吗？
 * 定义每一个长方形的长L和宽W都为正整数，并且1 <= W <= L <= INT_MAX, 袋子里面长方形的个数为N, 并且 1 <= N <= 1000000.
 * 假如袋子里共有5个积木分别为 (2, 2), (2, 4), (3, 3), (2, 5), (4, 5), 则不难判断这些积木最多可以搭成4层, 因为(2, 2) < (2, 4) < (2, 5) < (4, 5)。
 * 输入描述:
 * 第一行为积木的总个数 N
 * 之后一共有N行，分别对应于每一个积木的宽W和长L
 * 输出描述:
 * 输出总共可以搭的层数
 * 示例1
 * 输入
 * 5
 * 2 2
 * 2 4
 * 3 3
 * 2 5
 * 4 5
 * 输出
 * 4
 */
public class BuildingBlocks {
	public static int[] paInts(String string) {
		int[] res = new int[2];
		String[] ans = string.split(" ");
		res[0] = Integer.valueOf(ans[0]);
		res[1] = Integer.valueOf(ans[1]);
		return res;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String string = in.nextLine();
		int n = Integer.valueOf(string);
		int[][] blocks = new int[n][2];
		for (int i = 0; i < n; i++) {
			String str = in.nextLine();
			int[] res = paInts(str);
			blocks[i][0] = res[0];
			blocks[i][1] = res[1];

		}
		System.out.println(countBlock(blocks, n));
//		int n = 5;
//		int[][] blocks = {{2,2},{4,4},{3,3},{4,4},{4,5}};
//		System.out.println(countBlock(blocks, n));
	}

	public static int countBlock(int[][] blocks, int n) {
		// 先按照长排序,相等的话按照宽排序
		Arrays.sort(blocks, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] - o2[0] != 0) {
					return o1[0] - o2[0];
				} else {
					return o1[1] - o2[1];
				}
			}
		});

//		for(int i = 0; i < blocks.length; i++) {
//			System.out.println(Arrays.toString(blocks[i]));
//		}

		// 求宽的最长不递减子序列(和最长上升子序列有点不太一样)
		int[] tail = new int[n];
		tail[0] = blocks[0][1];
		int end = 0;
		for (int i = 1; i < n; i++) {
			int cur = blocks[i][1];
			if (cur >= tail[end]) {//相对于最长上身子序列，这里将大于改成大于等于
				end++;
				tail[end] = cur;
			} else {
				// 用二分的方法找到最小的大于cur的数的位置并且替换掉。
				int left = 0;
				int right = end;
				while (left < right) {
					int mid = (left + right) >>> 1;
					if (tail[mid] < cur) {
						left = mid + 1;
					} else {
						right = mid;
					}
				}
				tail[left] = cur;
			}
		}
		end++;// end是索引，所以长度得+1；
		return end;
	}

}
