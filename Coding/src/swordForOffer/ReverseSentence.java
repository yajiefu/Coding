package swordForOffer;


/*
 * 题目：翻转单词顺序列
 * 描述：牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
 *     同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
 *     例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
 *     正确的句子应该是“I am a student.”。
 *     Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 *     
 * 思路：不使用字符串的反转方法。
 *     1.先将整个句子翻转
 *     2.将每个单词翻转
 */
public class ReverseSentence {
	public static char[] reverseString(char[] chs, int start, int end) {
		char temp;
		while (start < end) {
			temp = chs[start];
			chs[start++] = chs[end];
			chs[end--] = temp;
		}
		return chs;
	}

	public static String reverseSentence(String str) {
		if (str == null || str.length() <= 1) {
			return str;
		}

		char[] chs = str.toCharArray();
		reverseString(chs, 0, chs.length - 1);
		int blank = -1;
		for (int i = 0; i < chs.length; i++) {
			if (chs[i] == ' ') {
				int nextBlank = i;
				reverseString(chs, blank + 1, nextBlank - 1);
				blank = nextBlank;
			}
		}
		// 最后一个单词
		reverseString(chs, blank + 1, chs.length - 1);
		return new String(chs);

	}

	public static void main(String[] args) {
		System.out.println(reverseSentence("hello world!"));
	}

}
