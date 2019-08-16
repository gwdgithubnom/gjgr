package org.gjgr.exam.number.find;

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
    public static void main(String[] args) {
        InputStream inputStream=System.in;
        //inputStream= InputOutObserver.getLocalInputFileStream(BracketMatching.class);
        Scanner scanner = new Scanner(inputStream);
        Integer length = scanner.nextInt();
        Integer node1 = scanner.nextInt();
        Integer node2 = scanner.nextInt();
        Integer node3 = scanner.nextInt();
        Integer max = node1 > node2 ? (node1 > node3 ? node1 : node3) : (node2 > node3 ? node2 : node3);
        Integer min = node1 < node2 ? (node1 < node3 ? node1 : node3) : (node2 < node3 ? node2 : node3);
        Integer left = 1;
        Integer right = (int)Math.pow(2, length) - 1;
        Integer middle = (left + right) / 2;
        while(true){
            if(middle > min && middle < max){
                break;
            }
            if(min > middle){
                left = middle + 1;
                middle = (left + right) / 2;
            }else if(max < middle){
                right = middle - 1;
                middle = (left + right) / 2;
            }
        }
        System.out.println(middle);
    }
}
