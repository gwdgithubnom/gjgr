package GAforSAT;

import java.util.*;

/*
 * �Ŵ�.done
 * @liu
 * ��ԭʼ��������Ŵ��������µĸ���ָ��
 * 30������ָ��ȥ��10��
 * ������25���Ŵ����10��
 * 20100503
 */

public class Genetic {

	public static final int VariableLength = 20;// ��Ԫ����Ԫ�ĳ���,Ҳ���ǻ���ĳ���
	public static final int GeneticNode = 25;// ָ�ɱ���ʱ������ָ����
	public static final int GeneLength = 30;// ָ�ɵĸ�����Ҳ����ָ�ɼ�����ĳ���
	/*
	 * ��þ�����Ӧ���������Ӧ����ߵ�25������ ��ͨ���Ŵ�����µ�10������
	 */
	public int[][] geneticGene(int[][] Gene, int[][] suitable) {
		int[][] geneticgene = new int[GeneLength][VariableLength];
		// ȡ����Ӧ�Ըߵ�20������
		for (int i = 0; i < GeneticNode; i++) {
			for (int j = 0; j < VariableLength; j++) {
				geneticgene[i][j] = Gene[suitable[i][1]][j];
			}

		}
		// �Ŵ��������10������
		
		
			for (int j = GeneticNode; j < 30; j++) {
				Random ran = new Random();
				int ran1 = ran.nextInt(VariableLength);// �ҵ��ֽ��
				int gene1 = ran.nextInt(VariableLength);
				int gene2 = ran.nextInt(VariableLength);// �ҵ�����ĸ����
				// �»����ǰ���ȡgene1��ǰ���
				for (int k = 0; k < ran1; k++) {
					geneticgene[j][k] = geneticgene[gene1][k];
				}
				// �»���ĺ���ȡgene2�ĺ���
				for (int m = ran1; m < VariableLength; m++) {
					geneticgene[j][m] = geneticgene[gene2][m];
				}
			}
		
		return geneticgene;
	}

	/*
	 * test
	 */
//	public static void main(String args[]) {
//		Gene gg = new Gene();
//		int[][] test = gg.gene();
//		int[][] test1 = { { 80, 0 }, { 79, 1 }, { 78, 2 }, { 77, 3 },
//				{ 76, 4 }, { 75, 5 }, { 74, 6 }, { 73, 7 }, { 72, 8 },
//				{ 71, 9 }, { 70, 10 }, { 69, 11 }, { 68, 12 }, { 67, 13 },
//				{ 66, 14 }, { 65, 15 }, { 64, 16 }, { 62, 18 }, { 61, 19 },
//				{ 59, 20 }, { 58, 21 }, { 57, 22 }, { 56, 23 }, { 55, 24 },
//				{ 54, 25 }, { 53, 26 }, { 51, 27 }, { 50, 28 }, { 49, 29 }, };
//		Genetic tt = new Genetic();
//		int[][] out = tt.geneticGene(test, test1);
//		for (int i = 0; i < 30; i++) {
//			for (int j = 0; j < 20; j++) {
//				System.out.print(out[i][j]);
//			}
//			System.out.println();
//		}
//	}

}