package swordForOffer;

/**
 * 题目：把字符串转换成整数 
 * 描述：将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，
 *    但是string不符合数字要求时返回0)，要求不能使用字符串转换整数的库函数。 
 *    数值为0或者字符串不是一个合法的数值则返回0。
 * 
 * 注意点： 
 * 1.空字符串，返回0？
 * 2.有+ - 号且在第一字符，后面不能有非法字符且不能只有+- 
 * 3.边界条件 可以先用正则表达式判断是否是合法输入
 *
 */
public class StrToInt {
	public static int StrToInt(String str) {
		/*
		 * 注意点： 1.空字符串，返回0？ 2.有+ - 号且在第一字符，后面不能有非法字符且不能只有+- 3.边界条件
		 */
		// 可以先用正则表达式判断是否是合法输入
		boolean flag = false;

//	    	int symbol = 0;// symbo1=0 为正数，symbol=1为负数
		int symbol = 1;
		int start = 0; // 数字开始的坐标
		int result = 0;
		int sum = 0; // 用于判断溢出
		if (str.trim().matches("[+-]?[0-9]+")) {
			char[] chs = str.toCharArray();
			if (chs[0] == '-') {
//					symbol = 0;
				symbol = -1;
				start = 1;
			} else if (chs[0] == '+') {
//					symbol = 0;
				start = 1;
			}

			for (int i = start; i < chs.length; i++) {
				// 判断溢出
				sum = result * 10 + (chs[i] - '0');
				if ((sum - (chs[i] - '0')) / 10 != result) {
					flag = true;
					return 0;
				}
				result = result * 10 + (chs[i] - '0');
//	    			if (result > Integer.MAX_VALUE) {
//	    				flag = true;
//						return 0;
//					}else if (result < Integer.MIN_VALUE) {
//						return 0;
//					}
			}
		} else {
			flag = true;
			return 0;
		}

		// Math.pow(x,y)的作用就是计算x的y次方
		// 注意：java中-1的n次方不能用：(-1)^n .'^'是异或运算

		// 方法1：Math.pow(-1, symbol)
		// 方法2：负数 -1 * result
//	    	result = (int) (Math.pow(-1, symbol) * result);

		result = symbol * result;
		return result;

		// 并没有处理溢出的问题

	}

	public static void main(String[] args) {
		System.out.println(StrToInt("123"));
		System.out.println(StrToInt("+123"));
		System.out.println(StrToInt("-123"));
		System.out.println(StrToInt("a123"));
		System.out.println(StrToInt("12a3"));
	}
}
