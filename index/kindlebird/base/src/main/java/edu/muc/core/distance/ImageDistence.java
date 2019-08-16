package edu.muc.core.distance;

import edu.muc.gwd.item.ImageItemVector;

public class ImageDistence implements DistenceInter {

    @Override
    public <T extends Number> int getDistence(ImageItemVector<T> a, ImageItemVector<T> b) {
        // logger.debug("Distance"+a.getId()+"-"+b.getId());
        T[] dataA = (T[]) a.getData();
        T[] dataB = (T[]) b.getData();
        int distance = 0;
        for (int i = 0; i < dataB.length; i++) {
            int ta = dataA[i].intValue();
            int tb = dataB[i].intValue();
            distance = distance + Math.abs((ta - tb));
        }
        return distance;
    }
}
