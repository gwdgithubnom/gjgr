package edu.muc.core.result;

import java.io.File;
import java.io.IOException;

/**
 * 将某一个文件夹下的所有文件移动到另一个文件夹中
 *
 * @author Simon
 */
public class MoveFiles {
    public static String rootPath = "E:\\project\\cluster\\AllDataSet";

    public static void main(String[] args) {
        String baseDir = "E:\\project\\cluster\\DataSet\\全部的-未合并\\全部的";
        File dir = new File(baseDir);
        MoveFiles.moveFiles(dir, rootPath);
    }

    public static void moveFiles(File dir, String destDir) {
        if (dir.exists()) {
            if (dir.isFile()) {
                String fileName = dir.getName();
                File destFile = new File(destDir + File.separator + fileName);
                if (!destFile.exists()) {
                    try {
                        destFile.createNewFile();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                try {
                    Results.forChannel(dir, destFile);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                // is dir

                File[] files = dir.listFiles();
                for (File file : files) {
                    MoveFiles.moveFiles(file, destDir);
                }
            }
        }
    }
}
