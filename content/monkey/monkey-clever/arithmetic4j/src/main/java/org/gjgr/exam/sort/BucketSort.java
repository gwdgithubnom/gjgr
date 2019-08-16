package org.gjgr.exam.sort;

/**
 * File Name : arithmetic4j - org.gjgr.exam.sort
 * CopyRright (c) 1949-xxxx:
 * File Number：
 * Author：gwd
 * Date：on 9/9/2017
 * Modify：gwd
 * Time ：
 * Comment：
 * Description：
 * Version：
 */
public class BucketSort {
    private int[] buckets;
    private int[] array;
    public BucketSort(int range,int[] array){
        this.buckets=new int[range];
        this.array=array;
    }
    public void sort(){
        if(array!=null&&array.length>1){
            for(int i=0;i<array.length;i++){
                buckets[array[i]]++;
            }
        }
    }
    public void print(){
        for(int i=buckets.length-1;i>=0;i--){
            for(int j=0;j<buckets[i];j++){
                System.out.println(i);
            }
        }
    }
}
