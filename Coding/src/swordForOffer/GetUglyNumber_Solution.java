package swordForOffer;

/**
 * 题目：丑数 
 * 描述：把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 * 
 * 思路： 
 * 1.方法1：逐个判断每个整数是不是丑数，直观但是不高效
 * 2.方法2：根据丑数的定义，丑数应该是另一个丑数乘以2、3或者5的结果(除了1)。因此我们可以创建一个数组，里面是排好序的丑数
 * 每个抽数都是前面的丑数乘以2、3或者5得到的。 
 * 
 * 关键：在于怎样确保数组里面的丑数是排好序的。我们将以前已经存在的丑数乘以2，但是不需要将每个丑数都乘以2，因为以后的丑数是按顺序放在数组中的，
 *     肯定存在一个丑数T2，排在它之前的丑数乘以2都会小于已有的丑数，在它之后的每一个丑数乘以2都会太大，因此我们只需记住这个丑数的位置，同时
 *     每次生成新的丑数的时候，我们都要更新这个T2。同理，对于乘以3和5来说，也存在着这样的T3和T5。
 *     
 * 与方法1相比，方法2不需要在非丑数的整数上做任何计算，所以时间效率明显提高。但是增加了空间上的消耗。
 * 如创建一个1500个丑数的数组，这个数组占内存6kb。总的来说，方法2相当于用较小的空间消耗换取了时间效率的提升
 * 
 * @author yajie
 *
 */
public class GetUglyNumber_Solution {

	// 方法1：逐个判断每个整数是不是丑数，直观但是不高效
	public static int getUglyNumber(int index) {
		if (index <= 0) {
			return 0;
		}
		int num = 0;
		int uglyFound = 0;
		while (uglyFound < index) {
			num++;
			if (isUgly(num)) {
				uglyFound++;
			}
		}
		return num;
	}

	public static boolean isUgly(int number) {
		while (number % 2 == 0) {
			number /= 2;
		}
		while (number % 3 == 0) {
			number /= 3;
		}
		while (number % 5 == 0) {
			number /= 5;
		}
		return number == 1 ? true : false;
	}

	// 方法2:
	public static int getUglyNumber2(int index) {
		if (index <= 0) {
			return 0;
		}
		// 1,2,3,4,5,6都是丑数
		if (index < 7) {
			return index;
		}
		int[] uglyNumbers = new int[index];
		uglyNumbers[0] = 1;
		int p2 = 0;
		int p3 = 0;
		int p5 = 0;
		for (int i = 1; i < index; i++) {
			uglyNumbers[i] = Math.min(uglyNumbers[p2] * 2, Math.min(uglyNumbers[p3] * 3, uglyNumbers[p5] * 5));
			if (uglyNumbers[i] == uglyNumbers[p2] * 2) {
				p2++;
			}
			if (uglyNumbers[i] == uglyNumbers[p3] * 3) {
				p3++;
			}
			if (uglyNumbers[i] == uglyNumbers[p5] * 5) {
				p5++;
			}
		}

		return uglyNumbers[index - 1];
	}

	public static void main(String[] args) {
		System.out.println(getUglyNumber(9));
		System.out.println(getUglyNumber2(9));
	}
}
