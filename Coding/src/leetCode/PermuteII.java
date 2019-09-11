package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 题目：47.全排列II
 * 描述：给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 示例:
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * 
 * 思路1：根据46题的全排序结果，用set去重
 * 思路2：过程当中去重
 * 
 * @author yajie
 *
 */
public class PermuteII {
	public static List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length <= 0) {
			return res;
		}
		int len = nums.length;
		boolean[] used = new boolean[len];
		// 首先排序，之后才有可能发现重复分支
		Arrays.sort(nums);
		findPermuteUnique(nums, used, 0, res, new Stack<>());
		return res;

	}

	public static void findPermuteUnique(int[] nums, boolean[] used, int curSize, List<List<Integer>> res,
			Stack<Integer> path) {
		if (curSize == nums.length) {
			res.add(new ArrayList<>(path));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (!used[i]) {
				// 因为排序以后重复的数一定不会出现在开始，故 i > 0
				// 和之前的数相等，并且之前的数还未使用过，只有出现这种情况，才会出现相同分支
				// 这种情况跳过即可
				// 为什么是这种情况呢？
				// 如果这个数和之前的数一样，并且之前的数还未使用过，那接下来如果走这个分支，就会使用到之前那个和当前一样的数，就会发生重复，此时分支和之前的分支一模一样。
				if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
					continue;
				}
				path.add(nums[i]);
				used[i] = true;
				findPermuteUnique(nums, used, curSize + 1, res, path);
				// 回溯
				path.pop();
				used[i] = false;
			}
		}

	}

	// 方法：根据46题的全排列结果去重，不推荐
	public static List<List<Integer>> permuteUnique1(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length <= 0) {
			return res;
		}
		Set<List<Integer>> set = new HashSet<>();
		int len = nums.length;
		boolean[] used = new boolean[len];
		generatePermution1(nums, used, len, 0, new Stack<>(), set);
		for (List<Integer> list : set) {
			res.add(list);
		}
		return res;

	}

	private static void generatePermution1(int[] nums, boolean[] used, int len, int curSize, Stack<Integer> path,
			Set<List<Integer>> set) {
		if (curSize == len) {
			set.add(new ArrayList<>(path));
			return;
		}
		for (int i = 0; i < len; i++) {
			if (!used[i]) {
				path.add(nums[i]);
				used[i] = true;
				generatePermution1(nums, used, len, curSize + 1, path, set);
				// 回溯
				path.pop();
				used[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = { 1, 1, 3 };
//		List<List<Integer>> res = permuteUnique1(nums);
		List<List<Integer>> res = permuteUnique(nums);
		for (List<Integer> list : res) {
			System.out.println(list);
		}

	}
}
