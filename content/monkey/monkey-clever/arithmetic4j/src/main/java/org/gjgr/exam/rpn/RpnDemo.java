package org.gjgr.exam.rpn;

/**
 * File Name : arithmetic4j - edu.rpn
 * CopyRright (c) 1949-xxxx:
 * File Number：
 * Author：gwd
 * Date：on 4/27/17
 * Modify：gwd
 * Time ：
 * Comment：
 * Description：
 * Version：
 */
import java.io.*;
import java.util.*;

public class RpnDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (;;) {
            String s = in.readLine();
            if (s==null) break;
            Stack<String> tks = new Stack<String>();
            tks.addAll(Arrays.asList(s.trim().split("[ \t]+")));
            if (tks.peek().equals("")) continue;
            try {
                double r = evalrpn(tks);
                if (!tks.empty()) throw new Exception();
                System.out.println(r);
            }
            catch (Exception e) {System.out.println("error");}
        }
    }

    private static double evalrpn(Stack<String> tks) throws Exception {
        String tk = tks.pop();
        double x,y;
        try {x = Double.parseDouble(tk);}
        catch (Exception e) {
            y = evalrpn(tks);  x = evalrpn(tks);
            if      (tk.equals("+"))  x += y;
            else if (tk.equals("-"))  x -= y;
            else if (tk.equals("*"))  x *= y;
            else if (tk.equals("/"))  x /= y;
            else throw new Exception();
        }
        return x;
    }
}