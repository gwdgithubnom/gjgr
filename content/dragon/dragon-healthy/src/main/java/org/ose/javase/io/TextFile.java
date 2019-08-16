package org.ose.javase.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TextFile {
    public static String read(String filePath) throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(new File(filePath).getCanonicalFile()));
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

    public static void write(String filePath, String content) throws IOException {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new File(filePath).getCanonicalFile());
            out.print(content);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
