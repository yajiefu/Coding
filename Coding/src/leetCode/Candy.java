package leetCode;

import java.util.Arrays;

/**
 * 题目：135.分糖果
 * 描述：老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 * 示例 1:
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * 示例 2:
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 * 
 * 
 * 
 * 思路：
 * 先从左至右遍历一遍学生，按照以下规则给糖：
 * 先给所有小朋友1颗糖；
 * 若第i名学生比i - 1名学生分数高，则第i名学生糖应比第i - 1名学生多一个。
 * 在此规则下，可以保证所有学生左边学生的糖数量符合规则。
 * 同理，从右至左遍历一遍学生，保证所有学生右边学生的糖数量符合规则。
 * 最终，取两遍遍历对应学生糖果数最大值（这样对于每个学生，左右学生糖果数量都满足），即可得最少糖果数量。
 * 时间空间复杂度都为 O(N)。
 * @author yajie
 *
 */
public class Candy {
	public static int candy(int[] ratings) {
		if (ratings == null || ratings.length == 0) {
			return 0;
		}
		int n = ratings.length;
		if (n == 1) {
			return 1;
		}
		int[] left = new int[n];
		int[] right = new int[n];
		Arrays.fill(left, 1);
		Arrays.fill(right, 1);
		// 从左边
		for (int i = 1; i < n; i++) {
			if (ratings[i - 1] < ratings[i]) {
				left[i] = left[i - 1] + 1;

			}
		}

		// 从右边
		// sum可以在这个循环就开始计算了
		int sum = left[n-1];
		for (int i = n - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1]) {
				right[i] = right[i + 1] + 1;
			}
			sum += Math.max(left[i], right[i]);
		}
//		int sum = 0;
//		for (int i = 0; i < n; i++) {
//			if (left[i] < right[i]) {
//				left[i] = right[i];
//			}
//			sum += left[i];
//		}
		return sum;

	}

	public static void main(String[] args) {
		int[] nums = {1,0,2};
//		int[] nums = { 1, 2, 2 };
		System.out.println(candy(nums));

	}

}
