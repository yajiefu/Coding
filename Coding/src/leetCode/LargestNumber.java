package leetCode;
/**
 * 题目：179. 最大数
 * 描述：给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 * 示例 1:
 * 输入: [10,2]
 * 输出: 210
 * 
 * 示例 2:
 * 输入: [3,30,34,5,9]
 * 输出: 9534330
 * 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * 
 * 思路： 自定义排序：比较 o1 + o2 和o2 + o1
 * 
 * 也可以用数组的排序Arrays.sort(),以及StringBuilder
 */
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class LargestNumber {
	public static String largestNumber(int[] nums) {
		if (nums == null || nums.length <= 0) {
			return "";
		}
		int len = nums.length;
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < len; i++) {
			list.add(nums[i]);
		}
		// 从大到小排序
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				String string1 = o1 + "" + o2;
				String string2 = o2 + "" + o1;
				return string2.compareTo(string1);
			}
		});

		//注意一个情况，如果为0，我们直接返回"0",否则会出现"00"的结果
		if (list.get(0) == 0) {
			return "0";
		}
		
		StringBuilder sb = new StringBuilder();
		for (Integer integer : list) {
			sb.append(integer);
		}
		return sb.toString();
//		String res = "";
//		for (Integer integer : list) {
//			res += integer;
//		}
//		return res;
	}

	public static void main(String[] args) {
		int[] nums = { 3, 30, 34, 5, 9 };
//		int[] nums = {0,0 };
		System.out.println(largestNumber(nums));
	}
}
