package swordForOffer;

public class FindNumFromTwodimensionalArray {
/**
 * ��Ŀ����ά�����еĲ���
 * ��������һ����ά�����У�ÿ��һά����ĳ�����ͬ����ÿһ�ж����մ����ҵ�����˳������
 *     ÿһ�ж����մ��ϵ��µ�����˳������
 *     �����һ������������������һ����ά�����һ���������ж��������Ƿ��и�����
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
