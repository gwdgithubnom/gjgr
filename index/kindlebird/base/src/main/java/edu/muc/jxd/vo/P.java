
package edu.muc.jxd.vo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.muc.core.distance.DistenceInter;
import edu.muc.gwd.item.ImageItemVector;

public class P {

    private int zeroCount = 0;

    /**
     * 最大的距离
     */

    private int maxDistance;

    /**
     * 點的个数
     */

    private int numberOfPoints;

    /**
     * 使用pv进行排序
     */
    private List<PDelta> pDeltaList = new ArrayList<>();

    private List<Length> clustersLength = new ArrayList<>();

    /**
     * 数据的引用
     */
    private List<ImageItemVector<Number>> itemList;

    /**
     * 计算距离的函数
     */
    private DistenceInter distence;

    /**
     * 用来存储p的数组
     */

    private int[] p;

    private HashMap<Integer, List<Number>> pDetail;

    /**
     * 距离
     */

    private double dc;

    /**
     * 为了计算这个dc，要遍历很多个dc，要将每次遍历结果存储于一个List,最终取list中的最优值。
     */

    private List<Entropy> list4Dc;

    /**
     * 距离矩阵
     */
    private int[][] distanceMatrix;

    private Delta delta;

    /**
     * *************************************************************************
     * *************************************** 构造方法
     *
     * @param length
     * @param itemList
     * @param distence
     */

    public P(int length, List<ImageItemVector<Number>> itemList, DistenceInter distence, double start, double end,
             double step) {
        this.distanceMatrix = new int[length][length];
        this.p = new int[length];
        this.numberOfPoints = this.p.length;
        this.delta = new Delta(numberOfPoints, this);
        this.pDetail = new HashMap<>();
        this.itemList = itemList;
        this.distence = distence;
        this.list4Dc = new ArrayList<Entropy>();
        this.optimizeDc(start, end, step);
    }

    public void optimizeDc(double start, double max, double step) {
        this.initP();
        if (max < 0) {
            max = this.maxDistance + 10;
        }

        // 条件要改,
        for (double i = start; i <= max; i = i + step) {
            this.dc = i;
            System.out.println("dc = " + dc);
            // 重新计算p和d
            this.computeP();
            this.delta.setP(this);
            this.delta.initDelta();
            // 开始计算熵

            double H = getH4Dc();

            Entropy e = new Entropy(this.dc, H);
            System.out.println(" H = " + H);
            this.list4Dc.add(e);
            if (this.dc % 10 == 0) {
                this.printEntropy();
            }
            if (H == 0.0 || H == -0.0) {
                this.zeroCount++;
                if (this.zeroCount == 100) {
                    break;
                }
            } else {
                this.zeroCount = 0;
            }
        }

		/*
         * 取熵最小的dc值作为最终的dc值。
		 */
        Collections.sort(this.list4Dc);
        Entropy entropy = this.list4Dc.get(0);
        this.dc = entropy.dc;
        this.initP();
    }

    public void initPDeltaList() {
		/*
		 * 初始化pv
		 */
        for (int i = 0; i < this.numberOfPoints; i++) {
            PDelta aPDelta = new PDelta(i, this.p[i], this.delta.getValue()[i]);
            this.pDeltaList.add(aPDelta);

        }

        Collections.sort(this.pDeltaList);

        // 初始化完成,飯轉
        List<PDelta> reversePD = new ArrayList<>();
        for (int i = 0; i < this.pDeltaList.size(); i++) {
            reversePD.add(this.pDeltaList.get(this.pDeltaList.size() - i - 1));
        }
        this.pDeltaList = reversePD;
    }

    /**
     * @param allPointsSet  已经处理过的点
     * @param resultList    影响的点
     * @param processedKeys 已经处理的key
     * @param id            当前点id
     * @param length        处理半径
     */
    public void spreadPoints(Set<Number> allPointsSet, Set<Integer> resultList, Set<Integer> processedKeys,
                             Integer fatherID, Integer id, int length) {

        if (!processedKeys.contains(id)) {
            processedKeys.add(id);
            List<Number> list = this.getpDetail().get(id);
            if (list.size() > 0) {
                for (Number key : list) {
                    if (!allPointsSet.contains(key)) {
                        // 没有处理过
                        resultList.add((Integer) key);
                        if (this.getP()[key.intValue()] > 0
                                // && this.getP()[key.intValue()] >=
                                // this.getP()[fatherID.intValue()]) {
                                && this.judgeSpreading(key.intValue(), fatherID.intValue())) {
                            // 如果其內部包含的点的个数大于其父点个数的比例，开始传播
                            this.spreadPoints(allPointsSet, resultList, processedKeys, id, key.intValue(), length);
                        }
                    }
                }
            }
        }

    }

    public boolean judgeSpreading(int fatherID, int childID) {
        List<Number> fatherList = new ArrayList<>();
        List<Number> childList = new ArrayList<>();
        fatherList.addAll(this.getpDetail().get(fatherID));
        childList.addAll(this.getpDetail().get(childID));
        if (fatherList != null && fatherList.size() > 0 && childList != null && childList.size() > 0) {
            // 计算这里面有多少个点相交
            fatherList.retainAll(childList);
            if (fatherList.size() > 3) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    public void getLength(HashMap<Integer, List<Number>> allPoints) {
        this.clustersLength = new ArrayList<>();
        for (Integer key : allPoints.keySet()) {
            Length length = new Length(key, allPoints.get(key).size());
            this.clustersLength.add(length);
        }

        Collections.sort(this.clustersLength);

    }

    public double getH4Dc() {

        this.initPDeltaList();

        // 已经处理过的点
        Set<Number> overSet = new HashSet<Number>();

        // 用作结果
        HashMap<Integer, List<Number>> allPoints = new HashMap<>();

        for (int i = 0; i < this.numberOfPoints; i++) {
            List<Number> aList = new ArrayList<>();
            aList.add(i);
            allPoints.put(i, aList);
        }

        while (overSet.size() < numberOfPoints) {

            // 将下一个点加入
            PDelta maxP = this.pDeltaList.get(0);

            int id = maxP.getID();

            // 已经影响的不算

            // 被影响的点
            Set<Integer> effectedList = new HashSet<>();
            // 处理过的ID
            Set<Integer> processedKeys = new HashSet<>();

            this.spreadPoints(overSet, effectedList, processedKeys, id, id, maxP.getP());

            for (Integer aID : effectedList) {
                if (allPoints.get(aID) != null && allPoints.get(aID).size() == 1
                        && allPoints.get(aID).get(0).intValue() == aID) {
                    allPoints.remove(aID);
                    overSet.remove(aID);
                }
            }

            if (effectedList.size() > 0) {
                // 已经处理的点
                overSet.addAll(effectedList);
                overSet.add(id);
                // 结果
                int temp = -1;
                List<Number> heheList = new ArrayList<>();

                for (Integer integer : effectedList) {
                    heheList.add(integer);
                    // 清理排序结果
                    for (int i = 0; i < this.pDeltaList.size(); i++) {
                        if (integer == this.pDeltaList.get(i).getID().intValue()) {
                            temp = i;
                        }
                    }
                    if (temp != -1) {
                        this.pDeltaList.remove(temp);
                    }

                }

                allPoints.put(id, heheList);

            }

            // 将待处理中的点删除
            Collections.sort(this.pDeltaList);
            // 初始化完成,飯轉
            List<PDelta> reversePD = new ArrayList<>();
            for (int i = 0; i < this.pDeltaList.size(); i++) {
                reversePD.add(this.pDeltaList.get(this.pDeltaList.size() - i - 1));
            }
            this.pDeltaList = reversePD;

        }

		/*
		 * 开始计算熵
		 */
        double H = 0.0;

        double sum = 0.0;

        for (Integer key : allPoints.keySet()) {
            sum = sum + allPoints.get(key).size();
        }
        for (Integer aKey : allPoints.keySet()) {
            int numberOfKey = allPoints.get(aKey).size();
            double gailv = numberOfKey / sum;
            H = H + (gailv * Math.log(gailv));
        }

        H = -H;

        if ((int) this.dc % 10 == 0) {
            this.printAllPoints(allPoints, "AllPoints-------------------------dc=" + this.dc + " H" + H);
            this.printEntropy();
        }
        this.printAllPoints(allPoints, "AllPoints-------------------------dc=" + this.dc + " H" + H);

        this.getLength(allPoints);
        return H;

    }

    /**
     * *************************************************************************
     * *************************************** 初始化P，包括初始化距离矩阵和p
     */

	/*
	 * 获取最大的距离
	 */
    public void getMax() {
        this.maxDistance = 0;
        for (int i = 0; i < this.distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[i].length; j++) {
                if (distanceMatrix[i][j] > this.maxDistance) {
                    this.maxDistance = this.distanceMatrix[i][j];
                }
            }
        }
    }

    public void initP() {
        this.computeDistanceMatrix();
        this.getMax();
        // this.printDistanceMatrix();
        System.out.println("最大的距离：" + this.maxDistance);
        this.computeP();
        // this.printP();
    }

    /**
     * 將距离矩阵输出
     */

    public void printDistanceMatrix() {
        StringBuilder builder = new StringBuilder();
        builder.append("DistanceMatrix：\n");
        for (int i = 0; i < this.distanceMatrix.length; i++) {
            for (int j = 0; j < this.distanceMatrix[i].length; j++) {
                builder.append(this.distanceMatrix[i][j] + " ");
            }
            builder.append("\n");
        }
        System.out.println(builder.toString());
    }

    /**
     * 输出p
     */
    public void printP() {
        StringBuilder builder = new StringBuilder();
        builder.append("p：\n");
        for (int i = 0; i < this.p.length; i++) {
            builder.append(this.p[i] + " ");
        }
        builder.append("\n");
        System.out.println(builder.toString());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("p：\n");
        for (int i = 0; i < this.p.length; i++) {
            builder.append(this.p[i] + " ");
        }
        builder.append("\n");
        return builder.toString();
    }

    /**
     * 计算Distance
     */
    public void computeDistanceMatrix() {
		/*
		 * 计算Distance
		 */
        for (int i = 0; i < this.itemList.size(); i++) {
            for (int j = i + 1; j < this.itemList.size(); j++) {
                int dis = diff(i, j);
                this.setValue(i, j, dis);
                this.setValue(j, i, dis);
            }
            // 设置对角线
            this.setValue(i, i, 0);
        }
    }

    /**
     * 通过Distance来计算p
     */
    public void computeP() {
		/*
		 * 计算p，如果i与j的距离小于dc，则Pi+1
		 */
        this.pDetail = new HashMap<>();
        for (int i = 0; i < distanceMatrix.length; i++) {
            int aP = 0;
            List<Number> pointList = new ArrayList<Number>();
            for (int j = 0; j < distanceMatrix[i].length; j++) {
                if (distanceMatrix[i][j] < this.dc) {
                    aP++;
                    pointList.add(j);
                }
            }
            this.p[i] = aP;
            this.pDetail.put(i, pointList);
        }
        // p计算完成。
    }

    /**
     * *************************************************************************
     * *************************************** 计算从Itemi到Itemj之间的距离.
     */
    public int diff(int i, int j) {
        //System.out.println(itemList.get(i));
        int dis = this.distence.getDistence(itemList.get(i), itemList.get(j));
        //System.out.println(dis);
        // logger.debug("disstance between "+i+" and "+j +" is "+dis);
        return dis;
    }

    /**
     * *************************************************************************
     * ***************************************
     *
     * @param i
     * @param j
     * @return
     */
    public int get(int i, int j) {
        if (i >= distanceMatrix.length || j >= distanceMatrix[i].length) {
            return -1;
        } else {
            return this.distanceMatrix[i][j];
        }
    }

    public void setValue(int i, int j, int value) {
        if (i >= this.distanceMatrix.length || j >= this.distanceMatrix[i].length || i < 0 || j < 0) {
            return;
        } else {
            this.distanceMatrix[i][j] = value;
        }
    }

    /**
     * *************************************************************************
     * *************************************** write to file
     */

    public void writetoFile(File fileName) {

        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("dc :" + this.dc + "\n");
            // writer.write("entropy: " +
            // Arrays.toString(this.getList4Dc().toArray()) + "\n");
            writer.write("p :" + Arrays.toString(this.getP()) + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * *************************************************************************
     * *************************************** getters and setters
     *
     * @return
     */
    public int[] getP() {
        return p;
    }

    public void setP(int[] p) {
        this.p = p;
    }

    public int[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public void setDistanceMatrix(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    public double getDc() {
        return dc;
    }

    public void setDc(double dc) {
        this.dc = dc;
    }

    public List<Entropy> getList4Dc() {
        return list4Dc;
    }

    public void setList4Dc(List<Entropy> list4Dc) {
        this.list4Dc = list4Dc;
    }

    public HashMap<Integer, List<Number>> getpDetail() {
        return pDetail;
    }

    public void setpDetail(HashMap<Integer, List<Number>> pDetail) {
        this.pDetail = pDetail;
    }

    public void printAllPoints(HashMap<Integer, List<Number>> points, String title) {
		/*
		 * 当前要处理的List，大小为window
		 */

        System.out.println(title + ":");
        for (Integer key : points.keySet()) {
            List<Number> list = points.get(key);


            System.out.print(" " + this.itemList.get(key).getId() + ": ");
            for (Number number : list) {
                System.out.print(this.itemList.get(number.intValue()).getId() + " ");
            }
            System.out.println();
        }

    }

    public void printEntropy() {
        System.out.println("Result---------------------------------------------------");
        for (Entropy entropy : this.list4Dc) {
            System.out.println(entropy);
        }
        System.out.println("Length:==================================================");
        for (Length length : clustersLength) {
            System.out.println(length);
        }
    }
}
