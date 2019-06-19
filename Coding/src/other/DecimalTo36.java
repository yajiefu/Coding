package other;

import java.util.HashMap;

/*
 * 题目：十进制和36进制之间的转换
 */
public class DecimalTo36 {
	// 将十进制数转换成36进制
	public static String decimalTo36(int num) {
		if (num == 0) {
			return "0";
		}
		boolean negative = num < 0;
		if (negative) {
			num = -num;
		}
		int base = 36;
		String X36 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] x36 = X36.toCharArray();
		String result = "";
		
		while(num > 0) {
			result = x36[num % base] + result;
			num = num / base;
		}
		return negative ? "-" + result : result;
		
	}
	
	// 将36进制数转换成十进制
	private static HashMap<Character, Integer> creatMapTirtySixToTen() {
		String X36 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0; i < X36.length(); i++) {
			map.put(X36.charAt(i), i);
		}
		return map;
	}
	public static int ThirtySixToDecimal(String string) {
		if (string == "") {
			return 0;
		}
		// 十进制结果，初始值为0
		int decimal = 0;
		// 记录次方,初始值为36进制长度-1
		int power = string.length() - 1;
		int base = 36;
		char[] key = string.toCharArray();
		// 利用hashmap键值对拿到36进制转换10进制的值键对
		HashMap<Character, Integer> map = creatMapTirtySixToTen();
		for(int i = 0; i < string.length(); i++) {
			decimal = (int) (map.get(key[i]) * Math.pow(base, power) + decimal);
			power--;
		}
		return decimal;
		
	}
	
	public static void main(String[] args) {
		System.out.println(decimalTo36(10));
		System.out.println(decimalTo36(-47));
		
		System.out.println(ThirtySixToDecimal("10"));
		System.out.println(ThirtySixToDecimal("1A"));
	}

}
