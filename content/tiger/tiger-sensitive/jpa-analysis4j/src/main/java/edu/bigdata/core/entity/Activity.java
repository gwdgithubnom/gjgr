package edu.bigdata.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gwd on 2016/4/15.
 */
public class Activity implements Serializable {

    private static final long serialVersionUID = 2264535351943252507L;
    private int id;
    private String content;
    private String title;
    private String material;
    private Date atime;//MM-dd-yyyy
    private String description;
    private String append;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Date getAtime() {
        return atime;
    }

    public void setAtime(Date atime) {
        this.atime = atime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppend() {
        return append;
    }

    public void setAppend(String append) {
        this.append = append;
    }
}
