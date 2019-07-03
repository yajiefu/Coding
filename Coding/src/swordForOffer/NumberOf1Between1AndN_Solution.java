package swordForOffer;

/**
 * 题目：整数中1出现的次数（从1到n整数中1出现的次数）
 * 描述：求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
 *      为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,
 *      但是对于后面问题他就没辙了。
 *      ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 * 
 * 方法1：暴力解决。对每个数字做除法和求余。
 *      如果输入的数字n,有O(logn)位，时间复杂度是O(nlogn)。计算效率不高
 * 方法2：找规律
 * @author yajie
 *
 */
public class NumberOf1Between1AndN_Solution {
	// 方法1：暴力解决
	public static int numberOf1Between1AndN(int n) {
		int count = 0;
		for (int i = 0; i <= n; i++) {
			int temp = i;
			while (temp > 0) {
				if (temp % 10 == 1) {
					count++;
				}
				temp /= 10;
			}
		}
		return count;
	}
	
	// 方法2：找规律
	public static int numberOf1Between1AndN2(int n) {
		int count = 0;
		for(long m = 1; m <= n; m *= 10) {
			long a = n / m; 
			long b = n % m;
			if (a % 10 == 0) {
				count += a / 10 * m;
			}else if (a % 10 == 1) {
				count += (a / 10 * m) + (b + 1);
			}else {
				count += (a / 10 + 1) * m;
			}
		}
		return count;
		
		
	}
	
	public static void main(String[] args) {
		System.out.println(numberOf1Between1AndN(21356));
		System.out.println(numberOf1Between1AndN2(21356));
	}
}
