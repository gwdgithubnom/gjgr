package org.gjgr.arithmetic.classic.ga;

import java.util.*;

/*
 * 遗传.done
 * @liu
 * 对原始基因进行遗传处理获得新的更优指派
 * 30条基础指派去掉10条
 * 由其他25条遗传获得10条
 * 20100503
 */

public class Genetic {

	public static final int VariableLength = 20;// 变元集变元的长度,也就是基因的长度
	public static final int GeneticNode = 25;// 指派变异时保留的指派数
	public static final int GeneLength = 30;// 指派的个数，也就是指派集数组的长度
	/*
	 * 获得经过适应性排序后适应性最高的25个基因 并通过遗传获得新的10条基因
	 */
	public int[][] geneticGene(int[][] Gene, int[][] suitable) {
		int[][] geneticgene = new int[GeneLength][VariableLength];
		// 取出适应性高的20条基因
		for (int i = 0; i < GeneticNode; i++) {
			for (int j = 0; j < VariableLength; j++) {
				geneticgene[i][j] = Gene[suitable[i][1]][j];
			}

		}
		// 遗传获得另外10条基因
		
		
			for (int j = GeneticNode; j < 30; j++) {
				Random ran = new Random();
				int ran1 = ran.nextInt(VariableLength);// 找到分结点
				int gene1 = ran.nextInt(VariableLength);
				int gene2 = ran.nextInt(VariableLength);// 找到两条母基因
				// 新基因的前半段取gene1的前半段
				for (int k = 0; k < ran1; k++) {
					geneticgene[j][k] = geneticgene[gene1][k];
				}
				// 新基因的后半段取gene2的后半段
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