package edu.muc.core.result;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import edu.muc.gwd.item.ImageItemVector;
import com.kindlebird.tools.ToImageVec;

public class XML2Image {

    public static BufferedImage ArrayInt2Image(int data[][], int size) {
        if (data != null) {
            BufferedImage br = new BufferedImage(size, size, BufferedImage.TYPE_BYTE_GRAY);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    br.setRGB(j, i, data[i][j]);// 设置像素
                }
            }
            return br;
        }
        return null;
    }

    public static int[][] to2Array(int[] data, int size) {
        int imageArray[][] = new int[size][size];
        int k = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                imageArray[i][j] = data[k++];
            }
        }
        return imageArray;
    }

    public static void saveImage(BufferedImage image, String pathname) {
        File file = new File(pathname);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            ImageIO.write(image, "jpg", file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void xml2Images() {
        String path = "6";
        // String path = "test";
        // String path = "all";
        String filePath = path + File.separator;
        List<ImageItemVector<Number>> itemList = ToImageVec.getImageVec(filePath + "image.xml");
        String destPath = "E:\\project\\cluster" + File.separator + path;
        for (ImageItemVector<Number> imageItemVector : itemList) {
            int id = imageItemVector.getId();
            Number[] data = imageItemVector.getData();
            System.out.println("data:");
            for (int i = 0; i < data.length; i++) {
                System.out.println(data[i]);
            }
            int[][] imageArray = to2Array(data, 28);
            System.out.println("imageArray");
            for (int[] numbers : imageArray) {
                for (Number number : numbers) {
                    System.out.println(number);
                }
            }
            BufferedImage iamge = ArrayInt2Image(imageArray, 28);
            saveImage(iamge, destPath + File.separator + "images" + File.separator + id + ".jpg");
        }

    }

    private static int[][] to2Array(Number[] data, int size) {
        int imageArray[][] = new int[size][size];
        int k = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                imageArray[i][j] = (data[k++].intValue() * 65535);
            }
        }
        return imageArray;
    }

    public static void main(String[] args) {
        xml2Images();
    }

}
