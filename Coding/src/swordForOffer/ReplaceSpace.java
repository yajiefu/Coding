package swordForOffer;


/*
 * ��Ŀ����ʵ��һ����������һ���ַ����е�ÿ���ո��滻�ɡ�%20����
 * ���磬���ַ���ΪWe Are Happy.�򾭹��滻֮����ַ���ΪWe%20Are%20Happy��
 * 
 * ˼·��
 * ����1����string.replace(" ", "%20")�������в�������ַ�����
 * ����2��StringBuffer
 * Ҫ��������⣺����ԭ�ַ��������滻�����¿�һ�����������ԭ�������ϡ�Ҫ�����ж��ٸ��ո�Ҫ������2*�ո������Ŀռ�
 * ��ǰ�����ƶ���Ҫ���ϵ��ƶ����Ӻ���ǰ�ƶ���ÿ���ַ�ֻҪ�ƶ�һ��

 */
public class ReplaceSpace {
	public static String replaceSpace(StringBuffer str) {
		if(str == null || str.length() <= 0) {
			return "";
		}
		
		// �ո�ĸ���
		int spaceCount = 0;
		for(int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ') {
				spaceCount++;
			}
		}
		
		int oldIndex = str.length() - 1;
		int newLength = str.length() + 2 * spaceCount;
	    //����StringBuffer�ĳ���
		str.setLength(newLength);
		int newIndex = str.length() - 1;
		while(oldIndex >= 0) {
			if (str.charAt(oldIndex) == ' ') {
				str.setCharAt(newIndex--, '0');
				str.setCharAt(newIndex--, '2');
				str.setCharAt(newIndex--, '%');
			}else {
				str.setCharAt(newIndex--, str.charAt(oldIndex));
			}
			oldIndex--;
		}
		return str.toString();
		
		
	}
	public static void main(String[] args) {
		String string = "We are Happy";
		StringBuffer stringBuffer = new StringBuffer(string);
		System.out.println(replaceSpace(stringBuffer));
	}
}
