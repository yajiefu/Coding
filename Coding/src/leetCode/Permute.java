package leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 题目：46.全排列
 * 描述：给定一个没有重复数字的序列，返回其所有可能的全排列。
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * 
 * 方法：“回溯搜索”算法即“深度优先遍历 + 状态重置 + 剪枝”（这道题没有剪枝）
 * 可以通过这个例子理解“回溯”算法的“状态重置”的操作，“回溯搜索” = “深度优先遍历 + 状态重置 + 剪枝”。
 * 1、“深度优先遍历” 就是“不撞南墙不回头”；
 * 2、回头的时候要“状态重置”，即回到上一次来到的那个地方，“状态”要和上一次来的时候一样。
 * 3、在代码上，往往是在执行下一层递归的前后，代码的形式是“对称的”。
 * 
 * 另外：可以用hashset或者代替used数组，可以看下面的链接
 * 链接：https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
 * 
 * 剑指offer的一个解法：递归，把字符串分为两个部分，  a(bc),a与后面的依次交换位置，接下来再求括号里的排列
 * 感觉不如这个清楚
 * 
 * 如果数组里有重复的数字呢？看PermuteII
 * @author yajie
 *
 */
public class Permute {
	public static List<List<Integer>> permute(int[] nums) {
		int len = nums.length;

		List<List<Integer>> res = new ArrayList<>();
		boolean[] used = new boolean[len];
		if (len == 0) {
			return res;
		}
		generatePermution(nums, used, len, 0, new Stack<>(), res);
		return res;

	}

	private static void generatePermution(int[] nums, boolean[] used, int len, int curSize, Stack<Integer> path,
			List<List<Integer>> res) {
		if (curSize == len) {
			res.add(new ArrayList<>(path));
			return;
		}

		for (int i = 0; i < len; i++) {
			if (!used[i]) {// 没有用过
				path.push(nums[i]);
				used[i] = true;
				generatePermution(nums, used, len, curSize + 1, path, res);
				// 回溯
				path.pop();
				used[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] nums = {1,2,3};
		List<List<Integer>> res = permute(nums);
		for (List<Integer> list : res) {
			System.out.println(list);
		}
		
 	}

}
