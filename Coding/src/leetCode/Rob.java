package leetCode;


/*
 * 题目：198.打家劫舍
 * 描述：你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 *            如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *            给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *            
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。偷窃到的最高金额 = 1 + 3 = 4 。
 * 
 * 示例 2:
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * 
 * 思路：典型的动态规划
 */
public class Rob {
	//写法1：动态规划自己写的，但是自己也很容易被绕进去。时间复杂度：O(n),空间复杂度O:(1)
	//执行用时 :1 ms, 在所有 Java 提交中击败了95.83%的用户。内存消耗 :34.2 MB, 在所有 Java 提交中击败了86.86%的用户
	public static int rob0(int[] nums) {
		if (nums == null || nums.length <= 0) {
			return 0;
		}
		boolean flag = false;//表示i-1是否被偷盗。
		int num1 = 0;//表示到i-2房屋的最大值
		int num2 = 0;//表示到i-1房屋的最大值
		
		num1 = 0;
		num2 = nums[0];
		flag = true;
		for (int i = 1; i < nums.length; i++) {
			int temp;
			if (flag) {//如果第i-1房屋被偷盗
				temp = num1 + nums[i];
			} else {//如果第i-1没有被偷盗
				temp = num2 + nums[i];
			}
			//temp的值是加上了第i个房屋的金额，如果比num2大，那么我们就偷盗该房间。
			if (temp > num2) {
				flag = true;
				num1 = num2;
				num2 = temp;
			}else {//如果没有num2大，我们就不偷。flag置为false
				flag = false;
			}
		}
		return num2;
	}
	
	//写法2：动态规划：dp[i] = max{dp[i-1],dp[i-2]+nums[i]}
	//注意：在方法1中之所以加了flag判断是不知道前一个房屋有没有被偷盗。但是实际上我们可以不用担心。如果前一个房间没有被偷盗，那么它与前前个房屋的值是一样的。
	//因此用dp[i-2] + nums[i]与 判断flag==false后dp[i-1]+nums[i]是一样的。
	//时间复杂度：O(n),空间复杂度O(n)
	//执行用时 :1 ms, 在所有 Java 提交中击败了95.83%的用户内存消耗 :34.2 MB, 在所有 Java 提交中击败了88.10%的用户
	public static int rob1(int[] nums) {
		if (nums == null || nums.length <= 0) {
			return 0;
		}
		
		int len = nums.length;
		int[] dp = new int[len+1];
		dp[0] = 0;
		dp[1] = nums[0];
		for(int i = 2; i <= len; i++) {
			dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
		}
		return dp[len];
		
	}
	
	//写法3：所以对方法1进行简化，也就是对方法2的优化。空间复杂度为O(1)即可
	//执行用时 :1 ms, 在所有 Java 提交中击败了95.83%的用户内存消耗 :35.1 MB, 在所有 Java 提交中击败了78.72%的用户
	public static int rob(int[] nums) {
		if (nums == null || nums.length <= 0) {
			return 0;
		}
		int num1 = 0;
		int num2 = nums[0];
		
		for(int i = 1; i < nums.length; i++) {
			int temp = Math.max(num1 + nums[i], num2);
			num1 = num2;
			num2 = temp;
		}
		return num2;
	}
	public static void main(String[] args) {
		int[] nums = {1,2,3,1,6,9,9,3,8};
		System.out.println(rob1(nums));
	}
}
