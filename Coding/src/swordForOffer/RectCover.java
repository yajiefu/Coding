package swordForOffer;

/*
 * 题目：矩形覆盖
 * 描述：我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 * 
 * 斐波纳契数列
 */
public class RectCover {
	public static int rectCover(int target) {
		if (target <= 0) {
			return 0;
		}
		if (target == 1) {
			return 1;
		}

		if (target == 2) {
			return 2;
		}
		int num1 = 1;
		int num2 = 2;
		int result = 0;
		for(int i = 3; i <= target; i++) {
			result = num1 + num2;
			num1 = num2;
			num2 = result;
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(rectCover(8));
	}
}
