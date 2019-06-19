package leetCode;
/*
 * 题目：504.七进制数
 * 描述：给定一个整数，将其转化为7进制，并以字符串形式输出。
 */
public class ConvertToBase7 {

	public static String convertToBase7(int num) {
		if (num == 0) {
			return "0";
		}
		// 注意：如果num是负数
		boolean negative = num < 0;
		if (negative) {
			num = -num;
		}
		StringBuilder sb = new StringBuilder();
		
		int base = 7;
		while(num > 0) {
			sb.append(num % base);
			num = num / base;
		}
		String result =  sb.reverse().toString();
		return negative ? "-" + result : result;
	}
	
	public static void main(String[] args) {
		System.out.println(convertToBase7(-7));
		System.out.println(convertToBase7(7));
		System.out.println(convertToBase7(100));
		
	}
}
