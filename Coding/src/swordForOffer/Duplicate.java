package swordForOffer;


/**
 * 题目：数组中重复的数字
 * 
 * 描述：在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2
 * 
 * 
 */
public class Duplicate {
	/*
	 * 方法1：排序，T(n)=O(nlogn)
	 * 方法2：哈希表，遍历放入哈希表，T(n)=O(n)，S(n)=O(n)
	 * 方法3：如何改进 T(n)=O(n)，S(n)=O(1)
	 * 关键：数组的特点——在一个长度为n的数组里的所有数字都在0到n-1的范围内，利用哈希表的特性
	 * 步骤1：从头到尾依次扫描数字，当扫描到下标为i的数字时，先比较这个数字是否与i相等，如果是，接着扫描下一个数字；
	 * 步骤2：如果不是，（假设这个数字为m），交换下标为i和m的数字。
	 *      接下来再重复这个比较交换的过程，直到我们发现一个重复的数字
	 */
	public static boolean duplicate(int numbers[],int length,int [] duplication) {
		if (numbers == null || length <= 1) {
			return false;
		}
		for(int i = 0; i < length; i++) {
			if (numbers[i] < 0 || numbers[i] >= length) {
				return false;
			}
		}
		for(int i = 0; i < length; i++) {
			while(numbers[i] != i) {
				if (numbers[numbers[i]] == numbers[i]) {
					// 如果相等，即找到了重复的数字
					duplication[0] = numbers[i];
					return true;
				}
				// 如果不相等，就交换
				int temp = numbers[i];
				numbers[i] = numbers[temp];
				numbers[temp] = temp;
			}
		}
		return false;
	}
	public static void main(String[] args) {
	    int[] num = {1,2,3,5,2,6,6};
	    int len = num.length;
		int[] duplication = new int[len];
			
		System.out.println(duplicate(num, len, duplication));
		}
	
}
