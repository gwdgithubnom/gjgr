package org.gjgr.exam.hex;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * File Name : arithmetic4j - org.gjgr.exam.hex
 * CopyRright (c) 1949-xxxx:
 * File Number：
 * Author：gwd
 * Date：on 8/24/2017
 * Modify：gwd
 * Time ：
 * Comment：
 * Description：
 * Version：
 */
public final class SerializeHex {
    private static final Logger logger = LoggerFactory.getLogger(SerializeHex.class);
    @Test
    public void test(){
   /*     Integer[] spam = new Integer[] { 1, 2, 3 };
        List<Integer> t=Arrays.asList(spam);*/
        int[] spam = new int[] { 1, 2, 3 };
        List<Integer> t= Arrays.stream(spam)
                .boxed()
                .collect(Collectors.toList());
        t.add(333);
        System.out.println(new String(hexStringToBytes("0x771A")));
        Integer[] integers = new Integer[] {1,2,3,4,5};
        List<Integer> list21 =  Arrays.asList(integers); // Cannot modify returned list
        List<Integer> list22 = new ArrayList<>(Arrays.asList(integers)); // good
        List<Integer> list23 = Arrays.stream(integers).collect(Collectors.toList()); //Java 8
    }

    /**
     * 将传进来的十六进制表示的字符串转换成byte数组
     * @param hexString
     * @return 二进制表示的byte[]数组
     */
    private static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase(Locale.getDefault());
        int length = hexString.length() / 2;
        //将十六进制字符串转换成字符数组
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            //一次去两个字符
            int pos = i * 2;
            //两个字符一个对应byte的高四位一个对应第四位
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * 将传入的byte[]数组转换成十六机制数的字符串
     * @param src 要转换的byte数组
     * @return 返回十六进制的字符串
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            //将一个byte的二进制数转换成十六进制字符
            String hv = Integer.toHexString(v);
            //如果二进制数转换成十六进制数高位为0，则加入'0'字符
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


}
