package GAforSAT;
import java.util.*;
/*
 * ����Ӿ伯
 * �������.done
 * �ⲿ���ݵ���.todo
 * 20100430
 */

public class ClauseClump {
	
	public int[][] array = new int[100][3];

	public int[][] getArray() {
		setArray(getS(getDaShus()));
		return array;
	}

	public void setArray(int[][] array) {
		this.array = array;
	}

	public int[][] getS(int[][] b) {
		int[][] a = new int[100][3];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = b[i][j];
			}
		}
		return a;
	}

	/**
	 * ���С����
	 * 
	 * @return
	 */
	public static int[] getShus() {
		int z = 0;
		int[] s = new int[4];
		int a = 0;
		for (int i = 0;; i++) {
			if (a == 3) {
				break;
			}
			z = getShu();
			if (a == 0) {
				s[a] = z;
				a++;
			} else if (a == 1 || a == 2) {
				if (!panShu(s, z)) {
					s[a] = z;
					a++;
				} else {
					continue;
				}
			}
		}
		s[3] = s[0] + s[1] + s[2];
		// System.out.println(s[0] + "--" + s[1] + "--" + s[2] + "--" + s[3]);
		return s;
	}

	/**
	 * �ж�һ�������Ƿ��ظ������෴��
	 * 
	 * @param s
	 * @param z
	 * @return
	 */
	public static boolean panShu(int[] s, int z) {
		for (int j = 0; j < s.length; j++) {
			if (s[j] == z) {
				return true;
			}
			if (s[j] + z == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ��ȡ��20��-20������
	 * 
	 * @return
	 */
	public static int getShu() {
		Random r = new Random();
		int z = 0;
		if (r.nextBoolean()) {
			z = r.nextInt(20) * (-1);
		} else {
			z = r.nextInt(20);
		}
		return z;
	}

	/**
	 * ��ȡ2ά����
	 * 
	 * @param s
	 * @return
	 */
	public static int[][] getDaShus() {
		int[][] ss = new int[100][4];
		int a = 0;
		int[] s = new int[4];
		for (int i = 0;; i++) {
			s = getShus();
			if (a == 0) {
				ss[a][0] = s[0];
				ss[a][1] = s[1];
				ss[a][2] = s[2];
				ss[a][3] = s[3];
				a++;
			} else if (a == 100) {
				break;
			} else {
				if (!panDaShus(ss, s, a)) {
					ss[a][0] = s[0];
					ss[a][1] = s[1];
					ss[a][2] = s[2];
					ss[a][3] = s[3];
					a++;
				} else {
					continue;
				}
			}
		}
		return ss;
	}

	/**
	 * �ж�С�������Ϊ0�������ظ�
	 * 
	 * @param ss
	 * @param s
	 * @param a
	 * @return
	 */
	public static boolean panDaShus(int[][] ss, int[] s, int a) {
		for (int i = 0; i <= a; i++) {
			if (ss[i][3] + s[3] == 0) {
				return true;
			} else if (panDaShusr(ss, s, i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * �ж�С�����Ƿ��ظ�
	 * 
	 * @param ss
	 * @param s
	 * @param i
	 * @return
	 */
	public static boolean panDaShusr(int[][] ss, int[] s, int i) {
		if (panDaShusn(ss, s, i, 1) && panDaShusn(ss, s, i, 2)
				&& panDaShusn(ss, s, i, 3)) {
			return true;
		}
		return false;
	}

	/**
	 * ѭ���ж�С����ÿ�����Ƿ��ظ�
	 * 
	 * @param ss
	 * @param s
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean panDaShusn(int[][] ss, int[] s, int a, int b) {
		for (int i = 0; i < 3; i++) {
			if (ss[a][i] == s[b]) {
				return true;
			}
		}
		return false;
	}

}
