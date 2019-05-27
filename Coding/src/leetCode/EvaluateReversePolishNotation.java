package leetCode;

import java.util.Stack;
/*
Evaluate Reverse Polish Notation:
Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9

 */
public class EvaluateReversePolishNotation {
	// 方法1：(一个高赞回答)：利用异常
	public static int evalRPN1(String[] tokens) {
		
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < tokens.length; i++) {
			try {
				Integer num = Integer.parseInt(tokens[i]);
				stack.push(num);
			} catch (Exception e) {
				int a = stack.pop();
				int b = stack.pop();
				stack.push(get(a, b, tokens[i]));
			}
		}
		return stack.pop();
	}
	public static int get(int a, int b, String c) {
		switch (c) {
		case "+":
			return a + b;
		case "-":
			return b - a;
		case "*":
			return a * b;
		case "/":
			return b / a;
		default:
			return 0;
		}
	}
	
	
	// 方法2:
	public static int evalRPN(String[] tokens) {
		if (tokens == null || tokens.length <= 0) {
			return 0;
		}
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < tokens.length; i++) {
			String cur = tokens[i];
			if (cur.equals("+") || cur.equals("-") || cur.equals("*") || cur.equals("/")) {
				// 如果是符号的话，就连同前面的值一起出栈
				int a = stack.pop();
				int b = stack.pop();
				stack.push(calculate(a, b, cur));
			}else {
				// 如果是数字，进栈
				stack.push(Integer.parseInt(cur));
				
			}
		}
		if (stack.size() == 1) {
			return stack.pop();
		}else {
			return 0;
		}
        
    }
	private static int calculate(int a,int b,String token) {
		if (token.equals("+")) {
			return a + b;
		}
		if (token.equals("-")) {
			return b - a;
		}
		if (token.equals("*")) {
			return a * b;
		}
		else return b / a;
	}
	
	public static void main(String[] args) {
		String[] tokens = {"2", "1", "+","3", "*"};
		
		System.out.println(evalRPN(tokens));
	}
}
