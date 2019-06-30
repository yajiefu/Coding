package swordForOffer;

/*
 * 题目：左旋转字符串
 * 描述：汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
 *     对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
 *     例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
 *     是不是很简单？OK，搞定它！
 */
public class LeftRotateString {
	// 方法1：YX = (XTYT)T
	public static String leftRotateString(String str, int n) {
		if (str == null || n < 0||str.length() <= 1) {
			return str;
		}
		int len = str.length();
		int k = n % len;
		char[] chs = str.toCharArray();
		
		reverseString(chs, 0, k - 1);
		reverseString(chs, k, len - 1);
		reverseString(chs, 0, len - 1);
		return new String(chs);

	}

	public static char[] reverseString(char[] chs, int start, int end) {
		char temp;
		while (start < end) {
			temp = chs[start];
			chs[start++] = chs[end];
			chs[end--] = temp;
		}
		return chs;
	}
	
	
	//方法2
	public static String leftRotateString2(String str, int n) {
		if (str == null || n < 0 || str.length() <= 1) {
			return str;
		}
		int len = str.length();
		int k = n % len;
		StringBuilder sb = new StringBuilder(str);
		sb.append(sb.substring(0, k));
		return sb.substring(k, sb.length());
		
		
	}

	public static void main(String[] args) {
		System.out.println(leftRotateString("abcXYZAdef", 4));
		System.out.println(leftRotateString2("abcXYZAdef", 4));
	}
}
