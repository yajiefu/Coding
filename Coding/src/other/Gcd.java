package other;
/**
 * 求两个数的最大公约数
 * 求出两个数的最大公约数，如果有一个自然数a能被自然数b整除，则称a为b的倍数，b为a的约数。
 * 几个自然数公有的约数，叫做这几个自然数的公约数。公约数中最大的一个公约数，称为这几个自然数的最大公约数。
 * 输入描述:输入两个整数n,m, n和m的范围是[1,10^9][1,10^9]。
 * @author yajie
 *
 */
public class Gcd {
	//递归
	public static int gcd(int m, int n) {
		if (n == 0) {
			return m;
		}
		return gcd(n, m % n);
	}
	//迭代
	public static int gcd1(int m, int n) {
		if (n == 0) {
			return m;
		}
		while (m % n != 0) {//不用区分谁大谁小。如果刚开始m小，n大，经过一次后，自然m大，n小
			int temp = n;
			n = m % n;
			m = temp;
		}
		return n;
	}
	public static void main(String[] args) {
		int m = 18;
		int n = 12;
		System.out.println(gcd1(m, n));
		System.out.println(gcd(m, n));
	}
}
