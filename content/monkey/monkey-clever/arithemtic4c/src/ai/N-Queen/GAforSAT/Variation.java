package GAforSAT;

import java.util.*;

/*
 * 变异
 * 30*20的数组里随机找一位取反
 * 变异率：1%
 * 20100503
 */
public class Variation {
	public static final double Variation = 0.01;// 变异率
	public static final int VariableLength = 20;// 变元集变元的长度,也就是基因的长度
	
	public int[][] variation(int[][] geneticgene) {
		Random ran = new Random();
		int total = geneticgene[0].length * geneticgene.length;//数组容量
		//变异
		for (int i = 0; i < total * Variation; i++) {
			int element = ran.nextInt(total);// 获得数组容量之内的随机值
			//System.out.println(element);
			geneticgene[element / VariableLength][element % VariableLength] = 1^geneticgene[element / VariableLength][element % VariableLength];// 按位取反
		}
		return geneticgene;
	}

	/*
	 * test
	 * 毛，不测了 爱咋咋地
	 */

}