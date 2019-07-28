package leetCode;
/**
 * 题目： 11.盛最多水的容器
 * 描述：给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 *     在坐标内画 n条垂直线，垂直线 i的两个端点分别为(i, ai) 和 (i, 0)。
 *     找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 
 * 示例:
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 * 
 * 方法1：动态规划（为什么我第一反应就是动态规划），时间复杂度：O(n^2) 空间复杂度：O(n^2)。超出内存限制
 * 方法2：暴力解法：时间复杂度：O(n^2) 空间复杂度：O(1)
 * 方法3：双指针。时间复杂度：O(n) 空间复杂度：O(1)
 * @author yajie
 *
 */
public class MaxArea {
	public static int maxArea1(int[] height) {
		if (height == null || height.length <= 1) {
			return 0;
		}
		int n = height.length;
		int[][] dp = new int[n][n];
		for (int i = n - 1; i >= 0; i--) {
			dp[i][i] = 0;
			for (int j = i + 1; j < n; j++) {
				int temp = Math.min(height[i], height[j]) * (j - i);
				if (temp > dp[i + 1][j] && temp > dp[i][j - 1]) {
					dp[i][j] = temp;
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
				}

			}
		}
		return dp[0][n - 1];
	}

	public static int maxArea2(int[] height) {
		int max = 0;
		for (int i = 0; i < height.length - 1; i++) {
			for (int j = i + 1; j < height.length; j++) {
				int h = Math.min(height[i], height[j]);
				if (h * (j - i) > max) {
					max = h * (j - i);
				}
			}
		}
		return max;

	}

	// 方法3：双指针
	public static int maxArea(int[] height) {
		int i = 0;
		int j = height.length - 1;
		int max = 0;
		while (i < j) {
			max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
			if (height[i] > height[j]) {
				j--;
			} else {
				i++;
			}
		}
		return max;

	}

	public static void main(String[] args) {
		int[] height = { 6, 4, 3, 1, 4, 6, 99, 62, 1, 2, 6 };
		System.out.println(maxArea1(height));
		System.out.println(maxArea2(height));
		System.out.println(maxArea(height));
	}
}
