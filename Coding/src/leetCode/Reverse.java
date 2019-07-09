package leetCode;
/**
 * 题目：7.整数翻转
 * 描述：给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 
 * int 的范围-2^31——2^31-1
 * -2147483648 —— 2147483647，会溢出
 * 思路：如果不考虑溢出的话，很简单。通过循环的方式将数字x的每一位拆开
 *   解决溢出的方法：
 *   1.通过字符串转换加try catch的方式来解决
 *   2.通过数学计算来解决：。通过循环的方式将数字x的每一位拆开，得到新值要进行判断是否溢出。
 *            溢出的条件：溢出条件有两个，一个是大于整数最大值MAX_VALUE，另一个是小于整数最小值MIN_VALUE
 *           从ans * 10 + pop > MAX_VALUE这个溢出条件来看
 *                   当出现 ans > MAX_VALUE / 10 且 还有pop需要添加 时，则一定溢出
 *                   当出现 ans == MAX_VALUE / 10 且 pop > 7 时，则一定溢出，7是2^31 - 1的个位数
 *           从ans * 10 + pop < MIN_VALUE这个溢出条件来看
 *                   当出现 ans < MIN_VALUE / 10 且 还有pop需要添加 时，则一定溢出
 *                   当出现 ans == MAX_VALUE / 10 且 pop < -8 时，则一定溢出，8是-2^31的个位数
 *                   
 *                   
 * 时间复杂度：O(log(x))，x中大约有 log10(x)位数字。
 * 空间复杂度：O(1)O(1)。
 * @author yajie
 *
 */
public class Reverse {
	
	// 不考虑溢出
	public static int reverse0(int x) {
		int revertNum = 0;
		while (x != 0) {
			revertNum = revertNum * 10 + x % 10;
			x = x / 10;
		}
		return revertNum;
	}

	// 考虑溢出
	public static int reverse(int x) {
		int ans = 0;
		while (x != 0) {
			int pop = x % 10;
			// 判断是否溢出
			if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7)) {
				return 0;
			}

			if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8)) {
				return 0;
			}
			ans = ans * 10 + pop;
			// 不能在ans后面再判断，因为此时ans已经溢出。
//			if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
//				return 0;
//			}
			x /= 10;
		}
		return ans;
	}
	
	//用字符串来解决
	public static int reverse1(int x) {
		int flag = x < 0 ? -1 : 1;
		String string = String.valueOf(Math.abs(x));
		String str = new StringBuilder(string).reverse().toString();
		
		try {
			int result = flag * Integer.parseInt(str);
			return result;
		} catch (Exception e) {
			return 0;
		}
		
	}
	public static void main(String[] args) {
		System.out.println(reverse(-123));
		System.out.println(reverse(1234));
		System.out.println(reverse(-120));
		System.out.println(reverse(-534236469));
		System.out.println(reverse(2147483647));// 溢出
		System.out.println(reverse(-2147483647));// 溢出
		System.out.println(reverse1(-534236469));
		System.out.println(reverse1(2147483647));// 溢出
	}
}
