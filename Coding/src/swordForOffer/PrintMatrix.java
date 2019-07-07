package swordForOffer;

import java.util.ArrayList;

/**
 * 题目：顺时针打印矩阵
 * 描述：输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 *           例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 *           
 *   1.先看第一圈，起点的横坐标和纵坐标是一样的（start,start）。终止条件是?
 *      Start*2<rows,start*2<cols
 *   2.	分析一圈打印的情况
 * @author yajie
 *
 */
public class PrintMatrix {
	ArrayList<Integer> result = new ArrayList<>();

	public ArrayList<Integer> printMatrix(int[][] matrix) {
		if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
			return null;
		}
		int rows = matrix.length;
		int cols = matrix[0].length;

		// 每一圈的起始地方都是matrix[start][start]
		int start = 0;
		while (start * 2 < rows && start < cols) {
			printMatrix(matrix, rows, cols, start);
			start++;
			System.out.println();
		}
		return result;
	}

	public ArrayList<Integer> printMatrix(int[][] matrix, int rows, int cols, int start) {
		System.out.println("@@");
		int endX = cols - 1 - start;
		int endY = rows - 1 - start;
		// 左上到右上:总是必须的。matrix[start][start~endY]
		for(int i = start; i <= endX; i++) {
			result.add(matrix[start][i]);
		}

		// 右上到右下：必须要有两行  matrix[start+1 ~ endY][endX],因此endY>=start+1即endX>start
		if(endY > start) {
			for(int i = start + 1; i <= endY; i++) {
				result.add(matrix[i][endX]);
			}
		}

		// 右下到左下:必须要有两行两列 matrix[endY][endX-1 ~ start]，
		//因此  endY>start且endX>start
		if(endX > start && endY > start) {
			for(int i = endX - 1; i >= start; i--) {
				result.add(matrix[endY][i]);
			}
		}

		
		// 左下到左上:至少是三行两列 matrix[endY-1~start+1][start+1]
		//因此endY-1>start 且 endY>start
		if(endY-1 > start && endX>start) {
			for(int i = endY - 1; i > start; i--) {
				result.add(matrix[i][start]);
			}
		}
		return result;
	}

}
