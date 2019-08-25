package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目：18.四数之和
 * 描述：给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 注意：案中不可以包含重复的四元组。
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * 
 * 思路：求三数之和时，用一个循环固定一个数，用双指针找到满足题解的另外两个数
 * 使用两个循环固定两个数，用双指针找到满足题解的另外两个数。
 * @author yajie
 *
 */
public class FourSum {
	public static List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> listAll = new ArrayList<List<Integer>>();
		if (nums == null || nums.length <= 3) {
			return listAll;
		}
		Arrays.sort(nums);
		int len = nums.length;
		//i的指针的范围
		for (int i = 0; i < len - 3; i++) {
			// 固定一个数
			//坑：不能这样写，这题和三数之和为0不一样，target可能小于0
//			if (nums[i] > target) {
//				break;
//			}

			if (i > 0 && nums[i - 1] == nums[i]) {// 去重
				continue;
			}
			//j的指针的范围
			for (int j = i + 1; j < len - 2; j++) {
				// 固定第二个数
				if (j > i + 1 && nums[j - 1] == nums[j]) {// 去重
					continue;
				}
				// 双指针
				int low = j + 1;
				int high = len - 1;
				while (low < high) {
					int sum = nums[i] + nums[j] + nums[low] + nums[high];
					if (sum == target) {
						listAll.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
						// 去重
						while (low < high && nums[low] == nums[low + 1]) {
							low++;
						}
						while (low < high && nums[high] == nums[high - 1]) {
							high--;
						}
						high--;
						low++;
					} else if (sum > target) {
						high--;
					} else {
						low++;
					}
				}
			}
		}
		return listAll;
	}
	public static void main(String[] args) {
		int[] nums = {1,-2,-5,-4,-3,3,3,5};
		int target = -11;
		List<List<Integer>> listAll = fourSum(nums,target);
		for (List<Integer> list : listAll) {
			System.out.println(Arrays.toString(list.toArray()));
		}
	}
}
