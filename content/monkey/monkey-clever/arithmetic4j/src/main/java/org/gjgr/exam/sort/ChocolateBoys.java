package org.gjgr.exam.sort;

import org.gjgr.exam.tools.InputOutObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Arrays;
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
 */
public class ChocolateBoys {

    private static final Logger logger = LoggerFactory.getLogger(ChocolateBoys.class);

    public static void main(String[] args) {
        InputStream inputStream=System.in;
        inputStream= InputOutObserver.getLocalInputFileStream(ChocolateBoys.class);
        Scanner scanner = new Scanner(inputStream);
        int n=scanner.nextInt();
        int[] h=new int[n+1];
        for(int i=1;i<=n;i++){
            h[i]=scanner.nextInt();
        }
        int m=scanner.nextInt();
        int[] w=new int[m+1];
        for(int i=1;i<=m;i++){
            w[i]=scanner.nextInt();
        }

       // Arrays.asList(h,Integer.class);
        Arrays.sort(h);
        Arrays.sort(w);
        int sum=0;
        int j=w.length-1;
        for(int i=h.length-1;i>0;i--){
            if(w[j]>=h[i]){
                    sum++;
                    j--;
                if(j<1)
                    break;
            }else{
                break;
            }
        }
        System.out.println(sum);
    }

    public static void reverse(final int[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = startIndexInclusive < 0 ? 0 : startIndexInclusive;
        int j = Math.min(array.length, endIndexExclusive) - 1;
        int tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

}
