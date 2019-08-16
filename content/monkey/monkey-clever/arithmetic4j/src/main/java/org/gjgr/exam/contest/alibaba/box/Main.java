package org.gjgr.exam.contest.alibaba.box;

import org.gjgr.exam.tools.InputOutObserver;

import java.io.InputStream;
import java.util.*;

/**
 * File Name : arithmetic4j - edu.gjgr.exam
 * CopyRright (c) 1949-xxxx:
 * File Number：
 * Author：gwd
 * Date：on 8/12/2017
 * Modify：gwd
 * Time ：
 * Comment：
 * Description：
 * Version：
 */
public class Main {

    /** 请完成下面这个process函数，实现题目要求的功能 **/
    /** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^  **/
    private static Integer CUSTOMS_LIMIT_MONEY_PER_BOX=2000;
    private static Model boxTemplate=new Model();
    private static Integer  boxMinNum=0;
    private static int process(Model[] items){
        //按照所有的盒子排序，按照盒子从大到小进行放入
        //如果不足，这调用get方法，添加box数量
        //每次排序从已经的box列表中寻找最小的box，装入

        //构成动态规划表


        return 0;
    }



    public static void get(){
        Model item=new Model();
        item.setPrice(boxTemplate.getPrice());
        item.setLength(boxTemplate.getPrice());
        item.setWidth(boxTemplate.getWidth());
        item.setHeight(boxTemplate.getHeight());
        boxMinNum++;
    }
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);

       // boxTemplate.setPrice(CUSTOMS_LIMIT_MONEY_PER_BOX);
        boxTemplate.setPrice(CUSTOMS_LIMIT_MONEY_PER_BOX);
        Model[] items;
        while (scanner.hasNext()){
            boxTemplate.setLength(scanner.nextInt());
            boxTemplate.setWidth( scanner.nextInt());
            boxTemplate.setHeight(scanner.nextInt());
            int itemNum = scanner.nextInt();
            items = new Model[itemNum];
            for(int i=0; i<itemNum; i++){
                Model item = new Model();
                item.setPrice( scanner.nextInt());
                item.setLength(scanner.nextInt());
                item.setWidth( scanner.nextInt());
                item.setHeight(scanner.nextInt());
                items[i] = item;
            }
            long startTime = System.currentTimeMillis();
            Integer boxMinNum = Integer.MAX_VALUE;
            Arrays.sort(items);
            List<Model> list=Arrays.asList(items);

            System.out.println (process(items));
        }
    }
/*    public static void main(String[] args){
        InputStream inputStream=System.in;
        inputStream= InputOutObserver.getLocalInputFileStream(BracketMatching.class);
        Scanner scanner = new Scanner(inputStream);


    }*/
}
class Model implements Comparable<Model>{
    private Integer price;
    private Integer length;
    private Integer width;
    private Integer height;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }


    public int compareTo(Model o) {
        if(o.getHeight()<this.height||o.getPrice()<this.height||o.getLength()<=this.getLength()||o.getWidth()<=this.getWidth()){
            return -1;
        }else{
            return 1;
        }
    }
}