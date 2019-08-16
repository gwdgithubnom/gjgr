package edu.muc.core.dataset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.muc.gwd.item.ImageItemVector;

public class DataFormat {

    public static List<ImageItemVector<Number>> getDataSetFromTxt(String fileName, String splitCode, String type) {
        List<ImageItemVector<Number>> result = new ArrayList<ImageItemVector<Number>>();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));

            String line = "";

            int count = 0;
            while ((line = reader.readLine()) != null && (line.length() > 0)) {
                String[] columns = line.split(splitCode);
                ImageItemVector<Number> vector = new ImageItemVector<>();
                if (type.equalsIgnoreCase("double")) {
                    Double[] data = new Double[columns.length];
                    for (int i = 0; i < columns.length; i++) {
                        Double d = new Double(columns[i]);
                        data[i] = d;
                    }
                    vector.setData(data);
                    vector.setId(count++);
                } else if (type.equalsIgnoreCase("int")) {
                    Integer[] data = new Integer[columns.length];
                    for (int i = 0; i < columns.length; i++) {
                        Integer d = new Integer(columns[i]);
                        data[i] = d;
                    }
                    vector.setData(data);
                    vector.setId(count++);
                }
                result.add(vector);
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        String path = "E:\\project\\cluster\\data.txt";
        List<ImageItemVector<Number>> result = DataFormat.getDataSetFromTxt(path, ",", "double");
        for (ImageItemVector<Number> imageItemVector : result) {
            if (imageItemVector != null) {
                String x = imageItemVector.toString();
                x = x.replace("0", " ");
                System.out.println(x);
            }

        }
    }
}
