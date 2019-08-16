package org.gjgr.exam.geometry;

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
public class ExteriorPoint {
    public static void main(String[] args){
        InputStream inputStream=System.in;
        inputStream= InputOutObserver.getLocalInputFileStream(ExteriorPoint.class);
        Scanner in = new Scanner(inputStream);
        int n=in.nextInt();
        TreeMap<Integer,Integer> hashMap1=new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        TreeMap<Integer,Integer> hashMap2=new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        TreeMap<Integer,Integer> hashMap3=new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        int i=0;
        int j,k=0;
        while(i<n){
            j=in.nextInt();
            k=in.nextInt();
            hashMap1.put(j,k);
            hashMap2.put(k,j);
            i++;
        }
        hashMap3.put(hashMap1.firstKey(),hashMap1.firstEntry().getValue());
        Integer r=hashMap1.firstKey();
        hashMap3.put(hashMap2.firstEntry().getValue(),hashMap2.firstKey());
        hashMap1.remove(r);
        hashMap1.remove(hashMap2.firstEntry().getValue());
        List<Map.Entry<Integer,Integer>> list=new LinkedList<>(hashMap1.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer,Integer>>() {
            @Override
            public int compare(Map.Entry<Integer,Integer> o1, Map.Entry<Integer,Integer> o2) {
                int a=o1.getValue()*o1.getValue()-o1.getKey()*o1.getKey()-o2.getValue()*o2.getValue()+o2.getKey()*o2.getKey();
                return a;
            }
        });
        hashMap3.values();
        hashMap3.put(hashMap1.firstKey(),hashMap1.firstEntry().getValue());
        Iterator iterator = hashMap3.keySet().iterator();
        while (iterator.hasNext()) {
            Integer ii=(Integer)iterator.next();
            System.out.println(ii+" "+hashMap3.get(ii));
        }

    }
}
