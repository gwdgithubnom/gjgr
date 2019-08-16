package org.gjgr.arithmetic.classic.ga;

import java.util.*;
/*
 * 获得20条基础的基因指派序列.doneorchange
 * 20100430
 */
public class Gene {
	public static final int VariableLength = 20;// 变元集变元的长度,也就是基因的长度
	public static final int GeneLength = 30;// 指派的个数，也就是指派集数组的长度
	/*
	 * @初步获得基因序列 @30条20位的二进制数
	 * @基因重复和取反的优化以后再做
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
