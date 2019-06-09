package swordForOffer;

/*
 * 题目：变态跳台阶
 * 描述：一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * 
 * 
 * 结论：           | 1       ,(n=0 ) 
 * f(n) =  | 1       ,(n=1 )
 *         | 2*f(n-1),(n>=2)
 */
public class JumpFloorII {
	public static int jumpFloorII(int target) {

		if (target <= 0) {
			return 0;
		}
		if (target == 1) {
			return 1;
		}
		int result = 1;
		for(int i = 2; i <= target; i++) {
			result *= 2;
		}
		return result; 
	}
	public static void main(String[] args) {
		System.out.println(jumpFloorII(5));
	}
}
