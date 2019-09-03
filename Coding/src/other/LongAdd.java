package other;

import java.util.Scanner;
/**
 * 2019.8.8趋势
 * 题目1：非负超长数相加
 * @author yajie
 *
 */
public class LongAdd {
	private static int returnIndex(String num) {
		int index = -1;
		for (int i = 0; i < num.length(); i++) {
			if (num.charAt(i) == '.') {
				index = i;
			}
		}
		return index;
	}

	private static String add(String num1, String num2) {
		// 坑1:如果没有小数点
		// 找到.位置
		String string1Z = "";
		String string1X = "";
		int index1 = returnIndex(num1);
		if (index1 == -1) {
			// 找不到小数点
			string1Z = num1;

		} else {
			String[] string1 = num1.split("\\.");
			string1Z = string1[0];
			string1X = string1[1];
		}

		String string2Z = "";
		String string2X = "";
		int index2 = returnIndex(num2);
		if (index2 == -1) {
			string2Z = num2;
		} else {
			String[] string2 = num2.split("\\.");
			string2Z = string2[0];
			string2X = string2[1];
		}

		// 小数相加
		int len1 = string1X.length();
		int len2 = string2X.length();

		int maxLen = Math.max(len1, len2);
		if (len1 < maxLen) {
			// 补齐len1
			StringBuilder sb = new StringBuilder(string1X);
			int dif = maxLen - len1;
			while (dif-- > 0) {
				sb.append('0');
			}
			string1X = sb.toString();
		}

		if (len2 < maxLen) {
			// 补齐len2
			StringBuilder sb = new StringBuilder(string2X);
			int dif = maxLen - len2;
			while (dif-- > 0) {
				sb.append('0');
			}
			string2X = sb.toString();
		}
		string1X = new StringBuilder(string1X).reverse().toString();
		string2X = new StringBuilder(string2X).reverse().toString();
		String reX = addNum(0, string1X, string2X);
		int temp = 0;
		if (reX.length() > maxLen) {
			// 说明第一位是进位
			temp = Integer.valueOf(reX.charAt(0) - '0');
			reX = reX.substring(1);
		}

		// 坑3：取掉小数后面的0
		char[] rx = reX.toCharArray();
		int h = rx.length - 1;
		int cout = rx.length;
		while (h >= 0) {
			if (h == rx.length - 1 && rx[h] == '0') {
				h--;
				cout--;
				while (h >= 0 && rx[h] == '0') {
					cout--;
					h--;
				}
			} else {
				break;
			}
		}
		reX = reX.substring(0, cout);

		// 整数相加
		StringBuilder sb = new StringBuilder();

		string1Z = new StringBuilder(string1Z).reverse().toString();
		string2Z = new StringBuilder(string2Z).reverse().toString();

		String reZ = addNum(temp, string1Z, string2Z);
		// 坑2：去掉整数开头为0
		char[] re = reZ.toCharArray();
		int k = 0;
		int count = 0;
		while (k < re.length) {
			if (k == 0 && re[k] == '0') {
				count++;
				k++;
				while (k < re.length && re[k] == '0') {
					count++;
					k++;
				}
				// 坑：整数部分若只有一个0，就不能去掉
				if (count == re.length) {
					count--;
				}
			} else {
				break;
			}
		}
		reZ = reZ.substring(count);

		String res = reX.equals("") ? reZ : reZ + "." + reX;
		return res;

	}

	// 相加
	private static String addNum(int temp, String str1, String str2) {
		StringBuilder sBuilder = new StringBuilder();
		int len1 = str1.length();
		int len2 = str2.length();
		int minLen = Math.min(len1, len2);
		boolean flag = len1 > len2 ? true : false;
		for (int i = 0; i < minLen; i++) {
			char x = str1.charAt(i);
			char y = str2.charAt(i);

			int result = x - '0' + y - '0' + temp;
			int r = result % 10;
			temp = result / 10;
			sBuilder.append(r);

		}

		if (flag) {// str1长
			while (temp != 0 && minLen < len1) {
				int result = str1.charAt(minLen) - '0' + temp;
				int r = result % 10;
				temp = result / 10;
				sBuilder.append(r);
				minLen++;
			}
			if (minLen < len1) {
				String string = str1.substring(minLen, len1);
				sBuilder.append(string);
			}
			if (temp != 0) {
				sBuilder.append(temp);
			}

		} else {
			while (temp != 0 && minLen < len2) {
				int result = str2.charAt(minLen) - '0' + temp;
				int r = result % 10;
				temp = result / 10;
				sBuilder.append(r);
				minLen++;
			}
			if (minLen < len2) {
				String string = str2.substring(minLen, len2);
				sBuilder.append(string);
			}
			if (temp != 0) {
				sBuilder.append(temp);
			}
		}
		String re = sBuilder.reverse().toString();
		return re;
	}

	// please don't modify any code below.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

//		String num1 = sc.nextLine();
//		String num2 = sc.nextLine();

		String num1 = "1.003";
		String num2 = "29.999999";

		String num3 = "0010.9900";
		String num4 = "29.01";

		String num5 = "000.9900";
		String num6 = "0.001";

		String sum1 = add(num1, num2);
		System.out.println(sum1);

		String sum2 = add(num3, num4);
		System.out.println(sum2);

		String sum3 = add(num5, num6);
		System.out.println(sum3);
//		
	}
}
