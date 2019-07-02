package swordForOffer;

/*
 * 题目：数组中只出现一次的数字
 * 题目1：数组中只有1个数字仅出现一次，其余数字出现两次，请找到这个仅出现一次的数字。
 * 题目2：一个整型数组里除了2个数字只出现1次之外，其他的数字都出现了2次。请写程序找出这两个只出现一次的数字。
 * 题目3：一个整型数组里除了1个数字只出现1次之外，其他的数字都出现了3次。请写程序找出这两个只出现一次的数字。
 */
public class FindNumsAppearOnce {
	//题目1：数组中只有1个数字仅出现一次，其余数字出现两次，请找到这个仅出现一次的数字。
	//思路：异或
	public static int findOneNumAppearOnce(int[] array) {
		int num = 0;
		for(int i = 0; i < array.length; i++) {
			num ^= array[i];
		}

		return num;
	}
	//题目2：一个整型数组里除了2个数字只出现1次之外，其他的数字都出现了2次。请写程序找出这两个只出现一次的数字。
	/*
	 * 问题2：若除了2个数字只出现一次，其他数字均出现两次，请找出这2个只出现一次的数字
	 * 如果能够把原数组分为两个子数组。在每个子数组中，包含一个只出现一次的数字，而其它数字都出现两次。
	 * 如果能够这样拆分原数组，按照前面的办法就是分别求出这两个只出现一次的数字了。
	 * 我们还是从头到尾依次异或数组中的每一个数字，那么最终得到的结果就是两个只出现一次的数字的异或结果。
	 * 因为其它数字都出现了两次，在异或中全部抵消掉了。由于这两个数字肯定不一样，那么这个异或结果肯定不为0 ，也就是说在这个结果数字的二进制表示中至少就有一位为1 。
	 * 我们在结果数字中找到第一个为1 的位的位置，记为第N 位。现在我们以第N 位是不是1 为标准把原数组中的数字分成两个子数组，第一个子数组中每个数字的第N 位都为1 ，
	 * 而第二个子数组的每个数字的第N 位都为0 。
	 * 现在我们已经把原数组分成了两个子数组，每个子数组都包含一个只出现一次的数字，而其它数字都出现了两次。
	 * 因此到此为止，所有的问题我们都已经解决
	 */
	public void findTwoNumsAppearOnce(int[] array, int num1[], int num2[]) {
		if (array == null || array.length <= 1) {
			return;
		}
		int len = array.length;
		int sum = 0; 
		int index = 0;
		for(int i = 0; i < len; i++) {
			sum ^= array[i];
		}
		//找到结果数字第一位为1的位置
		//1 << index:表示1左移index位
		for(index = 0; index < 32; index++) {
			if ((sum & (1 << index)) != 0) {
				break;
			}
		}
		//找到了index，然后将数组分成两个子数组
		for(int i = 0; i < len; i++) {
			if ((array[i] & (1 << index)) == 0) {
				num1[0] ^= array[i];
			}
			else {
				num1[2] ^= array[i];
			}
		}
	}
	
	// 题目3：一个整型数组里除了1个数字只出现1次之外，其他的数字都出现了3次。请写程序找出这两个只出现一次的数字。
	/*
	 * 思路：申请一个长度为32的数字，将每个数字按二进制展开，每一位相加，并保存到新建数组中，再看数组中不是3的倍数
	 * 如果是，我们要找的这个数在这位上是0，否则是1
	 */
	public static int find1from3(int[] array) {
		int[] bits = new int[32];
		int len = array.length;
		for(int i = 0; i < len; i++) {
			for(int j = 0; j < 32; j++) {
				bits[j] += (array[i] >> j) & 1;
			}
		}
		int result = 0;
		for(int i = 0; i < 32; i++) {
			if ((bits[i] % 3) != 0) {//如果不是3的倍数
				System.out.println((bits[i] % 3));
				result = result | (1 << i);
			}
		}
		return result;
 		
	}
	public static void main(String[] args) {
		int[] array = {1,2,1,4,8,2,1,2,4,4};
//		System.out.println(findOneNumAppearOnce(array));
		System.out.println(find1from3(array));
	}
}
