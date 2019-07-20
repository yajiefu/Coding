package leetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 题目：720.词典中最长的单词
 * 描述：给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。
 *     若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
 *     
 * 示例 1:
 * 输入: words = ["w","wo","wor","worl", "world"]
 * 输出: "world"
 * 解释: 单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
 * 示例 2:
 * 输入: words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 输出: "apple"
 * 解释: "apply"和"apple"都能由词典中的单词组成。但是"apple"得字典序小于"apply"。
 * 注意:所有输入的字符串都只包含小写字母。words数组长度范围为[1,1000]。words[i]的长度范围为[1,30]。
 * 
 * 思路：
 * 方法1：像这样一个个字符串前缀匹配的，特别适合使用trie树。先生产TrieNode,再生成Trie树。
 *     时间复杂度：O(m*n)
 * 方法2：   step 1：先根据字典序排序数组(可利用语言api自带实现)
 *       step 2：定义Set保存单词的前缀，定义ResultWord作为返回结果
 *       step 3：遍历数组，单个字符作为最原始的值保存到Set，一个一个字符添加组成单词的前缀(eg. h -> he -> hel -> hell -> hello)
 *       step 4：最后取最大长度的单词，由于words已经按照字典升序排序，所以只要考虑长度
 *     
 * 
 * @author yajie
 *
 */

//方法1：前缀树
//执行用时 :239 ms, 在所有 Java 提交中击败了5.04%的用户
//内存消耗 :44.9 MB, 在所有 Java 提交中击败了76.92%的用户
public class LongestWord {
	//字典树(前缀树)的节点
	private class TrieNode{
		private TrieNode[] links;
		private final int R=26;
		private boolean isEnd;
		public TrieNode(){
			links = new TrieNode[R];
		}
	}
	
	//字典树(前缀树)
	private class Trie{
		private TrieNode root;
		
		public Trie() {
			root = new TrieNode();
		}
		//insert时间复杂度：O(m)
		public void insert(String word) {
			TrieNode node = root;
			for(int i = 0; i < word.length(); i++) {
				char chs = word.charAt(i);
				if (node.links[chs-'a'] == null) {
					node.links[chs-'a'] = new TrieNode();
				}
				node = node.links[chs-'a'];
			}
			node.isEnd = true;
		}
		
		 //用来判断这个字符串是不是由其他节点依次加一个字符组成
		//时间复杂度：O(m)
		public boolean isBuild(String word) {
			System.out.println(word+"-------");
			char[] chs = word.toCharArray();
			TrieNode node = root;
			for(int i = 0; i < chs.length; i++){
				
				if (!node.links[chs[i] - 'a'].isEnd) {
					return false;
				}
				node = node.links[chs[i] - 'a'];
			}
			return true;
		}
	}
	
	private String longestWord = "";
	private int longestLen = 0;
	public  String longestWord(String[] words) {
		Trie trie = new Trie();
		//依次插入前缀树 //时间复杂度：O(m*n)
		for (String word : words) {
			trie.insert(word);////时间复杂度：O(m)
		}
		//时间复杂度：O(m*n)
		for (String word : words) {
			if (trie.isBuild(word) && word.length() > longestLen) {
				longestWord = word;
				longestLen = longestWord.length();
			}else if (trie.isBuild(word) && word.length() == longestLen) {
				//返回答案中字典序最小的单词
				char[] wordChar = word.toCharArray();
				char[] longestChar = longestWord.toCharArray();
				for(int i = 0; i < longestLen; i++) {
					if (wordChar[i] - longestChar[i] < 0) {
						longestWord = word;
						break;
					}else if (wordChar[i] - longestChar[i] > 0)  {
						break;
					}
				}
			}
			
		}
		return longestWord;
	}
	
}

//执行用时 :21 ms, 在所有 Java 提交中击败了84.03%的用户
//内存消耗 :37.9 MB, 在所有 Java 提交中击败了97.69%的用户

//public class LongestWord {
//	public  String longestWord(String[] words) {
//		//先排序
//		Arrays.sort(words);
//		//Arrays.sort()根据入参类型选择以下排序算法
//		//基本类型数组使用快速排序
//		//对象数组使用归并排序
//		String res = "";
//		Set<String> set = new HashSet<>();
//		for (String word : words) {
//			if (word.length()==1 || set.contains(word.substring(0, word.length()-1))) {
//				res = word.length() > res.length() ? word : res;
//				set.add(word);
//			}
//		}
//		return res;
//	}
//}