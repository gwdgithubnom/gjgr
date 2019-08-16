package GAforSAT;

public class Suitability {
	/*
	 * 将指派的适应性按大小排序 冒泡排序.done
	 * 20100430,20100503
	 */
	public int[][] suitablelist(int[] suitability) {
		/*
		 * 一维数组二维化 得到原基因的适应性和编号的二维数组 防止在排序过程中和原来的基因不对应
		 */
		int length = suitability.length;
		int[][] suitable = new int[suitability.length][2];
		for (int i = 0; i < length; i++) {
			suitable[i][0] = suitability[i];
			suitable[i][1] = i;
		}
		/*
		 * 冒泡排序
		 */

		for (int i = length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (suitable[j][0] < suitable[j + 1][0]) {
					// 实现值交换
					int temp = suitable[j][0];
					suitable[j][0] = suitable[j + 1][0];
					suitable[j + 1][0] = temp;
					// 实现同步序号交换
					int seq = suitable[j][1];
					suitable[j][1] = suitable[j + 1][1];
					suitable[j + 1][1] = seq;
				}
			}
		}
		return suitable;
	}

	/**
	 * test
	 * 
	 * @param args
	 */
//	public static void main(String[] args) {
//		int[] ss = { 2, 34, 5, 23, 45 };
//		Suitability suit = new Suitability();
//		int[][] mm = suit.suitablelist(ss);
//		for (int i = 0; i < ss.length; i++) {
//			for (int j = 0; j < 2; j++) {
//				System.out.print(mm[i][j] + "\t");
//			}
//			System.out.println();
//		}
//
//	}
}
