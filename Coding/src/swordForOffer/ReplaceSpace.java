package swordForOffer;


/*
 * 题目：请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * 
 * 思路：
 * 方法1：用string.replace(" ", "%20")（面试中不建议该种方法）
 * 方法2：StringBuffer
 * 要处理的问题：是在原字符串上做替换还是新开一个，如果是在原来基础上。要计算有多少个空格，要新增加2*空格数量的空间
 * 从前往后移动：要不断的移动；从后往前移动，每个字符只要移动一次

 */
public class ReplaceSpace {
	public static String replaceSpace(StringBuffer str) {
		if(str == null || str.length() <= 0) {
			return "";
		}
		
		// 空格的个数
		int spaceCount = 0;
		for(int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ') {
				spaceCount++;
			}
		}
		
		int oldIndex = str.length() - 1;
		int newLength = str.length() + 2 * spaceCount;
	    //设置StringBuffer的长度
		str.setLength(newLength);
		int newIndex = str.length() - 1;
		while(oldIndex >= 0) {
			if (str.charAt(oldIndex) == ' ') {
				str.setCharAt(newIndex--, '0');
				str.setCharAt(newIndex--, '2');
				str.setCharAt(newIndex--, '%');
			}else {
				str.setCharAt(newIndex--, str.charAt(oldIndex));
			}
			oldIndex--;
		}
		return str.toString();
		
		
	}
	public static void main(String[] args) {
		String string = "We are Happy";
		StringBuffer stringBuffer = new StringBuffer(string);
		System.out.println(replaceSpace(stringBuffer));
	}
}
