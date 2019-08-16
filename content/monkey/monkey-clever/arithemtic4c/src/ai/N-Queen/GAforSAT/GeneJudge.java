package GAforSAT;
/*
 * ��ÿ������ָ�ɣ�ȥ�ж�ÿ���Ӿ�.done
 * ���ÿ���������Ӧ�Դ�С��������
 */
public class GeneJudge {
	public static final int GeneLength = 30;// ָ�ɵĸ�����Ҳ����ָ�ɼ�����ĳ���
	public static final int ClauseLength = 3;// �Ӿ������ֵĸ�����3-SAT�̶�Ϊ3
	public int[] geneJudge(int[][] gene, int[][] cluseclump) {
		int[] GeneJudge = new int[GeneLength];// ������ֵ��
		int[] cluse = new int[ClauseLength];// �Ӿ�����
		int[] temp = new int[ClauseLength];// ������ֵ��
		// �Ӿ�������
		for (int i = 0; i < cluseclump.length; i++) {
			// �Ӿ������
			for (int j = 0; j < cluseclump[0].length; j++) {
				cluse[j] = cluseclump[i][j];// �����Ӿ�

			}
			// ָ��������
			for (int k = 0; k < gene.length; k++) {
				for (int m = 0; m < ClauseLength; m++) {
					if (cluse[m] < 0) {
						temp[m] =1^gene[k][-cluse[m]];//��1ȡ�����0��ֵΪ1����1��ֵΪ0
					} else
						temp[m] = gene[k][cluse[m]];

				}
				int tem = temp[0] | temp[1] | temp[2];
				if (tem != 0) {
					GeneJudge[k]++;
				}
			}

		}
		return GeneJudge;
	}

	/*
	 * test
	 */
//	public static void main(String args[]) {
//		Gene test = new Gene();
//		int[][] test1 = test.gene();
//		for(int i=0;i<30;i++){
//			for(int j=0;j<20;j++){
//				System.out.print(test1[i][j]);
//				
//			}
//			System.out.println();
//		}
//		ClauseClump arr=new ClauseClump();
//		int[][]arrr=arr.getArray();
//		for(int i=0;i<100;i++){
//			for(int j=0;j<3;j++){
//				System.out.print(arrr[i][j]+"\t");
//			}
//			System.out.println();
//		}
//		GeneJudge test4 = new GeneJudge();
//		int[] tests = test4.geneJudge(test1, arrr);
//		for (int i = 0; i < tests.length; i++) {
//			System.out.println(tests[i]);
//		}
//		
//	}

}