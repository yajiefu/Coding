package leetCode;
/**
 * 题目：14.最长公共前缀
 * 描述：编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
 * 示例 1:输入: ["flower","flow","flight"]
 *            输出: "fl"
 * 示例 2: 输入: ["dog","racecar","car"]
 *             输出: ""
 *             解释: 输入不存在公共前缀。
 * 说明:所有输入只包含小写字母 a-z 。
 * 
 * 思路1：首先，我们将描述一种查找一组字符串的最长公共前缀 LCP(S1 …Sn)的简单方法。 
 *            我们将会用到这样的结论：LCP(S1……Sn) = LCP(LCP(LCP(S1, S2),S3),……Sn)
 *               方法1：自己初次写的版本，利用字符一个一个对比，效率不高
 *               方法2：利用字符串的函数：indexOf(),substring()
 *                   时间复杂度：O(s),s表示所有字符串中字符数量的总和
 *                   空间复杂度：O(1)
 *                   
 *思路2：想象数组的末尾有一个非常短的字符串，使用上述方法依旧会进行 S​次比较。
 *              优化：我们从前往后枚举字符串的每一列，先比较每个字符串相同列上的字符（即不同字符串相同下标的字符）然后再进行对下一列的比较。
 *              
 *              时间复杂度：O(S)，S是所有字符串中字符数量的总和
 *                           最好情况是 n*minLen次比较
 *              空间复杂度：O(1)
 *              
 * 思路3：与思路1相似，但是采用分治
 *     LCP(S1……Sn) = LCP(LCP(S1……Sk),LCP(Sk+1……Sn))
 *     
 *     最坏情况下，我们有 n个长度为 m的相同字符串。
 *                时间复杂度：O(S),S是所有字符串中字符数量的总和，S=m*n。
 *                             时间复杂度的递推式为 T(n)=2*T(n/2)+O(m)化简后可知其就是 O(S)。
 *                              最好情况下，算法会进行 minLen*n次比较，其中 minLen是数组中最短字符串的长度。
 *    空间复杂度：O(m*log(n))
 *         内存开支主要是递归过程中使用的栈空间所消耗的。 一共会进行 log(n)次递归，每次需要 m的空间存储返回结果
 *         
 *         
 *         
 *         
 * @author yajie
 *
 */
public class LongestCommonPrefix {
	//思路3
	public static String longestCommonPrefix3(String[] strs) {
		if (strs == null || strs.length <= 0) {
			return "";
		}
		return longestCommonPrefix(strs, 0, strs.length-1);

	}

	public static String longestCommonPrefix(String[] strs, int left, int right) {
		if (left == right) {
			return strs[left];
		}else {
			int mid = (left + right) / 2;
			String leftString = longestCommonPrefix(strs, left, mid);
			String rightString = longestCommonPrefix(strs, mid+1,right);
			return commonPrefix(leftString,rightString);
		}
	}
	
	public static String commonPrefix(String left, String right) {
		int minLen = Math.min(left.length(), right.length());
		for(int i = 0; i < minLen; i++) {
			if (left.charAt(i) != right.charAt(i)) {
				return left.substring(0, i);
			}
		}
		return left.substring(0,minLen);
	}
	/************************************************************************/
	//思路2
	public static String longestCommonPrefix2(String[] strs) {
		if (strs == null || strs.length <= 0) {
			return "";
		}
		int n = strs.length;
		//以strs[0]为基准
		for(int i = 0; i < strs[0].length(); i++) {
			char c = strs[0].charAt(i);
			for(int j = 1; j < strs.length; j++) {
				//结束标志
				if (i == strs[j].length() || strs[j].charAt(i) != c) {
					return strs[0].substring(0, i);
				}
			}
		}
		return strs[0];
		
	}
	
	/************************************************************************/
	//思路1：方法2
	public static String longestCommonPrefix1(String[] strs) {
		if (strs == null || strs.length <= 0) {
			return "";
		}
		String prefix = strs[0];
		for(int i = 1; i < strs.length; i++) {
			while(strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length()-1);
				if (prefix.isEmpty()) {
					return "";
				}
			}
		}
		return prefix;
	}
	
	//思路1：方法1
	public static String longestCommonPrefix0(String[] strs) {
		if (strs == null || strs.length <= 0) {
			return "";
		}
		if (strs.length == 1) {
			return strs[0];
		}
		int n = strs.length;
		String string = strs[0];
		for(int i = 1; i < n; i++) {
			string = commonPrefix1(strs[i], string);
			if (string == "") {
				break;
			}
		}
		return string;
	}
	
	
	public static String commonPrefix1(String string1, String string2) {
		String prefix = "";
		char[] chs1 = string1.toCharArray();
		char[] chs2 = string2.toCharArray();
		int i = 0;
		int j = 0;
		while(i < chs1.length && j < chs2.length) {
			if (chs1[i] == chs2[j]) {
				prefix += chs1[i];
				i++;
				j++;
			}else {
				break;
			}
		}
		return prefix;
	}
	
	public static void main(String[] args) {
		String[] strings = {"flower","flow","flee"};
		System.out.println(longestCommonPrefix3(strings));
		
//		String string = "2abab";
//		String string2 = "ab";
//		System.out.println(string.indexOf(string2));
	}
}
