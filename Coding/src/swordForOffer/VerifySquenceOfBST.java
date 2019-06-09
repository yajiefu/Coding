package swordForOffer;

/*
 * 题目：二叉搜索树的后序遍历序列
 * 描述：输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class VerifySquenceOfBST {
	public static boolean verifySquenceOfBST(int[] sequence) {
		if (sequence == null || sequence.length <= 0) {
			return false;
		}
		return verifySquenceOfBST(sequence, 0, sequence.length - 1);
	}

	public static boolean verifySquenceOfBST(int[] sequence, int start, int end) {
		// 根节点
		int root = sequence[end];
		// 二叉搜索树中的左子树的节点均小于根节点
		int i = start;
		for (; i < end; i++) {
			if (sequence[i] > root) {
				break;
			}
		}

		// 二叉搜索树中的右子树的节点均大于根节点
		int j = i;
		for (; j < end; j++) {
			if (sequence[j] <= root) {
				return false;
			}
		}

		// 判断左子树是不是二叉搜索树
		boolean left = true;
		if (i > start) {
			left = verifySquenceOfBST(sequence, start, i - 1);
		}

		// 判断右子树是不是二叉搜索树
		boolean right = true;
		if (i < end) {
			right = verifySquenceOfBST(sequence, i, end - 1);
		}
		return left && right;
	}

	public static void main(String[] args) {
		int[] sequence = { 5, 7, 6, 9, 11, 10, 8 };
		System.out.println(verifySquenceOfBST(sequence));
		int[] sequence1 = { 2, 1, 4, 3, 5, 6, 7 };
		System.out.println(verifySquenceOfBST(sequence1));
		int[] sequence2 = { 7, 4, 6, 5 };
		System.out.println(verifySquenceOfBST(sequence2));
	}
}
