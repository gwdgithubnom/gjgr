package org.gjgr.exam.number;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
public class BigIntegerProblem {

    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int n=0;
        BigInteger r,avg;
        int i=0;
        BigInteger ai,bi;
        BigInteger num=BigInteger.valueOf(0);
        BigInteger sum=BigInteger.valueOf(0);
        BigInteger sumScore=BigInteger.ZERO;
        n=scanner.nextInt();
        r=scanner.nextBigInteger();
        avg=scanner.nextBigInteger();
        sumScore=avg.multiply(BigInteger.valueOf(n));
        List<BigInteger> bigIntegerList=new ArrayList<BigInteger>();
        while (i<n){
            i++;
            ai=scanner.nextBigInteger();
            bi=scanner.nextBigInteger();
            bigIntegerList.add(bi);
            sumScore.subtract(ai);
       /*     if(ai.compareTo(avg)>0){
                sumScore.subtract(ai);
                continue;
            }else {
                sum=sum.add(avg);
                sum=sum.subtract(ai);
                num.add(BigInteger.valueOf(1));
            }*/
        }
        Collections.sort(bigIntegerList);
        for(BigInteger bb:bigIntegerList){
            System.out.println(bb);
        }
        System.out.println(sum);

    }

}
