package leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 题目：211.添加和搜索单词-数据结构设计
 * 描述：设计一个支持以下两种操作的数据结构：
 *     void addWord(word)
 *     bool search(word)
 * search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
 * 
 * 示例:
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * 说明:你可以假设所有单词都是由小写字母 a-z 组成的。
 * 
 * 
 * 思路：
 * 方法1：前缀树
 * 方法2：根据字符串长度来存储 Map<Integer, Set<String>> map = new HashMap<>();
 *      然后与长度相等的字符串一一对比
 * 
 * @author yajie
 *
 */

//方法2：根据字符串的长度存放
//public class WordDictionary {
//	
//	Map<Integer, Set<String>> map = new HashMap<>();
//
//	/** Initialize your data structure here. */
//	public WordDictionary() {
//	}
//
//	/** Adds a word into the data structure. */
//	public void addWord(String word) {
//		int len = word.length();
//		if (map.get(len) != null) {
//			map.get(len).add(word);
//		}else {
//			Set<String> set = new HashSet<>();
//			set.add(word);
//			map.put(len, set);
//		}
//	}
//
//	/**
//	 * Returns if the word is in the data structure. A word could contain the dot
//	 * character '.' to represent any one letter.
//	 */
//	public boolean search(String word) {
//		Set<String> set = map.get(word.length());
//		if (set == null) {
//			return false;
//		}
//		if (set.contains(word)) {
//			return true;
//		}
//		//含有.
//		char[] chs = word.toCharArray();
//		P: for (String s : set) {
//			if (s.length() != word.length()) {
//				continue;
//			}
//			char[] cs = s.toCharArray();
//			for(int i = 0; i < cs.length; i++) {
//				//逐个字符对比
//				if (chs[i] != '.' && cs[i] != chs[i]) {
//					continue P;
//				}
//			}
//			return true;
//		}
//		return false;
//	}
//
//}
//方法1：字典树(前缀树)+递归   每个节点都开辟了长度为26的数组  196 ms	87.9 MB  
public class WordDictionary {
	private TrieNode root;

	/** Initialize your data structure here. */
	public WordDictionary() {
		root = new TrieNode();
	}

	/** Adds a word into the data structure. */
	public void addWord(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char currentChar = word.charAt(i);
			if (!node.containsKey(currentChar)) {
				node.put(currentChar, new TrieNode());
			}
			node = node.get(currentChar);
		}
		node.isEnd = true;
	}

	/**
	 * Returns if the word is in the data structure. A word could contain the dot
	 * character '.' to represent any one letter.
	 */
	public boolean match(String word, int start, TrieNode node) {
		if (start == word.length()) {
			return node.isEnd;
		}
		char currentChar = word.charAt(start);
		if (currentChar == '.') {
			for (int i = 0; i < 26; i++) {
				char ch = (char) ('a' + i);
				if (node.containsKey(ch) && match(word, start + 1, node.get(ch))) {
					return true;
				}
			}
			return false;
		} else {
			if (!node.containsKey(currentChar)) {
				return false;
			}
			return match(word, start + 1, node.get(currentChar));
		}
	}

	public boolean search(String word) {
		return match(word, 0, root);
	}

	public class TrieNode {

		private TrieNode[] links;

		private final int R = 26;

		private boolean isEnd = false;

		public TrieNode() {
			links = new TrieNode[R];
		}

		public boolean containsKey(char ch) {
			return links[ch - 'a'] != null;
		}

		public void put(char ch, TrieNode node) {
			links[ch - 'a'] = node;
		}

		public TrieNode get(char ch) {
			return links[ch - 'a'];
		}

		public void setEnd() {
			isEnd = true;
		}

		public boolean isEnd() {
			return isEnd;
		}
	}
}


//方法1的第二种写法：字典树(前缀树)+递归     超时。因为每个节点都开辟了长度为26的数组
//public class WordDictionary {
//	private TrieNode root;
//
//	/** Initialize your data structure here. */
//	public WordDictionary() {
//		root = new TrieNode();
//	}
//
//	/** Adds a word into the data structure. */
//	public void addWord(String word) {
//		TrieNode node = root;
//		for (int i = 0; i < word.length(); i++) {
//			char currentChar = word.charAt(i);
//			TrieNode next = node.links[currentChar-'a'];
//			if (next == null) {
//				node.links[currentChar-'a'] = new TrieNode();
//			}
//			node = node.links[currentChar-'a'];
//		}
//		node.isEnd = true;
//	}
//
//	/**
//	 * Returns if the word is in the data structure. A word could contain the dot
//	 * character '.' to represent any one letter.
//	 */
//	public boolean match(String word, int start, TrieNode node) {
//		if (start == word.length()) {
//			return node.isEnd;
//		}
//		char currentChar = word.charAt(start);
//		if (currentChar == '.') {
//			for (int i = 0; i < 26; i++) {
//				if (node.links[i] != null && match(word, start+1, node.links[i])) {
//					return true;
//				}
//			}
//			return false;
//		} else {
//			if (node.links[currentChar-'a'] == null) {
//				return false;
//			}
//			return match(word, start+1, node.links[currentChar-'a']);
//		}
//	}
//
//	public boolean search(String word) {
//		return match(word, 0, root);
//	}
//
//	public class TrieNode {
//
//		private TrieNode[] links;
//
//		private final int R = 26;
//
//		private boolean isEnd = false;
//
//		public TrieNode() {
//			links = new TrieNode[R];
//		}
//	}
//}
/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
