package org.gjgr.exam.array;

import org.gjgr.exam.tools.InputOutObserver;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 *
 *题目描述：
 * 小猴子下山，沿着下山的路有一排桃树，每棵树都结了一些桃子。小猴子想摘桃子，但是有一些条件需要遵守，小猴子只能沿着下
 * 山的方向走，不能回头，每颗树最多摘一个，而且一旦摘了一棵树的桃子，就不能再摘比这棵树结的桃子少的树上的桃子。那么小
 * 猴子最多能摘到几颗桃子呢？举例说明，比如有5棵树，分别结了10，4，5，12，8颗桃子，那么小猴子最多能摘3颗桃子，来
 * 自于结了4，5，8颗桃子的桃树。
 *
 * 输入:
 * 输入桃树的数量和每棵树的桃子数 N x1 x2 … xn
 * 输出:
 * 小猴子能摘到的最多的桃子个数
 * 输入范例:
 * 5
 * 10
 * 4
 * 5
 * 12
 * 8
 * 输出范例:
 * 3
 *
 * File Name : arithmetic4j - org.gjgr.exam.array
 * CopyRright (c) 1949-xxxx:
 * File Number：
 * Author：gwd
 * Date：on 8/21/2017
 * Modify：gwd
 * Time ：
 * Comment：
 * Description：
 * Version：
 */

public class MonkeyTakenPeach {

    static int pick(int[] peaches) {
        int[] max=new int[peaches.length];
        max[0]=0;
        for(int i=1;i<peaches.length;i++){
            if(peaches[i]>=peaches[i-1]){
                max[i]=max[i-1]+1;
            }else{
                int k=1;
                for(int j=i-1;j>=0;j--){
                    if(peaches[j]<peaches[i]){
                        k=max[j]+1;
                        break;
                    }
                }
                max[i]=k;
            }
        }
        Arrays.sort(max);
        return max[max.length-1];
    }

    static int pick1(int[] peaches) {
        int max = 1;
        // 记录每个位置的最长递增子序列的长度
        int result[] = new int[peaches.length];
        for (int i = 0; i < peaches.length; i++) {
            result[i] = 1;
            for (int j = 0; j < i; j++) {
                // 如果是i位置大于j位置，且j位置的最长递增子序列的长度+1长于目前i位置的最长递增子序列的长度，则更新i位置的最长递增子序列
                if (peaches[j] <= peaches[i] && result[j] + 1 > result[i])
                    result[i] = result[j] + 1;
            }
        }
        for (int i : result)
            max = i > max ? i : max;
        return max;
    }
    public static void main(String[] args){
        InputStream inputStream=null;
        if(true)
            inputStream= InputOutObserver.getLocalInputFileStream(MonkeyTakenPeach.class);
        else
            inputStream=System.in;
        Scanner in = new Scanner(inputStream);
        int trees = Integer.parseInt(in.nextLine().trim());
        int[] peaches = new int[trees];
        for (int i = 0; i < peaches.length; i++) {
            peaches[i] = Integer.parseInt(in.nextLine().trim());
        }
        System.out.println(pick(peaches));
    }
}
