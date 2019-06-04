package swordForOffer;
/*
 * 题目：机器人的运动范围
 * 描述：地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。
 *  例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 */

public class MovingCount {
	public static int movingCount(int threshold, int rows, int cols) {
		if (threshold < 0 || rows < 0 || cols < 0) {
			return 0;
		}
		boolean[] flag = new boolean[rows * cols];
		int count = movingCountCore(threshold, 0, 0, rows, cols, flag);
		return count;

	}

	public static int movingCountCore(int threshold, int i, int j, int rows, int cols, boolean[] flag) {
		int count = 0;
		// 如果机器人能进入该坐标
		if (check(threshold, i, j, rows, cols, flag)) {
			flag[i * rows + j] = true;
			count = 1 + movingCountCore(threshold, i + 1, j, rows, cols, flag)
					+ movingCountCore(threshold, i - 1, j, rows, cols, flag)
					+ movingCountCore(threshold, i, j + 1, rows, cols, flag)
					+ movingCountCore(threshold, i, j - 1, rows, cols, flag);
		}
		return count;
	}

	// 判断机器人能否进入坐标(i,j)，能就返回true
	public static boolean check(int threshold, int i, int j, int rows, int cols, boolean[] flag) {
		if (i >= 0 && j >= 0 && i < rows && j < cols && getDigitSum(i) + getDigitSum(j) <= threshold
				&& !flag[i * rows + j]) {
			return true;
		}
		return false;
	}

	// 坐标数位之和
	public static int getDigitSum(int num) {
		int sum = 0;
		while (num > 0) {
			sum += num % 10;
			num /= 10;
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(movingCount(6, 12, 12));
	}
}
