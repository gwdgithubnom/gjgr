package edu.muc.gwd.item;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gwd on 9/13/2016.
 */
@XmlRootElement(name = "Images")
public class ImageItemXml {
    @XmlElement(name = "Image")
    private List<ImageItemXmlElement> images;

    /**
     * init the entity
     */
    public ImageItemXml() {
        images = new ArrayList<>();
    }

    public List<ImageItemXmlElement> getImagesData() {
        return images;
    }

    public void setImagesData(List<ImageItemXmlElement> images) {
        this.images = images;
    }


}
