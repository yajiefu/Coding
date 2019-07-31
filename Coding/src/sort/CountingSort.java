package sort;

import java.util.Arrays;

/**
 * 计数排序
 * 算法步骤：
 * 1.花O(n)的时间扫描一下整个序列 A，获取最小值 min 和最大值 max
 * 2.开辟一块新的空间创建新的数组B，长度为 ( max - min + 1)
 * 3.数组B中index的元素记录的值是 A中某元素出现的次数
 * 4.最后输出目标整数序列，具体的逻辑是遍历数组 B，输出相应元素以及对应的个数
 * 
 * 
 * 时间复杂度：O(n+k)，n是元素的个数，k是辅助数组的空间大小
 * 空间复杂度：O(n+k)
 * 稳定性：稳定
 * 
 * 适用于：当k不是很大并且序列比较集中时
 * @author yajie
 *
 */
public class CountingSort {
	public static int[] countingSort(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return arr;
		}
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		//找到最小值和最大值
		for(int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
			if (arr[i] < min) {
				min = arr[i];
			}
		}
		//辅助数组
		int[] helper = new int[max - min + 1];
		
		//找出每个数字出现的次数
		for(int i = 0; i < arr.length; i++) {
			helper[arr[i] - min]++;
		}
		
		//根据helper数组进行排序
		for(int i = 0 , index = 0; i < helper.length; i++) {
			int item = helper[i];
			while(item-- != 0) {
				arr[index++] = i + min;
			}
		}
		return arr;
	}
	
	public static void main(String[] args) {
		int[] arr = {1,3,6,8,3,2,5,7};
		System.out.println(Arrays.toString(countingSort(arr)));
	}

}
