package leetCode;

import basic.TrieNode;

/**
 * 题目：208.实现Trie(前缀树)
 * 描述：实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * 
 * 示例:
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");   
 * trie.search("app");     // 返回 true
 * 
 * 说明：你可以假设所有的输入都是由小写字母 a-z 构成的。
 *     保证所有输入均为非空字符串。
 * 
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shi-xian-trie-qian-zhui-shu-by-leetcode/
 * 
 * @author yajie
 *
 *
 * 应用： 1.自动补全 
 *     2.拼写检查 
 *     3.IP路由(最长前缀匹配) 
 *     4.T9(九宫格)打字预测
 *     5.单词游戏
 *
 * 还有其他的数据结构，如平衡树和哈希表，使我们能够在字符串数据集中搜索单词。 为什么我们还需要 Trie 树呢？
 * 尽管哈希表可以在O(1) 时间内寻找键值，却无法高效的完成以下操作： 找到具有同一前缀的全部键值。 按词典序枚举字符串的数据集。
 *
 * Trie 树优于哈希表的另一个理由是，随着哈希表大小增加，会出现大量的冲突，时间复杂度可能增加到 O(n)，其中 n是插入的键的数量。
 * 与哈希表相比，Trie 树在存储多个具有相同前缀的键时可以使用较少的空间。此时 Trie 树只需要 O(m)的时间复杂度，其中 m为键长。 
 * 而在平衡树中查找键值需要O(mlogn) 时间复杂度。
 *
 * Trie 树的结点结构 Trie 树是一个有根的树，其结点具有以下字段：
 *  最多 R个指向子结点的链接，其中每个链接对应字母表数据集中的一个字母。 本文中假定 R为 26，小写拉丁字母的数量。
 *  布尔字段，以指定节点是对应键的结尾还是只是键前缀。
 *
 *       
 *         public class TrieNode {
 * 
 *         private TrieNode[] links;
 * 
 *         private final int R = 26;
 * 
 *         public boolean isEnd = false;
 * 
 *         public TrieNode() { links = new TrieNode[R]; }
 * 
 *         public boolean containsKey(char ch) { return links[ch - 'a'] != null;
 *         }
 * 
 *         public void put(char ch, TrieNode node) { links[ch - 'a'] = node; }
 * 
 *         public TrieNode get(char ch) { return links[ch - 'a']; }
 * 
 *         public void setEnd() { isEnd = true; }
 * 
 *         public boolean isEnd() { return isEnd; } }
 *
 *下面是一些很好的问题，供您练习使用 Trie 数据结构。
 *添加与搜索单词 - 一个 Trie 树的直接应用。
 *单词搜索 II - 类似 Boggle 的游戏。
 * 
 */
public class Trie {
	private TrieNode root;
	  /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    /*
     * 我们通过搜索 Trie 树来插入一个键。我们从根开始搜索它对应于第一个键字符的链接。有两种情况：
     * 1.链接存在。沿着链接移动到树的下一个子层。算法继续搜索下一个键字符。
     * 2.链接不存在。创建一个新的节点，并将它与父节点的链接相连，该链接与当前的键字符相匹配。
     * 重复以上步骤，直到到达键的最后一个字符，然后将当前节点标记为结束节点，算法完成。
     * m为键长
     * 时间复杂度：O(m) 在算法的每次迭代中，我们要么检查要么创建一个节点，直到到达键尾。只需要 mm 次操作。
     * 空间复杂度：O(m) 最坏的情况下，新插入的键和 Trie 树中已有的键没有公共前缀。此时需要添加 mm 个结点，使用 O(m)O(m) 空间。
     */
    public void insert(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
        	char currentChar = word.charAt(i);
        	if (!node.containsKey(currentChar)) {
				node.put(currentChar, new TrieNode());
			}
        	node = node.get(currentChar);
        }
        node.setEnd();
    }
    
    /** Returns if the word is in the trie. */
    /*
     * 每个键在 trie 中表示为从根到内部节点或叶的路径。我们用第一个键字符从根开始，。检查当前节点中与键字符对应的链接。有两种情况：
     * 1.存在链接。我们移动到该链接后面路径中的下一个节点，并继续搜索下一个键字符
     * 2.不存在链接。
     *      若已无键字符，且当前标记为isEnd，则返回true。
     *      有两种可能，均返回false
     *         还有键字符剩余，但是无法根据trie树的键路径，找不到键
     *         没有键字符剩余，但当前节点没有标记为isEnd，也就是说，待查找键只是Trie树中另一个键的前缀。
     * 
     * 时间复杂度：O(m) 算法的每一步均搜索下一个键字符。最坏的情况下需要 mm 次操作。
     * 空间复杂度：O(1)
     */
    // search a prefix or whole key in trie and returns the node where search ends
    public TrieNode searchPrefix(String word) {
    	TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
        	char currentChar = word.charAt(i);
        	if (node.containsKey(currentChar)) {
        		//移动到下一个节点
				node = node.get(currentChar);
			}else {
				return null;
			}
        }
        return node;
    }
    public boolean search(String word) {
    	TrieNode node = searchPrefix(word);
    	return node != null && node.isEnd;
    	
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    /*
     * 该方法与在 Trie 树中搜索键时使用的方法非常相似。
     * 我们从根遍历 Trie 树，直到键前缀中没有字符，或者无法用当前的键字符继续 Trie 中的路径。
     * 与上面提到的“搜索键”算法唯一的区别是，到达键前缀的末尾时，总是返回 true。
     * 我们不需要考虑当前 Trie 节点是否用 “isend” 标记，因为我们搜索的是键的前缀，而不是整个键。
     * 
     * 时间复杂度：O(m)
     * 空间复杂度：O(1)
     */
    public boolean startsWith(String prefix) {
    	TrieNode node = searchPrefix(prefix);
    	return node != null;
        
    }
}
