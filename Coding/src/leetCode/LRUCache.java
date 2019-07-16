package leetCode;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 题目：146.LRU缓存机制
 * 描述：运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *     获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 *     写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *     进阶:你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * 示例:
 * LRUCache cache = new LRUCache(2) //缓存容量;
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // 返回  1
 * cache.put(3, 3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4, 4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 * 
 * 
 * 思路1：基于LinkedHashMap
 * 1.自己想的一个版本
 *      LinkedHashMap数据结构。写一个新的LRULinkedHashMap继承LinkedHashMap
 *      重写里面的一个方法removeEldestEntry()
 *      put时，如果存在先删掉再重新插入,不存在就直接插入
 *      get时，如果不存在返回-1，存在的话先删除后插入
 * 2.还是基于LinkedHashMap数据结构。
 *   知识点：
 *    (1)而其中的 LinkedHashMap 通过设置 accessOrder 决定按照插入顺序还是访问顺序记录数据。
 *      accessOrder 默认为 false，按插入顺序排序
 *      accessOrder 按访问顺序记录
 *    (2)再重写 removeEldestEntry 方法，LinkedHashMap 会在插入时帮忙删除数据
 *    (3)linkedhashMap.getOrDefault(key, defaultValue)//如果key不存在就返回defaultValue
 *  复杂度分析
 *  时间复杂度：对于 put 和 get 操作复杂度是 O(1)，因为有序字典中的所有操作：get/in/set/move_to_end/popitem（get/containsKey/put/remove）都可以在常数时间内完成。
 *  空间复杂度：O(capacity)，因为空间只用于有序字典存储最多 capacity + 1 个元素。
 *  
 *  
 *  思路2：基于HashMap+双向链表
 *  这个问题可以用哈希表，辅以双向链表记录键值对的信息。所以可以在 O(1)O(1) 时间内完成 put 和 get 操作，同时也支持 O(1)O(1) 删除第一个添加的节点。
 * 
 * 
 * 
 * @author yajie
 *
 */
/*
// 版本1
public class LRUCache {
	 
	private int capacity;
	private LRULinkedHashMap<Integer, Integer> lruLinkedHashMap = new LRULinkedHashMap<>();

	private class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
		@Override
		protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
			if (size() > capacity) {
				return true;
			}
			return false;
		}

	}

	public LRUCache(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {
		Integer value = lruLinkedHashMap.get(key);
		if (null == value) {
			return -1;
		}
		lruLinkedHashMap.remove(key);
		lruLinkedHashMap.put(key, value);
		return value;
	}

	public void put(int key, int value) {
		if (lruLinkedHashMap.containsKey(key)) {
			lruLinkedHashMap.remove(key);
		}
		lruLinkedHashMap.put(key, value);
	}
}
	 */

//版本2：官方解法
public class LRUCache extends LinkedHashMap<Integer, Integer>{
	private int capacity;
	

	public LRUCache(int capacity) {
		super(capacity, (float) 0.75,true);
		this.capacity = capacity;
	}

	public int get(int key) {
		return super.getOrDefault(key, -1);
	}

	public void put(int key, int value) {
		super.put(key, value);
	}
	@Override
	protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

}
