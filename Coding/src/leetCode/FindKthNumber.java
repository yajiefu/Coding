package leetCode;
/**
 * 题目：668.乘法中第k小的数
 * 描述：几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第k小的数字吗？
 * 给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。
 * 例 1：
 * 输入: m = 3, n = 3, k = 5
 * 输出: 3
 * 解释: 
 * 乘法表:
 * 1	2	3
 * 2	4	6
 * 3	6	9
 * 第5小的数字是 3 (1, 2, 2, 3, 3).
 * 
 * 思路： 这道题可能有人会想着先构造出这个乘法表，用大顶堆，然后再去搜索，但这样是行不通的，
 *      因为m、n的取值可能非常大，非常耗内存。首先我们知道在m、n的乘法表中取值范围为[1, m * n]，
 *      那么我们可不可以使用使用二分搜索呢？
 *      首先观察乘法表我们会发现，由于构造关系，决定了他每一行都是递增的。
 *      如果我们需要在第i行中寻找不大于num的个数，我们只要min(num / i, n)，
 *      其中（i是这一行的行号，n是矩阵的列数）num / i代表的是如果num也在第i行，
 *      它存在的列数，所以只要取最小值就是第i行不大于num的个数。
 *      （比如例题1中，我们需要知道第2行，不大于4的个数，min(4 / 2, 3) == 2个（就是2， 4））
 *      那么如果我们需要确定这个乘法表中不大于num的个数就非常简单了，我们只要将每一行
 *      不大于num的个数累加即可。（比如例题1中，我们需要知道乘法表中不大于4的个数，
 *      第一行3个、第二行2个，第三行1个）
 *      现在我们就可以使用二分搜索了，初始化left = 1， right = n * m + 1，
 *      mid = （left + right） / 2，在m，n的乘法表中寻找不超过mid的个数。
 *      
 *      
 * 2019.9.1:拼多多笔试编程题，求第k大的数
 */

public class FindKthNumber {
	public static int findKthNumber(int m, int n, int k) {
		// 二分法模板
		int left = 1;
		int right = m * n;
		while (left < right) {
			int mid = (left + right) >>> 1;// 不能排除右边界，所以取左中位数
			int count = 0;
			for (int i = 1; i <= m; i++) {
				count += (mid / i > n ? n : mid / i);
			}
			if (count >= k) {
				right = mid;// 不能排除右边界
			} else {
				left = mid + 1;
			}

		}
		return left;
	}

	//拼多多笔试编程题，求第k大的数
	//在第i层，不大于(<=num)的个数有min(n,num/i),那么>= num的个数呢？ 
	public static int findKthNumber2(int m, int n, int k) {
		// 二分法模板
		int left = 1;
		int right = m * n;
		while (left < right) {
			int mid = (left + right + 1) >>> 1;// 不能排除左边界，所以取右中位数
			int count = 0;
			for (int i = m; i >= 1; i--) {
			
				count += mid / i > n ? 0 : (mid % i == 0 ? (n - mid / i + 1) : (n - mid / i));
			}
			if (count >= k) {
				left = mid;// 不能排除左边界
			} else {
				right = mid - 1;
			}

		}
		return left;
	}

	public static void main(String[] args) {
		System.out.println(findKthNumber(3, 5, 6));
		System.out.println(findKthNumber2(3, 5, 6));
	}

}
