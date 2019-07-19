package basic;
/*
 * Trie 树的结点结构 :
 * Trie 树是一个有根的树，其结点具有以下字段：
 *  最多 R个指向子结点的链接，其中每个链接对应字母表数据集中的一个字母。 本文中假定 R为 26，小写拉丁字母的数量。
 *  布尔字段，以指定节点是对应键的结尾还是只是键前缀。
 */
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
