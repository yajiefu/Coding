package leetCode;


/**
 * 题目：300.最长上升子序列
 * 描述：给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4 
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(nlogn) 吗?
 * 
 * 
 * 方法1：动态规划
 * 我们使用 dp数组来存储所需的数据。 dp[i]表示考虑到数组元素一直到 i的情况下可能的最长上升子序列的长度，必须包括 第i元素。
 * 为了找出 dp[i]，我们要遍历前面0到i-1的nums[j],找到nums[j]<nums[i]的dp[j]+1的最大值才是最终的dp[i]
 * 最后，确定最终结果是所有 dp[i]中的最大值。
 * 时间复杂度：O(n2)
 * 空间复杂度：O(n)
 * 
 * 
 * 方法2：贪心算法+二分查找
 * 具体解析：https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/
 * 注意：tail数组是一个辅助数组，它的最长长度可以表示最长上升子序列的长度，但是里面存放的元素却不是
 *时间复杂度：O(nlogn)，遍历数组O(n),二分查找O(logn)
 *空间复杂度：O(n)，tail数组
 * 
 * @author yajie
 *
 */
public class LengthOfLIS {
	// 方法1：动态规划
	public static int lengthOfLIS1(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int len = nums.length;
		int[] dp = new int[len];
		dp[0] = 1;
		int maxLen = 1;
		for (int i = 1; i < len; i++) {
			// 在0到i-1之间找到比nums[i]小的nums[j]
			int maxValue = 0;
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) {
					maxValue = Math.max(maxValue, dp[j]);
				}
			}
			dp[i] = maxValue + 1;
			maxLen = Math.max(dp[i], maxLen);
		}
		return maxLen;

	}

	// 方法2：贪心算法+二分查找
	public static int lengthOfLIS(int[] nums) {
		// 特殊处理。
		if (nums == null) {
			return 0;
		}
		int len = nums.length;
		if (len <= 1) {
			return len;
		}
		// tail 数组的定义：长度为 i + 1 的上升子序列的末尾最小是几
		int[] tail = new int[len];
		// 遍历第 1 个数，直接放在有序数组 tail 的开头
		tail[0] = nums[0];
		// end 表示有序数组 tail 的最后一个已经赋值元素的索引
		int end = 0;// tail的尾指针
		for (int i = 1; i < len; i++) {
			int cur = nums[i];
			// 【逻辑 1】比 tail 数组实际有效的末尾的那个元素还大
			if (cur > tail[end]) {
				// 直接添加在那个元素的后面，所以 end 先加 1
				tail[++end] = cur;
			} else {
				// 使用二分查找法，在有序数组 tail 中
				// 找到第 1 个大于等于 nums[i] 的元素，尝试让那个元素更小
				int left = 0;
				int right = end;
				while (left < right) {
					int mid = (left + right) >>> 1;
					if (tail[mid] < cur) {
						left = mid + 1;
					} else {
						right = mid;
					}
				}
				// 此时left就是要替换的位置
				// 走到这里是因为 【逻辑 1】 的反面，因此一定能找到第 1 个大于等于 nums[i] 的元素
				// 因此，无需再单独判断
				tail[left] = cur;
			}
		}
		// 此时 end 是有序数组 tail 最后一个元素的索引
		// 题目要求返回的是长度，因此 +1 后返回
		return end + 1;

	}

	public static void main(String[] args) {
		int[] nums = { 10,9,2,5,3,4 };
		System.out.println(lengthOfLIS1(nums));
		System.out.println(lengthOfLIS(nums));

	}

}
