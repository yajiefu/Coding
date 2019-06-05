package swordForOffer;
/*
 * 题目：正则表达式匹配
 * 描述：请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，
 * 而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 * 
 * 
 * 思路：
 * 前提：当pattern遍历完，return取决于str是否遍历完，str恰好遍历完才返回true，再接下来讨论
 * 1.若当前字符存在下一个字符，看下一个字符是否是 '*'，如果是，有2种情况
 *   (1)当前不匹配
 *       match(str, i, pattern, j+2);
 *   (2)当前匹配
 *       1)	相当于把*前面的字符出现0次 :match(str,i,pattern,j+2)
 *       2)	相当于把*前面的字符出现1次:match(str,i+1,pattern,j+2) 这种情况相当于1+3
 *       3)	相当于把*前面的字符出现>1次:match(str,i+1,pattenr,j)
 * 2.若当前字符存在下一个字符，看下一个字符不是 '*'
 *   (1)当前匹配或者当前为‘.’  match(str,i+1,pattern,j+1)
 *   (2)当前不匹配，flase
 *   
 */

public class Match {
	public static boolean match(char[] str, char[] pattern) {
		if (str == null || pattern == null) {
			return false;
		}
		return match(str, 0, pattern, 0);
	}
	
	public static boolean match(char[] str,  int i, char[] pattern, int j) {
		if (j == pattern.length) {
			return i == str.length;
		}
		//1.若当前字符存在下一个字符，看下一个字符是否是 '*'
		if (j < pattern.length - 1 && pattern[j+1] == '*') {
			// 1.2当前匹配
			if (i < str.length && (str[i] == pattern[j] || pattern[j] == '.')) {
				return match(str,i, pattern,j+2) || match(str, i+1, pattern, j);
			}else {
				return match(str, i, pattern, j+2);
			}
		}
		// 2.若当前字符存在下一个字符，看下一个字符不是 '*'
		//  (1)当前匹配或者当前为‘.’  match(str,i+1,pattern,j+1)
		if (i < str.length && (str[i] == pattern[j] || pattern[j] == '.')) {
			return match(str,i+1,pattern,j+1);
		}
		// (2)当前不匹配，flase
		return false;
	}
	public static void main(String[] args) {
		String str = "aaa";
	    String pattern1 = "a.a";
	    String pattern2 = "ab*ac*a";
	    String pattern3 = "aa.a";
	    String pattern4 = "ab*a";
		System.out.println(match(str.toCharArray(), pattern1.toCharArray()));
		System.out.println(match(str.toCharArray(), pattern2.toCharArray()));
		System.out.println(match(str.toCharArray(), pattern3.toCharArray()));
		System.out.println(match(str.toCharArray(), pattern4.toCharArray()));

	}

}
