package org.ose.javase.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryFile {
    public static byte[] read(String filePath) throws IOException {
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(new File(filePath).getCanonicalFile()));
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

    public static void write(String filePath, byte[] data) throws IOException {
        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(
                new File(filePath).getCanonicalFile()));
            out.write(data);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        byte[] data = read("src/main/java/com/htyleo/javase/io/BinaryFile.java");
        write("src/main/java/com/htyleo/javase/io/BinaryFileCopy.java", data);
        new File("src/main/java/com/htyleo/javase/io/BinaryFileCopy.java").delete();
    }
}
