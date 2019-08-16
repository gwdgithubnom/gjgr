package GAforSAT;

import java.util.*;
/*
 * ���20�������Ļ���ָ������.doneorchange
 * 20100430
 */
public class Gene {
	public static final int VariableLength = 20;// ��Ԫ����Ԫ�ĳ���,Ҳ���ǻ���ĳ���
	public static final int GeneLength = 30;// ָ�ɵĸ�����Ҳ����ָ�ɼ�����ĳ���
	/*
	 * @������û������� @30��20λ�Ķ�������
	 * @�����ظ���ȡ�����Ż��Ժ�����
	 */
	public int[][] gene() {
		int[][] Gene = new int[GeneLength][VariableLength];
		for (int i = 0; i < GeneLength; i++) {
			Random ran = new Random();
			for (int j = 0; j < VariableLength; j++) {
				if (ran.nextBoolean()) {
					Gene[i][j] = 0;
				} else
					Gene[i][j] = 1;
			}
		}
		return Gene;
	}

	/*
	 * test
	 */
//	public static void main(String args[]) {
//		Gene gene = new Gene();
//		int[][] genee= gene.gene();
//		for (int i = 0; i < GeneLength; i++) {
//			for (int j = 0; j < VariableLength; j++) {
//				System.out.print(genee[i][j]);
//			}
//			System.out.println();
//		}
//
//	}
}
