package swordForOffer;


/*
 * ��Ŀ����������1�ĸ���
 * ����������һ��long���͵���ֵ, �����ֵ�Ķ����Ʊ�ʾ�е�1�ĸ��� .
 */
public class NumberOf1 {

	// ����1��λ����
	/*
	 * ���һ��������Ϊ0����ô�������������һλ��1��
	 * ������ǰ����������1����ôԭ�������������ұߵ�1�ͻ��Ϊ0��ԭ����1��������е�0������1(������ұߵ�1���滹��0�Ļ�)��
	 * ��������λ�������ܵ�Ӱ�졣�ٸ����ӣ�һ����������1100�����ұ��������λ�Ǵ������ұߵ�һ��1��
	 * ��ȥ1�󣬵���λ���0�����������λ0�����1����ǰ���1���ֲ��䣬��˵õ��Ľ����1011.���Ƿ��ּ�1�Ľ���ǰ����ұߵ�һ��1��ʼ������λ��ȡ���ˡ�
	 * ���ʱ����������ٰ�ԭ���������ͼ�ȥ1֮��Ľ���������㣬��ԭ���������ұ�һ��1��һλ��ʼ����λ������0����1100&1011=1000.
	 * Ҳ����˵����һ��������ȥ1���ٺ�ԭ�����������㣬��Ѹ��������ұ�һ��1���0.��ôһ�������Ķ������ж��ٸ�1���Ϳ��Խ��ж��ٴ������Ĳ�����
	 */
	public static int numberOf1(int number) {
		int count = 0;
		while(number != 0) {// ������>0
			number = (number - 1) & number;
			count++;
		}
		return count;
	}
	// ����2���ύţ��������벻ͨ��
	public static int numberOfOne(int number) {
		int count = 0;
		String num = Integer.toBinaryString(number);

		char[] nums = num.toCharArray();
		for(int i = 0; i < nums.length; i++) {
			if (nums[i] == '1') {
				count++;
			}
		}
		return count;
	}
	public static void main(String[] args) {
		System.out.println(numberOfOne(4));
	}
}
