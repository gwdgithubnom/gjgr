package edu.muc.gwd.item;

import java.util.StringTokenizer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by gwd on 9/13/2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Image")
@XmlType(propOrder = {"id", "data"})
public class ImageItemXmlElement {
    private int id;
    private String data;

    public ImageItemXmlElement() {
        // data=new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * to covert the string to Number[]
     *
     * @return
     */
    public ImageItemVector<Number> getDataToImageItemVector() {
        if (data == null || id < 0) {
            return null;
        }
        ImageItemVector<Number> imageItemVector = new ImageItemVector<>();
        // TODO
        String temp = data;
        temp = temp.replace("[", "");
        temp = temp.replace("]", "");
        StringTokenizer tokenizer = new StringTokenizer(temp, ",");
        int l = tokenizer.countTokens();
        Number[] numbers = new Number[l];
        int i = 0;
        while (tokenizer.hasMoreElements()) {
            Integer x = Integer.valueOf(tokenizer.nextToken().trim());
            Integer y = 0;
            if (x > 128) {
                y = 0;
            } else {
                y = 1;
            }
            numbers[i++] = y;
        }

        imageItemVector.setId(id);
        imageItemVector.setData(numbers);
        return imageItemVector;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
