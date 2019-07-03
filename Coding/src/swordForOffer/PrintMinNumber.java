package swordForOffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 题目：把数组排成最小的数 
 * 描述：输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *     例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 * 
 * 思路： 根据排列组合，n个数字有n!个排列。
 * 1.两个整数拼接起来可能会超出int的表达范围，因此可以用字符串来表示数字 
 * 2.这题是希望我们可以找个一个排序规则，数组根据这个规则排序之后能排成一个最小的数字。
 *   如何排序：比较两个数字，也就是判断这两个数字m和n哪个在前面，而不是比较这两个数字的值更大。
 * 
 * @author yajie
 *
 */
public class PrintMinNumber {
	public static String printMinNumber(int[] numbers) {
		if (numbers == null || numbers.length <= 0) {
			return "";
		}
		int len = numbers.length;
		// 用选择排序:也可以优化选择排序
		int temp;
		for (int end = len - 1; end > 0; end--) {
			for (int i = 0; i < end; i++) {
				//其实这里写的不好，容易溢出。所以我们还是要比较字符串的大小。
				Integer a = Integer.valueOf(numbers[i] +"" + numbers[i+1]);
				Integer b = Integer.valueOf(numbers[i+1]+""+numbers[i]);
				if (a > b) {
					temp = numbers[i];
					numbers[i] = numbers[i+1];
					numbers[i+1]=temp;
				}
			}
		}
		String str = "";
		for(int i = 0; i < len;i++) {
			str += numbers[i];
		}
		return str;
	}

	//对字符串进行排序，用比较器
	public static String printMinNumber2(int[] numbers) {
		if (numbers == null || numbers.length <= 0) {
			return "";
		}
		int len = numbers.length;
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0; i < len;i++) {
			list.add(numbers[i]);
		}
		//排序
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				String str1 = o1 + "" + o2;
				String str2 = o2 + "" + o1;
				return str1.compareTo(str2);
				
			}
		});
		
		String str = "";
		for (Integer integer : list) {
			str += integer;
		}
		return str;
	}
	
	
	public static void main(String[] args) {
		int[] nums = {128,32,1,78,45,1111112,6};
		System.out.println(printMinNumber(nums));
		System.out.println(printMinNumber2(nums));
	}
	
	
}
