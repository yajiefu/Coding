package leetCode;
/**
 * 题目：69.x的平方根
 * 描述：实现 int sqrt(int x) 函数。计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 示例 1:
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,  由于返回类型是整数，小数部分将被舍去。
 * 
 * 
 * 方法：二分法
 * 时间复杂度：O(logN)，二分法的时间复杂度是对数级别的。
 * 空间复杂度：O(1)，使用了常数个数的辅助空间用于存储和比较。
 * @author yajie
 *
 */
public class MySqrt {
	public static int mySqrt(int x) {
		if (x == 0) {
			return 0;
		}
		// 注意：针对特殊测试用例，例如 2147395599
        // 要把搜索的范围设置成长整型
		long left = 0;
		long right = x / 2 + 1;// 一个数的平方根最多不会超过它的一半
		while (left < right) {
			// 注意：这里一定取右中位数，如果取左中位数，代码会进入死循环
			long mid = (left + right + 1) >>> 1;// 如果中点 mid 声明为 int 类型，针对大整型测试用例通不过，因此变量需要声明为 long 类型，下同。
			long square = mid * mid;
			if (square > x) {
				right = mid - 1;
			} else {
				left = mid;
			}
		}
		// 因为一定存在，因此无需后处理
		return (int) left;
	}

	public static void main(String[] args) {
		System.out.println(mySqrt(2147395599));
	}
}
