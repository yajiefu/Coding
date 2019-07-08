package leetCode;
/**
 * 442.数组中重复的数据
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 * 找到所有出现两次的元素。
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 * 
 * 题目如果是0 ≤ a[i] ≤ n-1 （n为数组长度），就比较好做，见下面。
 * 
 */
import java.util.ArrayList;
import java.util.List;

public class FindDuplicates {
	public static List<Integer> findDuplicates(int[] nums) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			while (nums[i] != -1 && i != nums[i] - 1) {
				//在交换之前看看是否相等，如相等就是出现了两次
				//由于会出现重复项，所以将每一个出现的数字对应的位置置为负数，下次遇到一个数字判断对应位置是否为负数即可
				if (nums[i] == nums[nums[i] - 1]) {
					list.add(nums[i]);
					System.out.println(nums[i]);
					nums[i] = -1;
					
					break;
				}
				swap(nums, i, nums[i] - 1);
			}
			
		}
		return list;
	}
	
	//如果0 ≤ a[i] ≤ n-1 （n为数组长度）
	public static List<Integer> findDuplicatesI(int[] nums) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			while (i != nums[i]) {
				//在交换之前看看是否相等，如相等就是出现了两次
				if (nums[i] == nums[nums[i]]) {
					list.add(nums[i+1]);
					System.out.println(nums[i]);
					break;
				}
				swap(nums, i, nums[i]);
			}
		}
		return list;
	}

	private static void swap(int[] nums, int i, int j) {
		//不使用中间变量交换位置
		nums[i] = nums[i] + nums[j];
		nums[j] = nums[i] - nums[j];
		nums[i] = nums[i] - nums[j];
//		int temp = nums[i];
//		nums[i] = nums[j];
//		nums[j] = temp;
	}
	
	public static void main(String[] args) {
		int[] nums = {1,1,3,9,9,2,4,6,7,6,7,10,10};
		findDuplicates(nums);
	}
}
