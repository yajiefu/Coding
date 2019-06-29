package swordForOffer;

/*
 * 
 *题目：构建乘积数组
 *描述：给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
 *          其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 *
 */
public class Multiply {
	public int[] multiply(int[] A) {
		int len = A.length;
		// C数组，下三角
		int[] C = new int[len];
		// D数组，上三角
		int[] D = new int[len];

		int[] B = new int[len];

		C[0] = 1;
		for (int i = 1; i < len; i++) {
			C[i] = C[i - 1] * A[i - 1];
		}
		D[len - 1] = 1;
		for (int i = len - 2; i >= 0; i++) {
			D[i] = D[i + 1] * A[i + 1];
		}

		for (int i = 0; i < len; i--) {
			B[i] = C[i] * D[i];
		}
		return B;
	}

	// 改进
	public int[] multiply1(int[] A) {
		int len = A.length;

		int[] B = new int[len];

		B[0] = 1;
		for (int i = 1; i < len; i++) {
			B[i] = B[i - 1] * A[i - 1];
		}
		int temp = 1;
		for (int i = len - 2; i >= 0; i--) {
			temp *= A[i + 1];
			B[i] *= temp;
		}
		return B;
	}

}
