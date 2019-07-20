package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：524. 通过删除字母匹配到字典里最长单词
 * 描述：给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。
 *     如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
 * 示例 1:
 * 输入:s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * 输出: "apple"
 * 示例 2:
 * 输入:s = "abpcplea", d = ["a","b","c"]
 * 输出: "a"
 * 说明:所有输入的字符串只包含小写字母。
 *     字典的大小不会超过 1000。
 *     所有输入的字符串长度不会超过 1000。
 *     
 * 思路：写一个match函数，判断d里面的string是否符合。
 *     然后再依次比较长度和字典顺序

 * @author yajie
 *
 */
public class FindLongestWord {

	public static String findLongestWord(String s, List<String> d) {
		String result = "";
		int longestLen = 0;
		
		for (String string : d) {
			if (match(s, string) && string.length() > longestLen) {
				if (string.length() > longestLen) {
					result = string;
					longestLen = result.length();
				}else {
					for(int i = 0; i < longestLen; i++) {
						if (string.charAt(i) - result.charAt(i) < 0) {
							result = string;
							break;
						}else if (string.charAt(i) - result.charAt(i) > 0) {
							break;
						}
					}
				}
			}
		}
		return result;
	}
	
	public static boolean match(String s, String string) {
		int p = 0;//p指向字符串s
		int i = 0;//i指向待比较字符串string
		while(i < string.length() && p < s.length()) {
			if (s.charAt(p) == string.charAt(i)) {
				p++;
				i++;
			}else {
				p++;
			}
		}
		if (i == string.length()) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		String string = "abpcplea";
		List<String> list = new ArrayList<>();
		list.add("ale");
		list.add("apple");
		list.add("monkey");
		list.add("plea");
		
		System.out.println(findLongestWord(string, list));
	}
	

}
