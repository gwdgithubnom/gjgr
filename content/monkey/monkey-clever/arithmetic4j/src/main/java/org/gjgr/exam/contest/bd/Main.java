package org.gjgr.exam.contest.bd;

import org.gjgr.exam.tools.InputOutObserver;

import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        InputStream inputStream=System.in;
       // inputStream= InputOutObserver.getLocalInputFileStream(org.gjgr.exam.problem.problem7.BracketMatching.class);
        Scanner scanner=new Scanner(inputStream);
        Integer n=Integer.parseInt(scanner.nextLine().split(",")[0]);
        String l=null;
        Integer[][] quadrangle=new Integer[4][2];
        Integer[][] bRangle=new Integer[3][2];
        Integer i=0,j=0;
        while (i<n){
            l=scanner.nextLine();
            if(l.contains("|")){
                bRangle[j][1]=l.indexOf('|');
                bRangle[j][0]=i;
                int k=l.lastIndexOf('|');
                if(k!=bRangle[j][1]){
                    j++;
                    bRangle[j][0]=i;
                    bRangle[j][1]=k;
                }
                j++;
            }
            i++;
        }
        l=scanner.nextLine();
        String[] ss=l.split(",");
            int k=0;
            Integer[] integers=new Integer[4];
            integers[0]=Integer.parseInt(ss[0]);
            integers[1]=Integer.parseInt(ss[1]);
            integers[2]=Integer.parseInt(ss[2]);
            integers[3]=Integer.parseInt(ss[3]);
            if((integers[0]==bRangle[0][0]&&integers[1]==bRangle[0][1])||(integers[2]==bRangle[0][0]&&integers[3]==bRangle[0][1])){
                quadrangle[k][0]=bRangle[0][0];
                quadrangle[k][1]=bRangle[0][1];
                k+=2;
            }else{
                quadrangle[1][0]=bRangle[0][0];
                quadrangle[1][1]=bRangle[0][1];
            }

            if((integers[0]==bRangle[0][0]&&integers[1]==bRangle[0][1])||(integers[2]==bRangle[1][0]&&integers[3]==bRangle[1][1])){
                quadrangle[k][0]=bRangle[1][0];
                quadrangle[k][1]=bRangle[1][1];
                k+=2;
            }else {
                quadrangle[1][0]=bRangle[1][0];
                quadrangle[1][1]=bRangle[1][1];
            }

            if((integers[0]==bRangle[0][0]&&integers[1]==bRangle[0][1])||(integers[2]==bRangle[2][0]&&integers[3]==bRangle[2][1])){
                quadrangle[k][0]=bRangle[2][0];
                quadrangle[k][1]=bRangle[2][1];
                k+=2;
            }else {
                quadrangle[1][0]=bRangle[2][0];
                quadrangle[1][1]=bRangle[2][1];
            }
        //向量加法的和就是以两个向量的边作为平行四边形长边的对角线表示
        //公式：u + v = < u.x , u.y  > + < v.x , v.y > = < u.x + v.x , u.y + v.y >
        //ref:http://blog.csdn.net/sunomg/article/details/70237098
        quadrangle[3][0]=bRangle[0][0]+bRangle[2][0]-bRangle[1][0];
        quadrangle[3][1]=bRangle[0][1]+bRangle[2][1]-bRangle[1][1];
        System.out.println((quadrangle[3][0]+1)+" "+(quadrangle[3][1]+1));

    }

}
