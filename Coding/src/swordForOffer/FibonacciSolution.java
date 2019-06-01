package swordForOffer;

import java.util.ArrayList;

/*
 * 题目：斐波纳契数列
 * 描述：大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
n<=39
 * 斐波纳契数列以如下被以递归的方法定义：F(0)=0，F(1)=1, F(n)=F(n-1)+F(n-2)（n>2，n∈N*）
 */
public class FibonacciSolution {
	// 方法1 递归,但是存在大量计算
	public static int Fibonacci1(int n) {
		if (n <= 0) {
			return 0;
		}
		if (n == 1 || n == 2) {
			return n;
		}
		return Fibonacci1(n - 1) + Fibonacci1(n - 2);
    }
	
	// 方法2：将数据保留下来 时间复杂度O(n) 空间复杂度O(n)
	public static int Fibonacci2(int n) {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(1);
		list.add(2);
		if (n == 0 || n == 1 || n == 2) {
			return list.get(n);
		}
		for(int i = 3; i <= n; i++) {
			list.add(i, list.get(i - 1) + list.get(i - 2));
		}
		
		return list.get(n);
	}
	
	
	// 方法3：迭代法。只讲前两个数据保留下来。时间复杂度O(n), 空间复杂度O(1)
	public static int Fibonacci3(int n) {
		if (n <= 0) {
			return 0;
		}
		if (n == 1 || n == 2) {
			return n;
		}
		int num1 = 1;
		int num2 = 2;
		
		while(n-- > 2) {
			num2 = num1 + num2;
			num1 = num2 - num1;
			
		}
		// 或者
//		for(int i = 3; i <= n; i++) {
//			num2 = num1 + num2;
//			num1 = num2 - num1;
//		}
		return num2;
	}
	public static void main(String[] args) {
		System.out.println(Fibonacci1(7));
		System.out.println(Fibonacci2(7));
		System.out.println(Fibonacci3(7));
	}
}
