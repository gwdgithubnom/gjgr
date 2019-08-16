package edu.muc.core.result;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Results {
    public static String rootPath = "E:\\project\\cluster\\result";

    public static HashMap<Integer, List<Number>> initClusterMap(String path) {
        ResultMap map = new ResultMap();
        map.readDataToMap(path);
        //map.trimMap();
        map.printMap();
        return map.resultMap;
    }

    /**
     * 将Map文件移动到dir
     *
     * @param map
     */

    public static void moveToDir(HashMap<Integer, List<Number>> map) {
        String dirPath = Results.rootPath + File.separator + "clusters";
        File dirs = new File(dirPath);
        if (!dirs.exists()) {
            dirs.mkdirs();
        }
        String fileEnd = ".jpg";
        for (Integer key : map.keySet()) {
            // 创建dir
            File dir = new File(dirPath + File.separator + "" + key);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // 获取图像
            List<Number> fileNames = map.get(key);
            for (Number aFileNum : fileNames) {
                // 要移动的图像
                String fileName = aFileNum + fileEnd;
                File aImage = new File(Results.rootPath + File.separator + "images" + File.separator + fileName);
                File destFile = new File(dir, fileName);
                // 移动文件
                try {
                    System.out
                            .println("Move From\n" + aImage.getAbsolutePath() + "\nto\n" + destFile.getAbsolutePath());
                    Results.forChannel(aImage, destFile);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 复制文件
     *
     * @param f1
     * @param f2
     * @return
     * @throws Exception
     */

    @SuppressWarnings("resource")
    public static long forChannel(File f1, File f2) throws Exception {
        long time = new Date().getTime();
        int length = 2097152;
        FileChannel inC = new FileInputStream(f1).getChannel();
        FileChannel outC = new FileOutputStream(f2).getChannel();
        ByteBuffer b = null;
        while (true) {
            if (inC.position() == inC.size()) {
                inC.close();
                outC.close();
                return new Date().getTime() - time;
            }
            if ((inC.size() - inC.position()) < length) {
                length = (int) (inC.size() - inC.position());
            } else
                length = 2097152;
            b = ByteBuffer.allocateDirect(length);
            inC.read(b);
            b.flip();
            outC.write(b);
            outC.force(false);
        }
    }

    public static void main(String[] args) {
        String path = Results.rootPath + File.separator + "\\Results\\241.txt";
        HashMap<Integer, List<Number>> map = Results.initClusterMap(path);
        Results.moveToDir(map);
    }

}
