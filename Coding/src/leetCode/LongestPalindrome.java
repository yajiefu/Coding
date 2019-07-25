package leetCode;
/**
 * 题目：5.最长回文子串
 * 描述：给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 * @author yajie
 *
 *
 *思路1：中心扩展法
 *    我们观察到回文中心的两侧互为镜像。
 *    因此，回文可以从它的中心展开，并且只有 2n-1个这样的中心。
 *    你可能会问，为什么会是 2n-1个，而不是 n个中心？
 *    原因在于所含字母数为偶数的回文的中心可以处于两字母之间
 *时间复杂度：O(n^2)，由于围绕中心来扩展回文会耗去 O(n)的时间，所以总的复杂度为 O(n^2)
 *空间复杂度：O(1)
 *
 *
 *思路2：动态规划
 *     dp[l, r] = (s[l] == s[r] and (r - l <= 2or dp[l + 1, r - 1]))
 *     如果s[l] == s[r]，接着判断[l + 1, r - 1]区间，如果这区间的数字>=2的话就看dp[l + 1, r - 1]，
 *     如果就一个数字或者空，就一定是回文.即 r-1<=l+1，r-l<=2
 *     因此二维数组的顺序是从左往右，从上到下
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n^2)    
 *     
 *思路3:Manacher   
 *    被中国程序员戏称为“马拉车”算法。专门用于解决“最长回文子串”问题，时间复杂度为 O(n)
 *    
 *    
 * 详情：   https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
 */
public class LongestPalindrome {
	
	
	//思路1：中心扩展法
	public static String longestPalindrome1(String s) {
		if (s == null || s.length() < 1) {
			return "";
		}
		int start = 0;
		int end = 0;

		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);
			int len2 = expandAroundCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				// 根据len和i更新start 和end
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);

	}

	public static int expandAroundCenter(String s, int left, int right) {
		int L = left;
		int R = right;
		while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
			L--;
			R++;
		}
		return R - L - 1;
	}
	
	//思路2：动态规划
	
	public static String longestPalindrome(String s) {
		if (s == null || s.length() <= 1) {
			return s;
		}

		char[] chs = s.toCharArray();
		int len = chs.length;
		boolean[][] dp = new boolean[len][len];
		int longestPalindrome = 1;
		String longestPalindromeStr = s.substring(0, 1);
		for (int r = 1; r < len; r++) {
			for (int l = 0; l < r; l++) {
				if (chs[l] == chs[r] && (r - l <= 2 || dp[l + 1][r - 1])) {
					dp[l][r] = true;
					if (r - l + 1 > longestPalindrome) {
						longestPalindrome = r - l + 1;
						longestPalindromeStr = s.substring(l, r + 1);
					}
				}
			}
		}
		return longestPalindromeStr;

	}
	public static void main(String[] args) {
		System.out.println(longestPalindrome("ac"));
	}
}
