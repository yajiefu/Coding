package swordForOffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

/*
 * 题目：字符串的排列
 * 描述：输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 *     例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 *     
 *     
 * 思路：递归，把字符串分为两个部分，  a(bc),a与后面的依次交换位置，接下来再求括号里的排列
 * 比如    a(bc) a(cb)
 *     b(ac) b(ca)
 *     c(ba) c(ab)
 * 注意点:1.去掉重复值hashset
 *      2.字典排序 collections.sort()
 *      3.交换后，还得交换回来

 */
public class Permutation {

	public static ArrayList<String> permutation(String str) {
		ArrayList<String> result = new ArrayList<>();
		// 用于去掉重复值
		HashSet<String> re = new HashSet<>();
		if (str == null || str.length() <= 0) {
			return result;
		}
		char[] chs = str.toCharArray();
		permutation(chs, 0, re);
		result.addAll(re);
		// 按字典序打印出该字符串中字符的所有排列。
		Collections.sort(result);
		
		// 如果是降序
//		Collections.sort(result, new Comparator<String>() {
//			public int compare(String string1, String string2) {
//				return string2.compareTo(string1);
//			}
//		});
		return result;
	}

	public static void permutation(char[] chs, int start, HashSet<String> re) {
		// 这是递归的终止条件，就是i下标已经移到char数组的末尾的时候，考虑添加这一组字符串进入结果集中
		if (start == chs.length - 1) {
			re.add(String.valueOf(chs));
		} else {
			for (int i = start; i < chs.length; i++) {
				swap(chs, start, i);
				permutation(chs, start + 1, re);
				swap(chs, start, i);
			}
		}

	}

	public static void swap(char[] chs, int i, int j) {
		char temp = chs[i];
		chs[i] = chs[j];
		chs[j] = temp;

	}

	public static void main(String[] args) {
		ArrayList<String> result = permutation("abc");
		for (String string : result) {
			System.out.println(string);
		}
	}
}
