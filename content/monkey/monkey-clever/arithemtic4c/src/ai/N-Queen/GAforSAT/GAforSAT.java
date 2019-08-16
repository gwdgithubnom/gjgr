package GAforSAT;

import java.util.*;

/*
 * @main
 * ������done
 * 20100504
 */
public class GAforSAT {
	public static final int VariableLength = 20;// ��Ԫ����Ԫ�ĳ���,Ҳ���ǻ���ĳ���

	public static final int ClauseClumpLength = 100;// �Ӿ伯�ĳ���

	public static final int GeneticNode = 25;// ָ�ɱ���ʱ������ָ����

	public static final int Generation = 100000;// �Ŵ�����100000��

	public static final int ClauseLength = 3;// �Ӿ������ֵĸ�����3-SAT�̶�Ϊ3

	public static final int GeneLength = 30;// ָ�ɵĸ�����Ҳ����ָ�ɼ�����ĳ���

	public static final int Accuracy = 1;// ָ�������Ӿ伯�ľ�ȷ��1-5

	public static final double Variation = 0.01;// ������,������˵��

	public static void main(String args[]) {

		long begin = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		long cost = end - begin;

		int[][] clause = new int[ClauseClumpLength][ClauseLength];
		System.out.println("��������Ӿ伯������0");
		System.out.println("ʹ��Ĭ���Ӿ伯������1");
		Scanner scan = new Scanner(System.in);
		String select = scan.next();
		/*
		 * ��������Ӿ伯
		 */
		if (select.equals("0")) {
			ClauseClump clauseclump = new ClauseClump();
			clause = clauseclump.getArray();// ��������Ӿ�clause
			System.out.println("�Ӿ��ǣ�");
			for (int i = 0; i < ClauseClumpLength; i++) {
				System.out.print("{");
				for (int j = 0; j < ClauseLength; j++) {

					System.out.print(clause[i][j] + ",");
				}
				System.out.print("},");
				System.out.println();
			}
		}
		/*
		 * ʹ����֪�Ӿ伯
		 */
		if (select.equals("1")) {
			Test testarr = new Test();
			clause = testarr.test();// ��֪�����Ӿ伯clause
		}

		Gene genelist = new Gene();
		int[][] gene = genelist.gene();// ����ָ��

		for (int i = 0; i < Generation; i++) {
			if (i % 1000 == 0) {
				System.out.println("��" + i + "��δ�ҵ�");
			}
			GeneJudge geneJudge = new GeneJudge();
			int[] judgeresult = geneJudge.geneJudge(gene, clause);// ����ָ����ֵҲ������Ӧ���ж�testORclause
			for (int j = 0; j < GeneLength; j++) {
				if (judgeresult[j] == ClauseClumpLength) {
					System.out.println("��" + i + "���ĵ�" + j + "��ָ���ǿ�����ģ�");
					for (int k = 0; k < VariableLength; k++) {
						System.out.print(gene[j][k]);
					}
					System.out.println();
					end = System.currentTimeMillis();
					cost = end - begin;
					System.out.println("��ʱ" + cost + "����");
					return;
				}
			}

			if (i < Generation) {

				Suitability suitable = new Suitability();
				int[][] suitresult = suitable.suitablelist(judgeresult);// ָ������
				Genetic genet = new Genetic();
				int[][] generesult = genet.geneticGene(gene, suitresult);// ָ���Ŵ�
				Variation variation = new Variation();
				int[][] varresult = variation.variation(generesult);// ����
				for (int m = 0; m < GeneLength; m++) {
					for (int n = 0; n < VariableLength; n++) {
						gene[m][n] = varresult[m][n];
					}
				}

			} else {
				System.out.println("��������ʮ���֮�ڲ�������");
				end = System.currentTimeMillis();
				cost = end - begin;
				System.out.println("��ʱ" + cost + "����");
			}
		}
	}
}