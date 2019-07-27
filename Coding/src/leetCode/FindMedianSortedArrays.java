package leetCode;
/**
 * 题目：4.寻找两个有序数组的中位数
 * 描述：给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 * 
 * 方法1：将两个数组合并，然后根据奇偶数，返回中位数
 *      时间复杂度：O(m+n), 空间复杂度：O(m+n)
 * 方法2：我们并不需要将两个数组合并，只要找到中位数在哪就可以了。过程和方法1类似，但是到了中位数的位置就可以停止了。
 *      不需要用数组来保存，只需要用两个变量即可（因为奇偶数的关系）
 *      时间复杂度：O(m+n)，空间复杂度：O(1)
 * 方法3：log(m+n)显然要用二分的方法
 * 具体分析：
 * 我们把数组 A 和数组 B分别在 i 和 j 进行切割。
 * 将 i 的左边和 j 的左边组合成「左半部分」，将 i 的右边和 j 的右边组合成「右半部分」。
 * 1.当 A 数组和 B 数组的总长度是偶数时，如果我们能够保证：
 *    (1)左半部分的长度等于右半部分，即i+j=m-i+n-j, 也就是 j=(m+n)/2-i
 *    (2)左半部分最大的值小于等于右半部分最小的值:max(A[i-1],B[j-1]) <= min(A[i],B[i])
 *    那么中位数就可以表示为(max(A[i-1],B[j-1]) + min(A[i],B[i]) )/2.0
 * 2.当 A 数组和 B 数组的总长度是奇数时，如果我们能够保证：
 *    (1)左半部分的长度比右半部分大1，即i+j=m-i+n-j+1, 也就是 j=(m+n+1)/2-i
 *    (2)左半部分最大的值小于等于右半部分最小的值:max(A[i-1],B[j-1]) <= min(A[i],B[i])
 *    那么中位数就是max(A[i-1],B[j-1])
 * 上面第一个条件可以合并：j=(m+n+1)/2-i
 * 原因是由于int间的计算，m+n为偶数时，1/2=0，当然，由于 0<=i<=m，为了保证 0<=j<=n，我们必须保证 m<=n。
 * 
 * 对于第二个条件：奇偶数情况一样。max(A[i-1],B[j-1]) <= min(A[i],B[i])
 * 因为 A数组和 B数组是有序的，所以 A[i-1]<=A[i]，B[i-1]<=B[i]这是天然的，
 * 所以我们只需要保证 B[j-1]<=A[i]和A[i-1]<=B[j] 所以我们分两种情况讨论：
 * 1.B[j-1]>A[i]，并且为了不越界：j!=0,i!=m,此时很明显，我们要增加i，为了数量平衡还要减少j,幸运的是 j=(m+n+1)/2-i，i增大，j自然会减少
 * 2.A[i-1]>B[j],并且为了不越界：i!=0,j!=n,此时很明显，和上面相反，我们要还要减少i
 * 
 * 上边两种情况，我们把边界都排除了，需要单独讨论。
 * 1.当 i=0, 或j=0，也就是切在了最前边。
 *   此时左半部分当 j=0时，最大的值就是 A[i-1] 当 i=0,最大值就是[j-1],右半部分最小值和之前一样
 * 2.当 i=m或者 j=n，也就是切在了最后边 
 *   此时左半部分最大值和之前一样。右半部分当 j=n时，最小值就是 A[i] ；当i=m时，最小值就是B[j]。
 * 
 * 有的思路都理清了，最后一个问题，增加 i的方式。当然用二分了。初始化i为中间的值，减半找中间的直到答案。
 * @author yajie
 *
 */
public class FindMedianSortedArrays {
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;
		if (m > n) {
			return findMedianSortedArrays(nums2, nums1);//保证m<n
		}
		int iMin = 0, iMax = m;
		while(iMin <= iMax) {
			int i = (iMin + iMax) / 2;//i是num1的指针
			//j是num2的指针
			//当num1和num2总长度为偶数时，如果我们能够保证左半部分的长度等于右半部分：i+j=m-i+n-j，即：j=(m+n)/2 - i
			//当num1和num2总长度为偶数时，如果我们能够保证左半部分的长度等于右半部分+1：i+j=m-i+n-j+1，即：j=(m+n+1)/2 - i
			//因为如果m+n是偶数，由于我们取的是 int值，所以加 1 也不会影响结果
			//当然，由于 00<=i<=m ，为了保证0<=j<=n，我们必须保证m<=n
			int j = (m + n + 1) / 2 - i;//i是num2的指针
			if (j != 0 && i != m && nums2[j - 1] > nums1[i]) {//第二条件排除第1种情况，需要i增大
				iMin = i + 1;
			}
			else if (i != 0 && j != n && nums1[i - 1] > nums2[j]) {//第二条件排除第2种情况，需要i减少
				iMax = i - 1;
			}
			else {//达到第二条件的要求： B[j-1]<=A[i]和A[i-1]<=B[j] 
				//需要将边界条件单独讨论
				int maxLeft = 0;
				if (i == 0) {
					maxLeft = nums2[j-1];
				}else if (j == 0) {
					maxLeft = nums1[i - 1];
				}else {
					maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
				}
				//如果是奇数的话就不用考虑右半部分
				if (((m + n) & 1) == 1) {
					return maxLeft;
				}
				
				
				int minRight = 0;
				if (i == m) {
					minRight = nums2[j];
				}else if (j == n) {
					minRight = nums1[i];
				}else {
					minRight = Math.min(nums1[i], nums2[j]);
				}
				//如果是偶数的话返回结果
				return (maxLeft + minRight) / 2.0;
			}
		}
		return 0.0;
	}
	public static void main(String[] args) {
		int[] nums1 = {8,9,20,21};
		int[] nums2 = {3,4,5,7};
		System.out.println(findMedianSortedArrays(nums1, nums2));
	}
}
