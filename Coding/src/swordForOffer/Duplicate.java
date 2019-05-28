package swordForOffer;


/**
 * ��Ŀ���������ظ�������
 * 
 * ��������һ������Ϊn����������������ֶ���0��n-1�ķ�Χ�ڡ� ������ĳЩ�������ظ��ģ�����֪���м����������ظ��ġ�
 * Ҳ��֪��ÿ�������ظ����Ρ����ҳ�����������һ���ظ������֡�
 * ���磬������볤��Ϊ7������{2,3,1,0,2,5,3}����ô��Ӧ������ǵ�һ���ظ�������2
 * 
 * 
 */
public class Duplicate {
	/*
	 * ����1������T(n)=O(nlogn)
	 * ����2����ϣ�����������ϣ��T(n)=O(n)��S(n)=O(n)
	 * ����3����θĽ� T(n)=O(n)��S(n)=O(1)
	 * �ؼ���������ص㡪����һ������Ϊn����������������ֶ���0��n-1�ķ�Χ�ڣ����ù�ϣ�������
	 * ����1����ͷ��β����ɨ�����֣���ɨ�赽�±�Ϊi������ʱ���ȱȽ���������Ƿ���i��ȣ�����ǣ�����ɨ����һ�����֣�
	 * ����2��������ǣ��������������Ϊm���������±�Ϊi��m�����֡�
	 *      ���������ظ�����ȽϽ����Ĺ��̣�ֱ�����Ƿ���һ���ظ�������
	 */
	public static boolean duplicate(int numbers[],int length,int [] duplication) {
		if (numbers == null || length <= 1) {
			return false;
		}
		for(int i = 0; i < length; i++) {
			if (numbers[i] < 0 || numbers[i] >= length) {
				return false;
			}
		}
		for(int i = 0; i < length; i++) {
			while(numbers[i] != i) {
				if (numbers[numbers[i]] == numbers[i]) {
					// �����ȣ����ҵ����ظ�������
					duplication[0] = numbers[i];
					return true;
				}
				// �������ȣ��ͽ���
				int temp = numbers[i];
				numbers[i] = numbers[temp];
				numbers[temp] = temp;
			}
		}
		return false;
	}
	public static void main(String[] args) {
	    int[] num = {1,2,3,5,2,6,6};
	    int len = num.length;
		int[] duplication = new int[len];
			
		System.out.println(duplicate(num, len, duplication));
		}
	
}
