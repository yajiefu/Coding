package leetCode;

import java.util.Stack;

/*
 * 题目：20有效的括号
 * 描述：给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：左括号必须用相同类型的右括号闭合。左括号必须以正确的顺序闭合。注意空字符串可被认为是有效字符串。
 * 
 * 思路：1.碰到左括号，就进栈；
 *     2.碰到右括号：
 *        （1）如果栈为空，则返回false，
 *        （2）如果不为空，和栈顶元素匹配就出栈，如果不匹配就返回false
 *     如果最后栈为空，就返回true
 */
public class IsValid {
	public static boolean isValid(String s) {
		if (s.equals("")) {
			return true;
		}
		Stack<Character> stack = new Stack<>();
		char[] chs = s.toCharArray();
		for(int i = 0; i < chs.length; i++) {
			if (chs[i] == '{' || chs[i] == '(' || chs[i] == '[' ) {
				stack.push(chs[i]);
			}
			else {
				if (stack.isEmpty()) {
					return false;
				}else {
					if (isMatch(stack.peek(), chs[i])) {
						stack.pop();
					}else {
						return false;
					}
				}
			}
		}
		return stack.isEmpty();
	}
	public static boolean isMatch(char c1, char c2) {
		if ((c1 == '{' && c2 == '}') || (c1 == '(' && c2 == ')') ||(c1 == '[' && c2 == ']')) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		System.out.println(isValid("[}]"));
		System.out.println(isValid("{()}"));
		System.out.println(isValid("{}[]()"));
		System.out.println(isValid("{[}]"));
		System.out.println(isValid("{[]}"));
	}
}
