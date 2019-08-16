package edu.muc.jxd.cluster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.muc.core.distance.DistenceInter;
import edu.muc.gwd.item.ImageItemVector;
import edu.muc.jxd.vo.Delta;
import edu.muc.jxd.vo.P;
import edu.muc.jxd.vo.Result;

public class Cluster {

    private List<ImageItemVector<Number>> itemList;

    private List<Result> resultList;

    /**
     * 用来计算小兵的肉
     */
    private P p;

    /**
     * 用来计算帮主之间距离的德尔塔。
     */
    private Delta delta;

    /**
     * 构造函数
     *
     * @param items
     */
    public Cluster(List<ImageItemVector<Number>> items, DistenceInter distance, double start, double end, double step) {
        this.itemList = items;
        this.resultList = new ArrayList<Result>();
        /*
		 * 初始化 p和Delta。
		 */
        this.p = new P(items.size(), items, distance, start, end, step);
        this.delta = new Delta(items.size(), this.p);
        this.initIdList();
    }

    /**
     * 獲得list，此List中保存了结果。
     */
    public void initIdList() {
        for (int i = 0; i < this.itemList.size(); i++) {
            ImageItemVector<Number> aItemVector = this.itemList.get(i);
            int id = aItemVector.getId();
            int p = this.p.getP()[i];
            int delta = this.getDelta().getI(i);
            Result result = new Result(id, p, delta);
            this.resultList.add(result);
        }
    }

    public void printResult() {
        Collections.sort(this.resultList);
        StringBuilder builder = new StringBuilder();
        for (Result result : this.resultList) {
            builder.append(result.getId() + " : " + "p: " + result.getP() + " D: " + result.getDelta() + " "
                    + result.getMutiplyResult() + "\n");
        }
        System.out.println(builder.toString());
    }

    public List<ImageItemVector<Number>> getItemList() {
        return itemList;
    }

    public void setItemList(List<ImageItemVector<Number>> itemList) {
        this.itemList = itemList;
    }

    public P getP() {
        return p;
    }

    public void setP(P p) {
        this.p = p;
    }

    public Delta getDelta() {
        return delta;
    }

    public void setDelta(Delta delta) {
        this.delta = delta;
    }

}
