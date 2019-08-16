package org.gjgr.exam.problem.problem9;

import org.gjgr.exam.tools.InputOutObserver;

import java.io.InputStream;
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
public class Main {
    static int cnt=0;
    public static void main(String[] args){
        InputStream inputStream=System.in;
        inputStream= InputOutObserver.getLocalInputFileStream(Main.class);
        Scanner scanner=new Scanner(inputStream);
        fib(7);
        System.out.println(cnt);
    }
    static  int fib(int n){
        cnt++;
        if(n==0){
            return 1;
        }else if(n==1)
            return 2;
        else
            return fib(n-1)+fib(n-2);
    }
    public void method(Object o){

    }
    public void method(String s){

    }
}
