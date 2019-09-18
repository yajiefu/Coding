package other;

import java.util.Arrays;
import java.util.Scanner;
/*
 * 将数字转换成汉字
 */
public class ConvertNumber {
	/** 定义数组存放数字对应的大写 */
	private final static String[] STR_NUMBER = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };

	/** 定义数组存放整数位数的大写 */
	private final static String[] STR_MODIFY = { "", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
	/** 定义数组存放小数位数的大写 */
	private final static String[] STRDot_MODIFY = { "角", "分" };

	/**
	 * 转化整数部分
	 */
	private static String getInteger(String string) {
		/** 用来保存整数部分数字串 */
		String strInteger = null;//
		/** 记录"."所在位置 */

		int intDotPosFlag = string.indexOf(".");
		int intDotPos = intDotPosFlag;
		int intFlagPos = string.indexOf("-");
		if (intDotPosFlag == -1) {
			intDotPos = string.length();
		}

		/** 取出整数部分 */
		strInteger = string.substring(intFlagPos + 1, intDotPos);
		strInteger = new StringBuffer(strInteger).reverse().toString();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strInteger.length(); i++) {
			sb.append(STR_MODIFY[i]);
			sb.append(STR_NUMBER[strInteger.charAt(i) - 48]);
		}

		sb = sb.reverse();
		replace(sb, "零十", "零");
		replace(sb, "零百", "零");
		replace(sb, "零千", "零");
		replace(sb, "零万", "万");
		replace(sb, "零亿", "亿");
		replace(sb, "零零", "零");
		replace(sb, "零零零", "零");
		replace(sb, "零零零零万", "");
		replace(sb, "零零零零", "");
		if (sb.charAt(sb.length() - 1) == '零' && sb.length() != 1)
			sb.deleteCharAt(sb.length() - 1);
		return intDotPosFlag == -1 ? sb.toString() + "元整" : sb.toString() + "元";
	}

	/**
	 * 转化小数部分
	 */
	private static String getFraction(String string) {
		String strFraction = null;
		int intDotPos = string.indexOf(".");
		if (intDotPos == -1)
			return "";
		strFraction = string.substring(intDotPos + 1);
		StringBuffer sb = new StringBuffer(strFraction.length());
		for (int i = 0; i < strFraction.length(); i++) {
			sb.append(STR_NUMBER[strFraction.charAt(i) - 48] + STRDot_MODIFY[i]);
		}
		replace(sb, "零角零分", "整");
		replace(sb, "零角", "");
		replace(sb, "零分", "");
		return sb.toString();
	}

	private static String getFlag(String string) {
		return string.indexOf("-") != -1 ? "负" : "";
	}

	public static String numberToChinese(String string) {
		StringBuffer sbResult = new StringBuffer(getFlag(string) + getInteger(string) + getFraction(string));
		return sbResult.toString();
	}

	private static void replace(StringBuffer value, String source, String dest) {
		if (value == null || source == null || dest == null)
			return;
		int intPos = 0;
		do {
			intPos = value.toString().indexOf(source);
			if (intPos == -1)
				break;
			value.delete(intPos, intPos + source.length());
			value.insert(intPos, dest);
		} while (true);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String string = in.nextLine();
		String[] string2 = string.substring(1, string.length() - 1).split(",");
		String[] numbers = new String[string2.length];
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < string2.length; i++) {
			numbers[i] = "\"" + numberToChinese(string2[i].substring(1, string2[i].length() - 1)) + "\"";
		}
		System.out.println(Arrays.toString(numbers));

	}
}
