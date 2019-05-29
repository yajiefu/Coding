package leetCode;

import java.util.Arrays;

/**
 * ��Ŀ���ϲ�����
 * ����������������������A��B��A�ĳ���Ϊm��B�ĳ���Ϊn��
 *     ������B�ϲ�������A�У��õ�Ψһ�������������飨����A����ĳ����㹻������
 *     �������㷨��ʱ�临�ӶȺͿռ临�Ӷȡ�
 *     
 * @author yajie
 *  �����ľ������ڣ��ںϲ��������飨�����ַ���ʱ���������ǰ������ÿ�����֣����ַ�������Ҫ�ظ��ƶ����֣����ַ�����Σ�
 *  ��ô���ǿ��Կ��ǴӺ���ǰ���ƣ��������Լ����ƶ��Ĵ������Ӷ����Ч�ʡ�
 */
public class MergeArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums2 == null || n <= 0) {
			return ;
		}
        //ָ��ϲ��������ָ��
        int indexMerge = m + n - 1;
       // ִ������num1��ָ��
        int index1 = m - 1;
        // ִ������num2��ָ��
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
