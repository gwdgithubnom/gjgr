package edu.muc.gwd.main;

import java.io.File;
import java.util.List;

import edu.muc.jxd.cluster.Cluster;
import edu.muc.core.distance.DistenceInter;
import edu.muc.core.distance.MixDistance;
import edu.muc.gwd.item.ImageItemVector;
import com.kindlebird.tools.ToImageVec;

public class Main {

    public static void main(String[] args) {

        DistenceInter distance = new MixDistance();
        // DistenceInter distance = new ImageDistence();
        String path = "/home/gwd/Projects/liber/src/python/data/xml/";
        // String path = "test";
        // String path = "all";
        String filePath = path + File.separator;
        List<ImageItemVector<Number>> itemList = ToImageVec.getImageVec(filePath + "image.xml");
        Cluster cluster = new Cluster(itemList, distance, 1, -1, 10);
        // System.out.println("ItemList");

		/*
         * for (ImageItemVector<Number> imageItemVector : itemList) {
		 * System.out.println(imageItemVector);
		 * 
		 * }
		 * 
		 */
        System.out.println(cluster.getP().toString());
        cluster.getP().writetoFile(new File("/home/gwd/Projects/liber/src/java/src/main/resources/" + filePath + "p.txt"));
        System.out.println(cluster.getDelta().toString());
        cluster.getDelta().writetoFile(new File("/home/gwd/Projects/liber/src/java/src/main/resources/" + filePath + "delta.txt"));
        System.out.println("dc=" + cluster.getP().getDc());
        cluster.printResult();
        cluster.getP().printEntropy();
    }

}
