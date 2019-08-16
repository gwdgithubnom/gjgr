package org.gjgr.exam.stack;

import org.gjgr.exam.tools.InputOutObserver;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

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
public class BracketMatching {
    public static void main(String[] args){
        InputStream inputStream=System.in;
        inputStream= InputOutObserver.getLocalInputFileStream(BracketMatching.class);
        Scanner scanner=new Scanner(inputStream);
        Stack<Model> stack=new Stack<>();
        String str=scanner.nextLine();
        List<Integer> list=new ArrayList<>();
        Integer sum=1;
        Integer pair=0;
        Integer i=0;
        //Integer ii=0;
        Integer cc=1;
        Character c;
        Character pre=new Character('\0');
        while (i<str.length()){
           c=str.charAt(i);
            if(c=='('){
                Model model=new Model();
                stack.push(model);
                pre=c;
            }else if(c==')'){
                if(pre==')'){
                    cc++;
                }
                list.add(cc);
                stack.pop();
                if(stack.size()>0)
                  stack.peek().i++;
                pre=c;
            }
            i++;
        }

        for (Integer k:list){
            sum*=k;
        }

        System.out.println(sum);

    }
}

class Model{
    public  Integer i=new Integer(0);
}
