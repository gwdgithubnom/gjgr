package edu.bigdata.core.entity.alibaba;

import java.io.Serializable;

/**
 * Created by gwd on 2016/4/17.
 */
public class UserAction implements Serializable{
    private static final long serialVersionUID = 1L;
    private String userId;
    private String songId;
    private int gmtCreate;
    private int actionType;
    private int ds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public int getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(int gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public int getDs() {
        return ds;
    }

    public void setDs(int ds) {
        this.ds = ds;
    }
}
