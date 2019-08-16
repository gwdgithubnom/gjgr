package org.gjgr.exam.stack;

/**
 * File Name : arithmetic4j - org.gjgr.exam.stack
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
public final class HanoiDemo {
    public  static void hanoi(Integer n,Character A,Character B,Character C){
        if(n==1){
            move(A,C);
        }
        hanoi(n-1,A,C,B);
        move(A,C);
        hanoi(n-1,B,A,C);
    }

    public static void move(Character A,Character C){
        System.out.println("move");
    }

    public static void main(String[] args){
        hanoi(3,new Character('A'),new Character('B'),new Character('C'));
    }

}
