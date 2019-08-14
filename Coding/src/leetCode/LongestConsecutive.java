package leetCode;

import java.util.HashSet;

/*
 * 题目：128.最长连续序列
 * 描述：给定一个未排序的整数数组，找出最长连续序列的长度。要求算法的时间复杂度为 O(n)。
 * 
 */
public class LongestConsecutive {
	public static int longestConsecutive(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for(int i = 0; i < nums.length; i++) {
			set.add(nums[i]);
		}
		
		int longest = 0;//最长连续序列的长度
		for (Integer val : set) {
			
			if (!set.contains(val - 1)) {
				int curNum = val;
				int count = 1;
				
				while(set.contains(curNum + 1)) {
					curNum++;
					count++;
				}
				
				longest = Math.max(longest, count);
			}
		}
		return longest;

	}
	
	public static void main(String[] args) {
		int[] arr = {100, 4, 200, 1, 3, 2};
		System.out.println(longestConsecutive(arr));
	}
}
