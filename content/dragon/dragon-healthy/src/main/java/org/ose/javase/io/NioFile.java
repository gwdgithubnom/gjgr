package org.ose.javase.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class NioFile {
    private static final int BUFSIZE = 1024;

    public static String read(String filePath) throws IOException {
        FileInputStream in = null;
        try {
            in = new FileInputStream(new File(filePath).getCanonicalFile());
            FileChannel inChannel = in.getChannel();
            try {
                StringBuilder sb = new StringBuilder();
                ByteBuffer buff = ByteBuffer.allocate(BUFSIZE);
                while (inChannel.read(buff) != -1) {
                    // prepare for reading buffer
                    // 1. set limit to current position
                    // 2. set position to 0
                    // 3. discard marks
                    buff.flip();
                    sb.append(Charset.forName(System.getProperty("file.encoding")).decode(buff)
                        .toString());
                    // prepare for writing buffer
                    // 1. set position to 0
                    // 2. set limit to capacity
                    // 3. discard marks
                    buff.clear();
                }
                return sb.toString();
            } finally {
                if (inChannel != null) {
                    try {
                        inChannel.close();
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                }
            }
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
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(filePath).getCanonicalFile());
            FileChannel outChannel = out.getChannel();
            try {
                outChannel.write(ByteBuffer.wrap(content.getBytes()));
            } finally {
                if (outChannel != null) {
                    try {
                        outChannel.close();
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                }
            }
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

    public static void append(String filePath, String content) throws IOException {
        RandomAccessFile raFile = null;
        try {
            raFile = new RandomAccessFile(new File(filePath).getCanonicalFile(), "rw");
            FileChannel fileChannel = raFile.getChannel();
            try {
                fileChannel.position(fileChannel.size());
                fileChannel.write(ByteBuffer.wrap(content.getBytes()));
            } finally {
                if (fileChannel != null) {
                    try {
                        fileChannel.close();
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                }
            }
        } finally {
            if (raFile != null) {
                try {
                    raFile.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void copy(String srcFilePath, String dstFilePath) throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(new File(srcFilePath).getCanonicalFile());
            out = new FileOutputStream(new File(dstFilePath).getCanonicalFile());
            FileChannel inChannel = in.getChannel();
            FileChannel outChannel = out.getChannel();
            try {
                ByteBuffer buff = ByteBuffer.allocate(BUFSIZE);
                while (inChannel.read(buff) != -1) {
                    buff.flip();
                    while (buff.hasRemaining()) {
                        outChannel.write(buff);
                    }
                    buff.clear();
                }
            } finally {
                if (inChannel != null) {
                    try {
                        inChannel.close();
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                }
                if (outChannel != null) {
                    try {
                        outChannel.close();
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                }
            }
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void copy2(String srcFilePath, String dstFilePath) throws IOException {
        Files.copy(Paths.get(srcFilePath), Paths.get(dstFilePath),
            StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copyDir(String srcDirPath, String dstDirPath) throws IOException {
        File srcDir = new File(srcDirPath).getCanonicalFile();
        if (srcDir.isDirectory()) {
            File dstDir = new File(dstDirPath).getCanonicalFile();
            if (!dstDir.isDirectory()) {
                dstDir.mkdirs();
                System.out.println("Directory created: " + dstDir);
            }

            File[] srcFiles = srcDir.listFiles();
            if (srcFiles != null) {
                for (File srcFile : srcFiles) {
                    if (srcFile.isFile()) {
                        copy2(srcFile.getCanonicalPath(),
                            new File(dstDir, srcFile.getName()).getCanonicalPath());
                    } else if (srcFile.isDirectory()) {
                        copyDir(srcFile.getCanonicalPath(),
                            new File(dstDir, srcFile.getName()).getCanonicalPath());
                    }
                }
            }
        }
    }

    public static void concat(String[] srcs, String dst) throws IOException {
        FileOutputStream out = null;
        FileChannel outChannel = null;
        try {
            out = new FileOutputStream(new File(dst).getCanonicalFile());
            outChannel = out.getChannel();

            for (String src : srcs) {
                FileInputStream in = null;
                FileChannel inChannel = null;
                try {
                    in = new FileInputStream(new File(src).getCanonicalFile());
                    inChannel = in.getChannel();

                    inChannel.transferTo(0, inChannel.size(), outChannel);
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }
                    if (inChannel != null) {
                        try {
                            inChannel.close();
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }
                }
            }
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        write("test.txt", "Hello World!\n");
        append("test.txt", "Java!\n");
        System.out.println(read("test.txt"));

        copy("test.txt", "test_copy.txt");
        System.out.println(read("test_copy.txt"));

        concat(new String[] { "test.txt", "test_copy.txt" }, "concated.txt");
        System.out.println(read("concated.txt"));

        copy2("concated.txt", "concated_copy.txt");
        System.out.println(read("concated_copy.txt"));

        copyDir("./src/main/java/com/htyleo/javase/io", "test/main/java/com/htyleo/javase/io");

        new File("test.txt").delete();
        new File("test_copy.txt").delete();
        new File("concated.txt").delete();
        new File("concated_copy.txt").delete();
    }
}
