package leetCode;

import java.util.Arrays;

/**
 * 题目：合并数组
 * 描述：两个升序整数数组A和B，A的长度为m，B的长度为n。
 *     将数组B合并到数组A中，得到唯一的升序排序数组（假设A数组的长度足够长），
 *     并分析算法的时间复杂度和空间复杂度。
 *     
 * @author yajie
 *  这道题的精髓在于：在合并两个数组（包括字符串时），如果从前往后复制每个数字（或字符）则需要重复移动数字（获字符）多次，
 *  那么我们可以考虑从后往前复制，这样可以减少移动的次数，从而提高效率。
 */
public class MergeArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums2 == null || n <= 0) {
			return ;
		}
        //指向合并后数组的指针
        int indexMerge = m + n - 1;
       // 执行数组num1的指针
        int index1 = m - 1;
        // 执行数组num2的指针
        int index2 = n - 1;
        while(index1 >= 0 && index2 >= 0) {
        	if (nums1[index1] <= nums2[index2]) {
				nums1[indexMerge--] = nums2[index2--];
			}else {
				nums1[indexMerge--] = nums1[index1--];
			}
        }
        while(index1 >= 0) {
        	nums1[indexMerge--] = nums1[index1--];
        }
        while(index2 >= 0) {
        	nums1[indexMerge--] = nums2[index2--];
        }
//        System.out.println(Arrays.toString(nums1));
    } 
	public static void main(String[] args) {
		int[] nums1 = {1,5,7,9,0,0,0,0,0,0};
		int m = 4;
		int[] nums2 = {2,3,4,6,8,10};
		int n = 6;
		merge(nums1, m, nums2, n);
	}  
}
