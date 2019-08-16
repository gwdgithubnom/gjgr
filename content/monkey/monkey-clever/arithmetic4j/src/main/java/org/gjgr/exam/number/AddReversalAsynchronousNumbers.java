package org.gjgr.exam.number;

import java.util.Scanner;

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
public class AddReversalAsynchronousNumbers {

    public static int[] rec(int[] s,int[] nn){
        int[] nnn=new int[s.length];
        int index=1;
        for(int i=1;i<s.length;i++){
            System.arraycopy(nn,0,nnn,0,index);
            nnn[index]=s[i];
            reverse(nnn,0,index);
            index++;
            nn=nnn;
        }
        return nn;
    }

    public static void reverse(int [] arr,int s,int t){
        int tm,k=s,j=t;
        while(k<j){
            tm=arr[k];
            arr[k]=arr[j];
            arr[j]=tm;
            k++;
            j--;
        }
    }

    public static void main(String[] args){
        int i=0,j=0,k=0;
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int m=2;
        int[] nn=new int[n];
        //int[] t=new int[0];
        while(i<n){
            nn[i]=s.nextInt();
            i++;
        }
        System.out.print(nn[n-1]);
        if(n%2!=0){
            n++;
            m=1;
        }
        for(i=n/2;i>=1;i/=2){
            System.out.print(" "+nn[i-1]);
        }
        for(i+=m;i<nn.length;i+=2){
            System.out.print(" "+nn[i]);
        }
        
    }
}
