package edu.muc.gwd.main;

import java.io.File;
import java.util.List;

import edu.muc.jxd.cluster.Cluster;
import edu.muc.core.dataset.DataFormat;
import edu.muc.core.distance.DistenceInter;
import edu.muc.core.distance.SimpleDistance;
import edu.muc.gwd.item.ImageItemVector;

public class TestOtherDataSet {

    public static void main(String[] args) {

        DistenceInter distance = new SimpleDistance();
        String rootPath = "E:\\Projects\\Java\\kindlebird\\data";
        String path = "unbance";
        String filePath = path + File.separator;
        List<ImageItemVector<Number>> result = DataFormat
                .getDataSetFromTxt(rootPath + filePath + "data.txt", " ", "double");
        for (ImageItemVector<Number> imageItemVector : result) {
            System.out.println(imageItemVector);
        }
        Cluster cluster = new Cluster(result, distance, 0, -1, 5);

        System.out.println(cluster.getP().toString());
        cluster.getP().writetoFile(new File(rootPath + filePath + "p.txt"));
        System.out.println(cluster.getDelta().toString());
        cluster.getDelta().writetoFile(new File(rootPath + filePath + "delta.txt"));
        System.out.println("dc=" + cluster.getP().getDc());
        cluster.printResult();
        cluster.getP().printEntropy();

    }

}
