package GAforSAT;
/*
 * 用每条基因（指派）去判断每条子句.done
 * 获得每条基因的适应性大小存入数组
 */
public class GeneJudge {
	public static final int GeneLength = 30;// 指派的个数，也就是指派集数组的长度
	public static final int ClauseLength = 3;// 子句中文字的个数，3-SAT固定为3
	public int[] geneJudge(int[][] gene, int[][] cluseclump) {
		int[] GeneJudge = new int[GeneLength];// 最终真值表
		int[] cluse = new int[ClauseLength];// 子句文字
		int[] temp = new int[ClauseLength];// 文字真值表
		// 子句纵坐标
		for (int i = 0; i < cluseclump.length; i++) {
			// 子句横坐标
			for (int j = 0; j < cluseclump[0].length; j++) {
				cluse[j] = cluseclump[i][j];// 单个子句

			}
			// 指派纵坐标
			for (int k = 0; k < gene.length; k++) {
				for (int m = 0; m < ClauseLength; m++) {
					if (cluse[m] < 0) {
						temp[m] =1^gene[k][-cluse[m]];//和1取异或，是0则值为1，是1则值为0
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