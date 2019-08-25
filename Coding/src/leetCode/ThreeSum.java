package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 题目：15.三数之和
 * 描述：给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * 
 * 难点：怎么去重
 */
public class ThreeSum {
	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> listAll = new ArrayList<>();
		if (nums == null || nums.length <= 2) {
			return listAll;
		}

		Arrays.sort(nums);// 从小到大排序
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			if (nums[i] > 0) {
				break;// 如果当前数字大于0，则三数之和一定大于0，所以结束循环
			}

			if (i > 0 && nums[i - 1] == nums[i]) {// 去重
				continue;// 不再执行循环体中continue语句之后的代码，直接进行下一次循环
			}
			int low = i + 1;
			int high = len - 1;
			while (low < high) {
				int sum = nums[i] + nums[low] + nums[high];
				if (sum == 0) {
//					List<Integer> list = new ArrayList<>();
//					list.add(nums[i]);
//					list.add(nums[low]);
//					list.add(nums[high]);
//					listAll.add(list);
					listAll.add(Arrays.asList(nums[i], nums[low], nums[high]));
					// 去重
					while (low < high && nums[low] == nums[low + 1]) {
						low++;
					}
					while (low < high && nums[high] == nums[high - 1]) {
						high--;
					}
					low++;
					high--;

				} else if (sum > 0) {
					high--;
				} else {
					low++;
				}
			}
		}
		return listAll;
	}

	public static void main(String[] args) {
		int[] nums = { -1, 0, 1, 2, -1, -4 };
		List<List<Integer>> listAll = threeSum(nums);
		for (List<Integer> list : listAll) {
			System.out.println(Arrays.toString(list.toArray()));
		}
	}
}
