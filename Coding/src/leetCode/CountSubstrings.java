package leetCode;
/**
 * 题目：647.回文子串
 * 描述：给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *     具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *     
 * 示例 1:
 * 输入: "abc"  输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 * 
 * 示例 2:
 * 输入: "aaa"  输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 * 
 * 注意:输入的字符串长度不会超过1000。
 * 
 * 方法1：中心扩展法,速度快
 *      时间复杂度：O(n^2),空间复杂度：O(1)
 * 方法2：动态规划
 *      时间复杂度：O(n^2),空间复杂度：O(n^2)
 * 方法3：马拉车
 * @author yajie
 *
 */
public class CountSubstrings {
	//方法1：中心扩展法,速度快
	public static int countSubstrings1(String s) {
		if (s == null || s.length() <= 0) {
			return 0;
		}
		int ans = 0;
		for(int i = 0; i < s.length(); i++) {
			ans += expandAroundCenter(s, i, i);
			ans += expandAroundCenter(s, i, i + 1);
		}
		return ans;

	}
	
	public static int expandAroundCenter(String s, int left, int right) {
		int count = 0;
		int L = left;
		int R = right;
		while(L >= 0 && R < s.length() && s.charAt(R) == s.charAt(L)) {
			count++;
			L--;
			R++;
		}
		return count;
	}
	
	//动态规划
	public static int countSubstrings(String s) {
		if (s == null || s.length() <= 0) {
			return 0;
		}
		int len = s.length();
		int count = 0;
		char[] chs = s.toCharArray();
		boolean[][] dp = new boolean[len][len];
		
		for(int r = 0; r < len; r++) {
			for(int l = 0; l <= r; l++) {
				if (l == r) {
					dp[l][r] = true;
					count++;
				}else {
					if ((chs[l] == chs[r]) && (dp[l+1][r-1] || r - l <= 2)) {
						dp[l][r] = true;
						count++;
					}
				}
				
			}
		}
		return count;
	}
	public static void main(String[] args) {
		System.out.println(countSubstrings("aba"));
	}
}
