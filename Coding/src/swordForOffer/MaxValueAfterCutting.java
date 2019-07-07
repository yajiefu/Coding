package swordForOffer;
/**
 * 题目：剪绳子
 * 描述：给你一根长度为n的绳子，请把绳子剪成m段 (m和n都是整数，n>1并且m>1)每段绳子的长度记为k[0],k[1],...,k[m].
 *     请问k[0]*k[1]*...*k[m]可能的最大乘积是多少？
 *
 * 注：这里的m不是确定的数字。
 *     
 * 思路：
 * 方法1：动态规划
 * 动态规划求解问题的四个特征： 
 * ①求一个问题的最优解； 
 * ②整体的问题的最优解是依赖于各个子问题的最优解； 
 * ③小问题之间还有相互重叠的更小的子问题； 
 * ④从上往下分析问题，从下往上求解问题；
 * 这道题很明显复合这四个特征故我们可以使用动态规划求解。
 * products表示，当绳子长度为i时，其最大乘积是products[i]，products[i]的值应为max(products[i-j]*products[j])
 * 为了避免递归产生的重复计算，采用了从下而上的计算顺序实现。时间复杂度O(n*n/2)=O(n^2)，空间复杂度O(n)
 * 
 * 方法2：贪心算法
 * 从问题的某一个初始解出发逐步逼近给定的目标，以尽可能快的地求得更好的解。当达到某算法中的某一步不能再继续前进时，算法停止，这就是贪婪算法。
 * 当n>=5时，我们尽可能多剪长度为3的绳子，当剩下长度为4时，把绳子剪成两段长度为2的绳子。、
 * 要证明  ：当n>=5时， 2(n-2)>n且3(n-3)>n
 *       当n>=5时，3(n-3)>=2(n-2)
 * @author yajie
 *
 */
public class MaxValueAfterCutting {
	// 方法1：动态规划
	public static int maxValueAfterCutting(int length) {
		if (length < 2) {
			return 0;
		}
		if (length == 2) {
			return 1;
		}
		if (length == 3) {
			return 2;
		}
		// products表示，当绳子长度为i时，其最大乘积是products[i]
		int[] products = new int[length + 1];
		// 为了后续计算方便，我们将products[1~3]的值定义如下
		products[1] = 1;
		products[2] = 2;
		products[3] = 3;
		for (int i = 4; i <= length; i++) {
			// products[i]的值应为max(products[i-j]*products[j])
			int max = 0;
			for (int j = 1; j <= i / 2; j++) {
				int product = products[i - j] * products[j];
				if (product > max) {
					max = product;
				}
			}
			products[i] = max;
		}
		return products[length];
	}

	public static int maxValueAfterCutting1(int length) {
		if (length < 2) {
			return 0;
		}
		if (length == 2) {
			return 1;
		}
		if (length == 3) {
			return 2;
		}
		int result = 1;
		// 尽可能多的剪成长度为3的绳子段
		int timesOf3 = length / 3;
		// 当绳子最后剩下的长度为4时，不能再剪。最好剪成长度为2的两段。2*2>1*3
		if (length - timesOf3 * 3 == 1) {
			timesOf3--;
		}
		int timesOf2 = (length - timesOf3 * 3) / 2;
		while (timesOf3 > 0) {
			result *= 3;
			timesOf3--;
		}
		while (timesOf2 > 0) {
			result *= 2;
			timesOf2--;
		}
		return result;

	}

	public static void main(String[] args) {
		System.out.println(maxValueAfterCutting(9));
		System.out.println(maxValueAfterCutting1(9));
		System.out.println(maxValueAfterCutting(8));
		System.out.println(maxValueAfterCutting1(8));
	}

}
