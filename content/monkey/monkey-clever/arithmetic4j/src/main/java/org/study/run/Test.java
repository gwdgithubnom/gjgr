package org.study.run; /**
 * File Name : kindlebird - PACKAGE_NAME
 * CopyRright (c) 1949-xxxx:
 * File Number：
 * Author：gwd
 * Date：on 8/1/2017
 * Modify：gwd
 * Time ：
 * Comment：
 * Description：
 * Version：
 */
import java.math.BigInteger;
import java.util.Scanner;

public class Test {

    public String getMultiBigNumber(String A,String B){
        if(A.length() > B.length()){
            char[] temp = new char[A.length()-B.length()];
            for(int i = 0;i < A.length() - B.length();i++)
                temp[i] = '0';
            B = String.valueOf(temp) + B;
        }
        if(A.length() < B.length()){
            char[] temp = new char[B.length()-A.length()];
            for(int i = 0;i < B.length() - A.length();i++)
                temp[i] = '0';
            A = String.valueOf(temp) + A;
        }

        int len = A.length() + B.length();

        char[] arrayA = A.toCharArray();
        char[] arrayB = B.toCharArray();
        for(int i = 0;i < arrayA.length;i++)
            if(arrayA[i] < '0' || arrayA[i] > '9')
                return null;
        for(int i = 0;i < arrayB.length;i++)
            if(arrayB[i] < '0' || arrayB[i] > '9')
                return null;

        char[] result = new char[len];
        for(int i = 0;i < len;i++)
            result[i] = '0';

        int countI = 0;       //用于计算当前B中已经和A中每个字符进行完乘法运算的字符个数
        for(int i = arrayB.length-1;i >= 0;i--){
            int tempB = arrayB[i] - '0';
            int countJ = 0;   //用于计算当前A中正在进行乘法运算的字符个数
            for(int j = arrayA.length - 1;j >= 0;j--,countJ++){
                int tempA = arrayA[j] - '0';
                int tempRe = (tempB * tempA) % 10;  //用于计算当前位置的数
                int tempResult = result[(len-1-countJ)-countI] - '0';  //当前位置已包含的结果
                tempResult += tempRe;
                //count--表示当前A字符串中进行乘法运算的字符位置，countI表示当前B字符串中进行乘法运算的字符位置
                //(count--)-countI则表示当前进行乘法运算两个数字结果的最低位的位置
                result[(len-1-countJ)-countI] = (char) (tempResult%10 + 48); //当前位置数最终结果

                int tempDi = tempB * tempA / 10 + tempResult / 10;       //用于计算进位
                for(int k = 1;tempDi > 0;k++){   //处理进位操作
                    //当前下第k个位置包含的结果
                    int tempResultK = result[(len-1-countJ)-countI-k] - '0';
                    tempResultK += tempDi;
                    result[(len-1-countJ)-countI-k] = (char) (tempResultK%10 + 48);
                    tempDi = tempResultK / 10;
                }
            }
            countI++;
        }

        return getNoneZeroString(result);
    }

    //去掉字符串前面的0
    public String getNoneZeroString(char[] result){
        int count = 0;
        for(int i = 0;i < result.length;i++){
            if(result[i] == '0')
                count++;
            else
                break;
        }
        char[] A = new char[result.length-count];
        for(int i = 0;i < result.length-count;i++)
            A[i] = result[count+i];
        return String.valueOf(A);
    }

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        String[] n= s.split(" ");
        BigInteger bigInteger1 = new BigInteger(n[0]);
        BigInteger bigInteger2 = new BigInteger(n[1]);
        bigInteger2 = bigInteger2.multiply(bigInteger1);
        System.out.println(bigInteger2);
    }
}
