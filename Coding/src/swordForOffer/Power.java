package swordForOffer;
/**
 * 题目：数值的整数次方
 * 描述：给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * 
 * 注意：要考虑多种情况
 * 1.base为0，1，负数的情况
 * 2.exponent为0，1，负数的情况
 * 
 * 当base为0，且exponent为负数的情况，会对0求倒数。我们可以采用三种方法：返回值，全局代码，异常。
 * 在判断base是否等于0时，不能直接写base==0，这是因为在计算机内表示小数时（包括float和double）都有误差。
 * 判断两个小数是否相等，只能判断他们之差的绝对值是不是在一个很小的范围。如果两个小数相差很小，就可以认为他们相等。
 * 
 * 以上分析，考虑很周祥了。但是如果输入的exponent为32，我们要做31次乘法。
 * 但我们可以换一种思路。如果我们已经知道了它的16次方，我们在此基础上只要再平方一次就好了，依次类推。
 * 
 * a^n = a^(n/2)*a^(n/2)           当n为偶数时
 *     = a^((n-1)/2)*a^((n-1)/2)*a 当n为奇数时
 * @author yajie
 *
 */
public class Power {
	// 方法1
	public static double power1(double base, int exponent) {
		// base为0，且exponent为负数，分母不能为0
		if (equal(base, 0.0) && exponent < 0) {
			throw new RuntimeException("分母不能为0");
		}
		if (exponent == 0) {
			return 1;
		}
		if (base == 1 || exponent == 1) {
			return base;
		}
		double result = 1;
		if (exponent > 0) {// exponent为正数
			while (exponent > 0) {
				result *= base;
				exponent--;
			}
		} else {
			exponent = -exponent;
			while (exponent > 0) {
				result *= base;
				exponent--;
			}
			result = 1 / result;
		}
		return result;

	}

	public static boolean equal(double num1, double num2) {
		if (num1 - num2 > -0.0000001 && num1 - num2 < 0.0000001) {
			return true;
		} else {
			return false;
		}
	}

	//方法2：递归
	public static double power2(double base, int exponent) {
		// base为0，且exponent为负数，分母不能为0
		if (equal(base, 0.0) && exponent < 0) {
			throw new RuntimeException("分母不能为0");
		}
		if (exponent == 0) {
			return 1;
		}
		if (base == 1 || exponent == 1) {
			return base;
		}
		if (exponent > 0) {
			return power2help(base, exponent);
		}else {
			return 1 / power2help(base, -exponent);
		}
		
	
	}
	public static double power2help(double base, int exponent) {
		if (exponent == 1) {
			return base;
		}
		if ((exponent & 1) == 0) {
			double temp = power2(base, exponent >> 1);
			return temp * temp;
		}else {
			double temp = power2(base, (exponent - 1) >> 1);
			return temp * temp * base;
		}
	}
	//不用递归
	public static double power3(double base, int exponent) {
		int p;
		double result = 1;
		if (exponent > 0) {
			p = exponent;
		}else if (exponent < 0) {
			if (equal(base, 0.0)) {
				throw new RuntimeException("分母不能为0");
			}else {
				p = - exponent;
			}
		}else {//exponent = 0
			return 1;
		}
		
		while(p != 0) {
			if ((p & 1) == 1) {
				result *= base;
			}
			base *= base;//翻倍
			p >>= 1;
		}
		return exponent > 0 ? result : 1 / result;
	}
	public static void main(String[] args) {
		System.out.println(power1(0, 2));
		System.out.println(power1(-2, 0));
		System.out.println(power1(-2, 3));
		System.out.println(power1(5, -2));
		System.out.println("****************");
		System.out.println(power2(0, 2));
		System.out.println(power2(-2, 0));
		System.out.println(power2(-2, 3));
		System.out.println(power2(5, -2));
		
		System.out.println("****************");
		System.out.println(power3(0, 2));
		System.out.println(power3(-2, 0));
		System.out.println(power3(-2, 3));
		System.out.println(power3(5, -2));
	}
}
