package GAforSAT;

public class Suitability {
	/*
	 * ��ָ�ɵ���Ӧ�԰���С���� ð������.done
	 * 20100430,20100503
	 */
	public int[][] suitablelist(int[] suitability) {
		/*
		 * һά�����ά�� �õ�ԭ�������Ӧ�Ժͱ�ŵĶ�ά���� ��ֹ����������к�ԭ���Ļ��򲻶�Ӧ
		 */
		int length = suitability.length;
		int[][] suitable = new int[suitability.length][2];
		for (int i = 0; i < length; i++) {
			suitable[i][0] = suitability[i];
			suitable[i][1] = i;
		}
		/*
		 * ð������
		 */

		for (int i = length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (suitable[j][0] < suitable[j + 1][0]) {
					// ʵ��ֵ����
					int temp = suitable[j][0];
					suitable[j][0] = suitable[j + 1][0];
					suitable[j + 1][0] = temp;
					// ʵ��ͬ����Ž���
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
