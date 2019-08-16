package org.study.run;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

/** 请完成下面这个函数，实现题目要求的功能 **/
    /** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^  **/
    static boolean resolve(int[] A) {
        int n=A.length;
        int i=0;
        int pre[]=new int[1111111];
        for(i=1;i<n;i++){
            pre[i]=pre[i-1]+A[i];
        }
        int temp=pre[n]/4;
        int flag[]={0,0,0,0,0};
        int s[]=new int[5];
        for(i=1;i<=4;i++)
        {
            s[i] = temp*i;
        }

        for(i=1;i<=n;i++)
        {

            if(flag[2]==1&&pre[i]==s[3]) flag[3]=1;
            else if(flag[1]==1&&pre[i]==s[2]) flag[2]=1;
            else if(pre[i]==s[1]) flag[1] = 1;
        }
        if(flag[3]==1||flag[1]==1)
        {
            return true;
        }
        else
        {
            return false;
        }


    }

    public static void main(String[] args){
        ArrayList<Integer> inputs = new ArrayList<Integer>();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while(line != null && !line.isEmpty()) {
            int value = Integer.parseInt(line.trim());
            if(value == 0) break;
            inputs.add(value);
            line = in.nextLine();
        }
        int[] A = new int[inputs.size()];
        for(int i=0; i<inputs.size(); i++) {
            A[i] = inputs.get(i).intValue();
        }
        Boolean res = resolve(A);

        System.out.println(String.valueOf(res));
    }
}