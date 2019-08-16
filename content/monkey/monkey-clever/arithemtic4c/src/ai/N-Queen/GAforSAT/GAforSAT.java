package GAforSAT;

import java.util.*;

/*
 * @main
 * 主函数done
 * 20100504
 */
public class GAforSAT {
	public static final int VariableLength = 20;// 变元集变元的长度,也就是基因的长度

	public static final int ClauseClumpLength = 100;// 子句集的长度

	public static final int GeneticNode = 25;// 指派变异时保留的指派数

	public static final int Generation = 100000;// 遗传上限100000代

	public static final int ClauseLength = 3;// 子句中文字的个数，3-SAT固定为3

	public static final int GeneLength = 30;// 指派的个数，也就是指派集数组的长度

	public static final int Accuracy = 1;// 指派满足子句集的精确度1-5

	public static final double Variation = 0.01;// 变异率,仅用作说明

	public static void main(String args[]) {

		long begin = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		long cost = end - begin;

		int[][] clause = new int[ClauseClumpLength][ClauseLength];
		System.out.println("随机生成子句集请输入0");
		System.out.println("使用默认子句集请输入1");
		Scanner scan = new Scanner(System.in);
		String select = scan.next();
		/*
		 * 随机生成子句集
		 */
		if (select.equals("0")) {
			ClauseClump clauseclump = new ClauseClump();
			clause = clauseclump.getArray();// 随机生成子句clause
			System.out.println("子句是：");
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
		 * 使用已知子句集
		 */
		if (select.equals("1")) {
			Test testarr = new Test();
			clause = testarr.test();// 已知测试子句集clause
		}

		Gene genelist = new Gene();
		int[][] gene = genelist.gene();// 生成指派

		for (int i = 0; i < Generation; i++) {
			if (i % 1000 == 0) {
				System.out.println("第" + i + "代未找到");
			}
			GeneJudge geneJudge = new GeneJudge();
			int[] judgeresult = geneJudge.geneJudge(gene, clause);// 生成指派真值也就是适应度判断testORclause
			for (int j = 0; j < GeneLength; j++) {
				if (judgeresult[j] == ClauseClumpLength) {
					System.out.println("第" + i + "代的第" + j + "条指派是可满足的：");
					for (int k = 0; k < VariableLength; k++) {
						System.out.print(gene[j][k]);
					}
					System.out.println();
					end = System.currentTimeMillis();
					cost = end - begin;
					System.out.println("耗时" + cost + "毫秒");
					return;
				}
			}

			if (i < Generation) {

				Suitability suitable = new Suitability();
				int[][] suitresult = suitable.suitablelist(judgeresult);// 指派排序
				Genetic genet = new Genetic();
				int[][] generesult = genet.geneticGene(gene, suitresult);// 指派遗传
				Variation variation = new Variation();
				int[][] varresult = variation.variation(generesult);// 变异
				for (int m = 0; m < GeneLength; m++) {
					for (int n = 0; n < VariableLength; n++) {
						gene[m][n] = varresult[m][n];
					}
				}

			} else {
				System.out.println("该问题在十万代之内不可满足");
				end = System.currentTimeMillis();
				cost = end - begin;
				System.out.println("耗时" + cost + "毫秒");
			}
		}
	}
}