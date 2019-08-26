package swordForOffer;
/*
 * 题目：跳台阶
 * 描述：一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * 方法1：递归：这是一个最低级的实现，时间复杂度度为O(2^n)。如果n很大，数据类型应改为long
 * 方法2：非递归：时间复杂度：O(n);空间复杂度：O(1)
 */
public class JumpFloor {
	public static long jumpFloor(int target) {
		if (target <= 0) {
			return 0;
		}
		if (target == 1) {
			return 1;
		}
		if (target == 2) {
			return 2;
		}
		long num1 = 1;
		long num2 = 2;
		long result = 0;
		for(int i = 3; i <= target; i++) {
			result = num1 + num2;
			num1 = num2;
			num2 = result;
		}
		return result;
    }
	
	public static void main(String[] args) {
		System.out.println(jumpFloor(4));
	}
}
