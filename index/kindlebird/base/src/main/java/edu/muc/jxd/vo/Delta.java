package edu.muc.jxd.vo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Delta {

    private Set<Integer> iDList = new HashSet<Integer>();

    private P p;

    private int[] value;

    public Delta(int length, P p) {
        this.value = new int[length];
        this.p = p;
        this.initDelta();
        System.out.println("IDList:" + this.iDList.toString());
    }

    public void initDelta() {
        for (int i = 0; i < this.value.length; i++) {
            int pValue = this.p.getP()[i];
            List<Integer> Ps = new ArrayList<>();
            /*
			 * 求pi < pj
			 */
            for (int j = 0; j < this.p.getP().length; j++) {
                if (pValue <= this.p.getP()[j]) {
                    // 如果是同一个值，跳过，不然會是0
                    if (i == j) {
                        continue;
                    } else {
                        Ps.add(this.p.getDistanceMatrix()[i][j]);
                    }
                }
            }
			/*
			 * 求Min(pj)
			 */
            if (Ps.size() > 1) {
                Collections.sort(Ps);
                // System.out.println(Ps.toString());
                this.value[i] = Ps.get(0);
            } else {
                // 記錄下i，以後再算
                iDList.add(i);
            }
        }

        //算最大的两个值
        System.out.println("idList:" + this.iDList.toString());
        boolean flag = true;
        int maxIndex = 0;
        int maxValue = this.value[0];
        for (int i = 0; i < this.value.length; i++) {
            if (this.value[i] > maxValue) {
                maxValue = this.value[i];
            }
        }
        for (Integer integer : iDList) {
            if (flag) {
                maxIndex = integer;
                this.value[integer] = maxValue;
                flag = false;
            } else {
                this.value[integer] = this.p.getDistanceMatrix()[integer][maxIndex];
            }
        }

    }

    public int getI(int i) {
        if (i >= 0 && i < this.value.length) {
            return this.value[i];
        } else {
            return -1;
        }
    }

    public P getP() {
        return p;
    }

    public void setP(P p) {
        this.p = p;
    }

    public int[] getValue() {
        return value;
    }

    public void setValue(int[] value) {
        this.value = value;
    }

    /**
     * *************************************************************************
     * *************************************** write to file
     */

    public void writetoFile(File fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("value :" + Arrays.toString(this.getValue()) + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        StringBuilder builder = new StringBuilder();
        builder.append("Delta:\n");
        for (int i : value) {
            builder.append(i).append(" ");
        }
        builder.append("\n");
        return builder.toString();
    }

}
