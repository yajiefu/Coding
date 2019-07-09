package leetCode;


import java.util.HashMap;

/**
 * 题目：1.两数之和
 * 描述：给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *            你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *            
 *            
 *方法1：暴力解法。 对于每个元素，我们试图通过遍历数组的其余部分来寻找它所对应的目标元素
 *             时间复杂度O(n2)  空间复杂度O(1)
 *             
 *方法2：方法1中对于第二次查找用了O(n)时间，可否O(1)时间呢？哈希表，查找时间为O(1).
 *             时间复杂度：O(n)， 我们把包含有 n个元素的列表遍历两次。由于哈希表将查找时间缩短到 O(1)，所以时间复杂度为 O(n)。
 *             空间复杂度：O(n)， 所需的额外空间取决于哈希表中存储的元素数量，该表中存储了 n个元素。
 *
 *方法3：根据方法2我们可以发现，实际上我们第一次遍历的时候就可以边放元素边进行判断
 *             时间复杂度 O(n),空间复杂度O(n)
 * @author yajie
 *
 */
public class TwoSum {
	// 方法1：暴力解法
	public static int[] twoSum1(int[] nums, int target) {
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] == target - nums[i]) {
					// 返回下标
					return new int[] { i, j };
				}
			}
		}
		throw new IllegalArgumentException("No two sum solution");
	}

	// 方法2：哈希表，对列表遍历两次
	public static int[] twoSum2(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		// 将所有元素放入哈希表中。
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}
		for (int i = 0; i < nums.length; i++) {
			int num = target - nums[i];
			if (map.containsKey(num) && map.get(num) != i) {
				// 返回下标
				return new int[] { i, map.get(num) };
			}
		}
		throw new IllegalArgumentException("No two sum solution");
	}

	// 方法3：
	public static int[] twoSum3(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		// 将所有元素放入哈希表中。边放边找
		for (int i = 0; i < nums.length; i++) {
			int num = target - nums[i];
			if (map.containsKey(num)) {
				// 返回下标
				return new int[] { map.get(num), i };
			}
			map.put(nums[i], i);
		}
		throw new IllegalArgumentException("No two sum solution");
	}

	public static void main(String[] args) {
//		int[] result = twoSum1(new int[] {1,5,2,7,9}, 7);
//		int[] result = twoSum2(new int[] {1,5,2,7,9}, 7);
		int[] result = twoSum3(new int[] { 1, 5, 2, 7, 9 }, 7);
		for (int i : result) {
			System.out.print(i + " ");
		}
	}
}
