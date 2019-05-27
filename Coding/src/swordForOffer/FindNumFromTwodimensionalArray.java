package swordForOffer;

public class FindNumFromTwodimensionalArray {
/**
 * 题目：二维数组中的查找
 * 描述：在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
 *     每一列都按照从上到下递增的顺序排序。
 *     请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数
 */
	
	public static boolean Find(int target, int [][] array) {
		if (array == null || array.length <= 0 || array[0].length <= 0) {
			return false;
		}
		int rows = array.length;
		int cols = array[0].length;
		
		int row = 0;
		int col = cols - 1;
		while(row < rows && col >= 0) {
			int val = array[row][col];
			if (val == target) {
				return true;
			}
			if (val < target) {
				row++;
			}
			if (val > target) {
				col--;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int[][] array = {{1,2,8,9},{2,4,9,12},{4,7,10,13}};
		int target = 8;
		System.out.println(Find(target, array));
	}
}
