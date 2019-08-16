package org.gjgr.exam.sort;

import org.gjgr.exam.tools.InputOutObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * File Name : arithmetic4j - org.gjgr.exam.sort
 * CopyRright (c) 1949-xxxx:
 * File Number：
 * Author：gwd
 * Date：on 8/23/2017
 * Modify：gwd
 * Time ：
 * Comment：
 * Description：
 * Version：
 * 贪心
 */
public class SectionMulti {

    private static final Logger logger = LoggerFactory.getLogger(SectionMulti.class);

    public static void main(String[] args) {
        InputStream inputStream=System.in;
        inputStream= InputOutObserver.getLocalInputFileStream(SectionMulti.class);
        Scanner scanner = new Scanner(inputStream);
        int n=scanner.nextInt();
        int[] section=new int[n];
        for(int i=0;i<n;i++){
            section[i]=scanner.nextInt();
        }
        Arrays.sort(section);
        int mid=(section[0]+section[section.length-1])/2;
        int sum=binarysearchKey(section,mid);
        System.out.println(sum);
    }


    public static Integer binarysearchKey(int[] array, int targetNum) {
        int bl=0,br=0;
        int left = 0, right = array.length-1;
        int mid=(left+right)/2;
        while (right>left&&(mid!=right&&mid!=left)){
            if(array[mid]>targetNum){
                right=mid;
                bl=left;
                br=right;
                mid=(left+right)/2;
            }else if(array[mid]<targetNum){
                left=mid;
                bl=left;
                br=right;
                mid=(left+right)/2;
            }else {
                return mid;
            }
        }
        return Math.abs(array[bl]-targetNum)-Math.abs(array[br]-targetNum)>0?array[br]:array[bl];
    }


    public List<Integer> convertToIntegerList(int[] array){
        List<Integer> ll=new ArrayList<>();

        for(int i=0;i<array.length;i++){
            ll.add(array[i]);
        }
        return ll;
    }
}
