package swordForOffer;

import java.util.HashMap;

/*
 * 题目：第一个只出现一次的字符
 * 描述：在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）
 * 相关题目：字符流中第一个不重复的字符
 * 
 * 思路：HashMap,由于全部由字母组成，所以最多26*2
 * 另外：由于HashMap的key是无序的，不知道哪个是第一个不重复的字符，所以我们可以用LinkedHashMap。但是最后我们可以遍历chs，所以没关系
 */
public class FirstNotRepeatingCharII {
	public static int firstNotRepeatingChar(String str) {
		HashMap<Character, Integer> map = new HashMap<>();
		char[] chs = str.toCharArray();
		int len = chs.length;
		for (int i = 0; i < len; i++) {
			if (map.containsKey(chs[i])) {
				map.put(chs[i], map.get(chs[i]) + 1);
			} else {
				map.put(chs[i], 1);
			}
		}
		int index = -1;
		for (int i = 0; i < len; i++) {
			if (map.get(chs[i]) == 1) {
				return i;
			}
		}
		return index;
	}

	public static void main(String[] args) {
		System.out.println(firstNotRepeatingChar("ababdcdcfeehh"));
	}
}
