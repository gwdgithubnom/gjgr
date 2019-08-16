package edu.muc.core.result;

import java.io.File;
import java.io.IOException;

/**
 * 将某一个文件夹下的所有文件移动到另一个文件夹中
 *
 * @author Simon
 */
public class MoveFilesAndRename {
    public static String rootPath = "E:\\project\\cluster\\paperdataset";

    public static int id = 0;

    public static void main(String[] args) {
        String baseDir = "E:\\project\\cluster\\DataSet\\7Test";
        File dir = new File(baseDir);
        MoveFilesAndRename.moveFiles(dir, rootPath);
    }

    public static void moveFiles(File dir, String destDir) {
        if (dir.exists()) {
            if (dir.isFile()) {
                File destFile = new File(destDir + File.separator + MoveFilesAndRename.id++ + ".jpg");
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
                    MoveFilesAndRename.moveFiles(file, destDir);
                }
            }
        }
    }
}
