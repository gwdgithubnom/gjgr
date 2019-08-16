package org.ose.javase.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlDara {
    public static byte[] readBytes(URL url) throws IOException {
        URLConnection uc = url.openConnection();
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(uc.getInputStream());
            byte[] data = new byte[in.available()];
            in.read(data);
            return data;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static String readString(URL url) throws IOException {
        URLConnection uc = url.openConnection();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = in.readLine()) != null) {
                sb.append(s).append("\n");
            }
            return sb.toString();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://htyleo.com/test/test.txt");

        byte[] bdata = readBytes(url);
        BinaryFile.write("test.txt", bdata);

        String sdata = readString(url);
        System.out.println(sdata);
    }
}
