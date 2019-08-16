package edu.muc.core.result;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultMap {

    HashMap<Integer, List<Number>> resultMap;

    public ResultMap() {
        this.resultMap = new HashMap<>();
    }

    public void readDataToMap(String path) {
        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int i = 0;
        while (null != line && line.length() > 0) {
            String[] num = line.split(" ");
            List<Number> numList = new ArrayList<>();
            for (int j = 0; j < num.length; j++) {
                if (num[j].trim().length() > 0) {
                    numList.add(Integer.parseInt(num[j].trim()));
                }
            }
            this.resultMap.put(i++, numList);
            try {
                line = reader.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("Read Data Finished!");
    }

    public void trimMap() {
        int len = 10;
        HashMap<Integer, List<Number>> newMap = new HashMap<>();
        for (Integer key : this.resultMap.keySet()) {
            List<Number> numList = this.resultMap.get(key);
            if (numList.size() > len) {
                newMap.put(key, numList);
            }
        }
        this.resultMap = newMap;
    }

    public void printMap() {
        for (Integer key : this.resultMap.keySet()) {
            System.out.print(key + ":");
            List<Number> nums = this.resultMap.get(key);
            for (Number number : nums) {
                System.out.print(number + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
