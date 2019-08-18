package swordForOffer;


/*
 * 题目：旋转数组的最小数字
 * 描述：把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。 
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 *  NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *  
 *  
 *  思路：方法1：遍历O(n)，没有利用旋转数组的特性
 *      方法2：两个有序的子数组，而且前面的数组都大于或等于后面的数组的元素。最小的元素还是分界点。二分查找。
 *      
 *      当mid在后面的递增数组的时候，arr[mid]应该<= arr[high],此时应该high移动到 mid的位置
 *      当mid在前面的递增数组的时候，arr[mid]应该>= arr[low]，此时应该low移动到mid的位置
 *      
 *      注意：如果arr[low]==arr[mid]==arr[high]只能顺序查找
 *      
 *      

 */
public class MinNumberinRotateArray {

	public static int minNumberInRotateArray(int [] array) {
		int length = array.length;
		if (length <= 0) {
			return 0;
		}
		int low = 0;
		int high = length - 1;
		int mid = (high + low) / 2;
		
		while(array[low] >= array[high]) {
			if (high - low == 1) {
				mid = high;
			    break;
			}
			mid = (high + low) / 2;
			// 三者相等时，比如1,1,1,1,0,1  或者1,0,1,1,1,1时，只能顺序查找
			if (array[low] == array[mid] && array[mid] == array[high]) {
				int min = array[mid];
				for(int i = low; i <= high; i++) {
					if (array[i] < min) {
						return array[i];
					}
				}
			}
			// 当mid在后面的递增数组的时候，arr[mid]应该<= arr[high],此时应该high移动到 mid的位置
			if (array[mid] <= array[high]) {
				high = mid;
			}
			// 当mid在前面的递增数组的时候，arr[mid]应该>= arr[low]，此时应该low移动到mid的位置
			if (array[mid] >= array[low]) {
				low = mid;
			}
			
		}
		return array[mid];
		
	}
	
	public static void main(String[] args) {
		int[] array = {4,5,6,7,0,1,2};
		System.out.println(minNumberInRotateArray(array));
	}
}
