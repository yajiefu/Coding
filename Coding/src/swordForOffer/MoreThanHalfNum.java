package swordForOffer;
/**
 * 题目：数组中出现次数超过一半的数字
 * 描述：数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *            例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
 *            如果不存在则输出0。
 *
 *思路：
 *方法0：最直观的排序。这个不好。
 *方法1：由于该数字出现次数超过一半，所以排序后的数组的中位数就是它。有成熟的O(n)的partition算法我们可以得到数组中任意第k大的数字。
 *方法2.由于该数字出现次数超过一半，所以我们可以每次对数组任意去掉不相同的两个数字，最后留下来的两个肯定是相同的，如果留下来的是一个，那个就是我们要找到的数字
 *  时间复杂度：O(n)
 *方法3：同样是方法2的思想，我们可以在遍历数组时保存两个变量：一个是数组中的数字，一个是次数。
 *    当我们遍历下一个数字时，如果和我们之前保存的数字不一样，则次数-1；如果一样就+1；如果次数为0，我们需要保存下一个数字，并把次数设置为1。那么最后一次把次数设置为1的数字就是我们要找的数字。
 *    
 * @author yajie
 *
 *注意：如果输入的数组无效怎么办？比如最高频率的数字出现的次数没有超过数组长度的一半。
 *所以，我们用个函数CheckMoreThanHalf进行判断。另外也可设置一个全局变量来表示输入无效的情况boolean inputInvalid = true;
 */
public class MoreThanHalfNum {
	//方法1
	public static int moreThanHalfNum_Solution1(int[] array) {
		if (array == null||array.length <= 0) {
			return 0;
		}
		int len = array.length;
		int start = 0;
		int end = len - 1;
		int middle = len >> 1;
		int index = partition(array, start, end);
		while(index != middle) {
			if (index > middle) {
				end = index - 1;
				index = partition(array, start, end);
			}else {
				start = index + 1;
				index = partition(array, start, end);
			}
		}
		int result = array[middle];
		if (!checkMoreThanHalf(array, result)) {
			result = 0;
		}
		return result;

	}
	
	
	//方法3：
	public static int moreThanHalfNum_Solution2(int[] array) {
		if (array == null||array.length <= 0) {
			return 0;
		}
		int result = array[0];
		int times = 1;
		for(int i = 1; i < array.length; i++) {
			if (times == 0) {
				result = array[i];
				times = 1;
			}
			else if (array[i] == result) {
				times++;
			}else {
				times--;
			}
		}
		if (!checkMoreThanHalf(array, result)) {
			result = 0;
		}
		return result;
		
	}

	//partition
	public static int partition(int[] arr, int low, int high) {
		// 设定基准值(pivot)
		int pivot = low;
		int index = pivot + 1;
		for (int i = index; i <= high; i++) {
			if (arr[i] < arr[pivot]) {
				swap(arr, i, index);
				index++;
			}
		}
		swap(arr, pivot, index - 1);
		
		return index - 1;
	}
	//检查数组是否输入无效
	private static boolean checkMoreThanHalf(int[] arr, int number) {
		int times = 0;
		int len = arr.length;
		for(int i = 0; i < len; i++) {
			if (arr[i] == number) {
				times++;
			}
		}
		boolean isMoreThanHalf = true;
		if (times * 2 <= len) {
			isMoreThanHalf = false;
		}
		return isMoreThanHalf;
	}
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
	public static void main(String[] args) {
		int[] array = {1,2,4,3,5,2,2,2,8,2,2};
		System.out.println(moreThanHalfNum_Solution2(array));
		int[] array1 = {1,4,3,5,2,2,8,2};//不符合标准
		System.out.println(moreThanHalfNum_Solution2(array1));
	}
}
