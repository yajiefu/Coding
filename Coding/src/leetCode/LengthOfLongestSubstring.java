package leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 题目：3.无重复字符的最长字串
 * 描述： 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3 
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 
 *  请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *  
 *  
 * 思路：
 * 方法1：遍历s的字符，从某i到最后去找以i开始的不重复字串，并比较长度，找到最长的。
 *    要检查一个字符串是否有重复字符，我们可以使用集合set
 *    时间复杂度：O(n3)  
 *  n−1   n            n−1
 *O(∑  (  ∑ (j−i))) =O( ∑ (1+n−i)(n−i)/2 = O(n3)
 *  i=0 j=i+1          i=0
 *
 *
 *    空间复杂度：O(max(m,n)我们需要 O(k)的空间来检查子字符串中是否有重复字符
 *                           其中 k表示 Set 的大小。而 Set 的大小取决于字符串 n的大小以及字符集/字母 m的大小。
 * 
 * 
 * 方法2：滑动窗口 用[i,j)来表示窗口，要检查一个字符串是否有重复字符，我们可以使用集合set
 *   时间复杂度：O(2n) = O(n)，在最糟糕的情况下，每个字符将被 i和 j访问两次。
 *   空间复杂度：O(min(m,n))，与之前的方法相同。
 *                      滑动窗口法需要O(k)的空间，其中K表示 Set 的大小。而 Set 的大小取决于字符串 n的大小以及字符集 / 字母 m的大小
 * 
 * 
 * 
 * 方法3：当我们在[i,j)的窗口中找到了j'和j相同的字符，我们没有必要一直i++的删除，可以直接将i跳到j'+1
 *   时间复杂度： O(n)   空间复杂度：O(min(m,n))
 * @author yajie
 *
 */
public class LengthOfLongestSubstring {

	// 方法1:
	public static int lengthOfLongestSubstring1(String s) {
		int maxLength = 0;
		char[] chs = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			int len = match(chs, i);
			if (len > maxLength) {
				maxLength = len;
			}
		}
		return maxLength;
	}

	public static int match(char[] chs, int start) {
		Set<Character> set = new HashSet<>();
		int len = 0;
		for (int i = start; i < chs.length; i++) {
			if (!set.contains(chs[i])) {
				set.add(chs[i]);
				len++;
			} else {
				break;
			}
		}
		return len;
	}

	// 方法2：滑动窗口 用[i,j)来表示窗口
	public static int lengthOfLongestSubstring2(String s) {
		int n = s.length();
		char[] chs = s.toCharArray();
		int maxLength = 0;
		int i = 0;
		int j = 0;
		Set<Character> set = new HashSet<>();
		while (i < n && j < n) {
			if (!set.contains(chs[j])) {
				set.add(chs[j++]);
				if ((j - i) > maxLength) {
					maxLength = j - i;
				}
			} else {
				set.remove(chs[i++]);
			}
		}
		return maxLength;
	}

	//方法3：优化的滑动窗口
	public static int lengthOfLongestSubstring(String s) {
		int n = s.length();
		char[] chs = s.toCharArray();
		int maxLength = 0;
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0, j = 0; j < n; j++) {
			if (map.containsKey(chs[j])) {
				//是因为滑动窗口i时可能会跳过一些元素，而被跳过的元素还在hashSet中
				i = Math.max(map.get(chs[j])+1, i);
			}
			map.put(chs[j], j);
			maxLength = Math.max(maxLength, j - i + 1);
		}
		return maxLength;
	}

	public static void main(String[] args) {
		String s1 = " ";
		System.out.println(lengthOfLongestSubstring(s1));

		String s2 = "abba";
		System.out.println(lengthOfLongestSubstring(s2));

		String s3 = "pwwkew";
		System.out.println(lengthOfLongestSubstring(s3));
	}
}
