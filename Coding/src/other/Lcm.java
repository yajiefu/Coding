package other;
/**
 * 最小公倍数
 * 最小公倍数 = (m * n) / 最大公约数gcd
 * @author yajie
 *
 */
public class Lcm {

	public static int lcm(int m, int n) {
		int gcd = gcd(m, n);
		return (m / gcd) * (n / gcd) * gcd;
	}

	// 迭代
	public static int gcd(int m, int n) {
		if (n == 0) {
			return m;
		}
		while (m % n != 0) {// 不用区分谁大谁小。如果刚开始m小，n大，经过一次后，自然m大，n小
			int temp = n;
			n = m % n;
			m = temp;
		}
		return n;
	}
	
	public static void main(String[] args) {
		int m = 18;
		int n = 12;
		System.out.println(lcm(m, n));
	}
}
