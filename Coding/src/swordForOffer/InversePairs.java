package swordForOffer;

/*
 * 题目：数组中的逆序对
 * 描述：在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 *            输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 *            
 *输入描述：题目保证输入的数组中没有的相同的数字
 *数据范围：
 *        对于%50的数据,size<=10^4 
 *        对于%75的数据,size<=10^5
 *        对于%100的数据,size<=2*10^5   
 *        
 *                 
 *思路：
 *方法1：暴力求解，时间复杂度T(n)=O(n2)
 *方法2：归并排序。T(n)=O(nlogn) S(n)=O(n)
 *            
 */
public class InversePairs {
	int count = 0;
	public int inversePairs(int[] array) {
		int[] temp = new int[array.length];
		sort(array, 0, array.length - 1, temp);
		return count;

	}
	
	//下面是归并排序
	public void sort(int[] array, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2;
			sort(array, left, mid, temp);//左边归并排序，使得左子序列有序
			sort(array, mid + 1, right, temp);//右边归并排序，使得右子序列有序
			merge(array, left, mid, right, temp);//将两边数组进行合并
		}
	}
	
	public void merge(int[] array, int left, int mid, int right, int[] temp) {
		int i = left;// 左序列指针
		int j = mid + 1;// 右序列指针
		int k = 0;//临时数组的指针
		while(i <= mid && j <= right) {
			if (array[i] <= array[j]) {
				temp[k++] = array[i++];
			}else {
				//核心部分：当array[i] > array[j]时
				temp[k++] = array[j++];
				count += mid - i  + 1;
				count %= 1000000007;
			}
		}
		// 当两个数组中有一个数组的数全部放到新排序数组中时。可能存在某个数组还有数。
		// 将左边剩余元素都填充到tmp
		while(i <= mid) {
			temp[k++] = array[i++];
		}
		// 右边剩余元素都填充到tmp
		while(j <= right) {
			temp[k++] = array[j++];
		}
		k = 0;
		//将temp数组中的数拷贝到原数组中去
		while(left <= right) {
			array[left++] = temp[k++];
		}
	}
}
