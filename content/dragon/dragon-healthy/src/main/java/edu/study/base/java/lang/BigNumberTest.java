package edu.study.base.java.lang;

import java.math.BigDecimal;
import java.math.BigInteger;
public class BigNumberTest {

    /**
     * This is method is to add paddingChar when the first param string length !> expectLength
     * @param now
     * @param expectLength
     * @param paddingChar
     * @return
     */
    private static String lPad(String now , int expectLength , char paddingChar) {
        if(now == null || now.length() > expectLength) {
            return now;
        }
        StringBuilder buf = new StringBuilder(expectLength);
        for(int i = 0 , paddingLength = expectLength - now.length() ; i < paddingLength ; i++) {
            buf.append(paddingChar);
        }
        return buf.append(now).toString();
    }

    public static void main(String []args) {
        //这个数字long是放不下的
        BigDecimal bigDecimal = new BigDecimal("1233243243243243243243243243243243241432423432.1");
        System.out.println("数字的原始值是：" + bigDecimal);

        //bigDecimal = bigDecimal.add(BigDecimal.TEN); //for method add, there would must A BigDecimal could do this operation.
        //System.out.println("添加10以后：" + bigDecimal);

        //二进制数字
        byte[] bytes = bigDecimal.toBigInteger().toByteArray();
        for(byte b : bytes) {
            //to make the bytes to be positive bytes, this means complement 补码
            //to keep only 8 bits
            String bitString = Integer.toBinaryString(b & 0xff);
            System.out.println(b+" - "+Integer.toBinaryString(b)+" - "+(b&0xff)+" - "+bitString+"--->"+lPad(bitString , 8 , '0'));
        }
        //还原结果
        BigInteger bigInteger = new BigInteger(bytes);
        System.out.println("还原结果为：" + bigInteger);
    }
}
