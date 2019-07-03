package swordForOffer;


/**
 * 题目：连续子数组的最大和 
 * 描述：在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
 *     但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
 *     例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。 给一个数组，返回它的最大连续子序列的和
 * 
 * 
 * 
 * @author yajie
 *
 */
public class FindGreatestSumOfSubArray {
	//
	public static int findGreatestSumOfSubArray(int[] array) {
		if (array == null || array.length <= 0) {
			return 0;
		}
		int max = array[0];//用于保存最大和的值
		int sum = 0;
		for(int i = 0; i < array.length; i++) {
			if (sum <= 0) {//如果之前的sum为负数的话，则从现在这个点开始
				sum  = array[i];
			}else {
				sum += array[i];
			}
			if (sum > max) {
				max = sum;
			}
			
		}
		return max;

	}
	
	//动态规划：用f(i)来表示以第i个数字结尾的子数组的最大和。
	// f(i) = arr[i]          当i=0或f(i-1)<=0
	//        f(i-1)+arr[i]   当i不为0或f(i-1)>0
	//通常用递归方式做，但最终都会基于循环去编码
	//其实f[i]对应的上一种方法的sum。可见两种方法的思路异曲同工。
	public static int findGreatestSumOfSubArray1(int[] array) {
		if (array == null || array.length <= 0) {
			return 0;
		}
		int[] f = new int[array.length];
		f[0] = array[0];
		int max = array[0];
		for(int i = 1; i < array.length; i++) {
			if (f[i-1] <= 0) {
				f[i] = array[i];
			}else {
				f[i] = f[i-1] + array[i];
			}
			
			if (f[i] > max) {
				max = f[i];
			}
		}
		return max;
	}
	public static void main(String[] args) {
		int[] arr = {6,-3,-2,7,-15,1,2,2};
		System.out.println(findGreatestSumOfSubArray(arr));
		System.out.println(findGreatestSumOfSubArray1(arr));
		
	}
}
