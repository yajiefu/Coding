package swordForOffer;

/*
 * 题目：矩阵中的路径
 * 描述：请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。
 *  例如 a b c e s f c s a d e e 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，
 * 但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 */
public class HasPath {
	public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
		// 设置标志位，true表示该位置走过
		boolean[] flag = new boolean[matrix.length];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				// 循环遍历二维数组，找到起点等于str第一个元素的值，再递归判断四周是否有符合条件的----回溯法
				if (judge(matrix, i, j, rows, cols, flag, str, 0)) {
					return true;
				}
			}
		}
		return false;

	}

	// judge(初试矩阵，行坐标i，列坐标j，矩阵行数，矩阵列数，标志位，待判断的字符串，字符串索引为0即先判断字符串的第一位)
	public static boolean judge(char[] matrix, int i, int j, int rows, int cols, boolean[] flag, char[] str, int k) {
		// 先根据i和j计算匹配的第一个元素转为一维数组的位置
		int index = i * cols + j;
		// 以下情况，不能成功 递归终止条件
		if (i < 0 || j < 0 || i >= rows || j >= cols || flag[index] == true || matrix[index] != str[k]) {
			return false;
		}
		// 若k已经达到str末尾了，说明之前都已经匹配成功了，直接返回true即可
		if (k == str.length - 1) {
			return true;
		}
		// 要走的第一个位置为true，表示已经走过了
		flag[index] = true;
		// 回溯，递归寻找，每次找到了就给k加一，找不到，还原
		if (judge(matrix, i + 1, j, rows, cols, flag, str, k + 1)
				|| judge(matrix, i - 1, j, rows, cols, flag, str, k + 1)
				|| judge(matrix, i, j + 1, rows, cols, flag, str, k + 1)
				|| judge(matrix, i, j - 1, rows, cols, flag, str, k + 1)) {
			return true;
		}
		// 走到这里，说明这一条路不同，还原，再试其他的路径
		flag[index] = false;
		return false;
	}

	public static void main(String[] args) {
		char[] matrix = { 'a', 'b', 't', 'g', 'c', 'f', 'c', 's', 'j', 'd', 'e', 'h' };
		char[] str = { 'b', 'f', 'c', 'e' };
		int rows = 3, cols = 4;

		System.out.println(hasPath(matrix, rows, cols, str));
	}

}
