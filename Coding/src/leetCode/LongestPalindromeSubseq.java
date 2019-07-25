package leetCode;
/**
 * 题目：516.最长回文子序列
 * 描述：给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。
 * 示例 1:输入:"bbbab"  输出:4   一个可能的最长回文子序列为 "bbbb"。
 * 示例 2:输入:"cbbd"   输出:2   一个可能的最长回文子序列为 "bb"。
 * 
 * 
 * 注意：子序列可以不连续的，可以跳过某些单词，子串是必须连续的
 * 
 * 思路：动态规划 设dp[i][j]表示s[i:j]中的最长回文子序列长度，则
 *  (1) s[i] == s[j] ==> dp[i][j] = dp[i+1][j-1] + 2;
 *  (2) s[i] != s[j] ==> dp[i][j] = max(dp[i+1][j], dp[i][j-1])
 *  初始化: dp[i][i] = 1
 *  return dp[0][n-1]
 *  
 *  二维数组的计算顺序是 i[n-1 -> 0]
 *                   j[i+1 -> n-1]
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n^2)
 * 
 * 
 * 优化：空间复杂度为O(n)??怎么优化呢
 * @author yajie
 *
 */
public class LongestPalindromeSubseq {
	
	public static int longestPalindromeSubseq(String s) {
		if (s == null || s.length() <= 0) {
			return 0;
		}
		int n = s.length();
		char[] chs = s.toCharArray();
		int[][] dp = new int[n][n];
		for(int i = n - 1; i >= 0; i--) {
			dp[i][i] = 1;
			for(int j = i + 1; j < n; j++) {
				if (chs[i] == chs[j]) {
					dp[i][j] = dp[i+1][j-1] + 2;
				}else {
					dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
				}
			}
		}
		return dp[0][n-1];
	}

	public static void main(String[] args) {
		System.out.println(longestPalindromeSubseq("bbbab"));
		System.out.println(longestPalindromeSubseq("cbbd"));
	}
}
