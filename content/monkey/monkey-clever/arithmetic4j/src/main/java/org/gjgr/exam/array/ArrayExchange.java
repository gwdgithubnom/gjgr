package org.gjgr.exam.array;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * File Name : arithmetic4j - org.gjgr.exam.array
 * CopyRright (c) 1949-xxxx:
 * File Number：
 * Author：gwd
 * Date：on 8/20/2017
 * Modify：gwd
 * Time ：
 * Comment：
 * Description：
 * Version：
 */
public class ArrayExchange {
    public static void main(String[] args){
        int n=0,i=0,pre,next;
        Scanner scanner=new Scanner(System.in);
        List<Integer> list=new LinkedList<Integer>();
        n=scanner.nextInt();
        pre=1;
        list.add(1);
        while (i<n){
            next=scanner.nextInt();
            i++;
            if(next==pre){
                continue;
            }else {
                pre=next;
                list.add(next);
            }
        }
        int sum=0;
        int p=0,q=1,r=0;
        while (q<list.size()){
            if(list.get(q)>list.get(p)){
                q++;
                p++;
            }else if(list.get(q)<list.get(p)){
                sum++;
                p++;
            }else if(list.get(q)==list.get(p)){
                list.remove(q);
                q--;
                p--;
                //p++;
                //p=0;
            }
        }
        System.out.println(sum);
    }

    @Test
    public void test(){

    }
}
