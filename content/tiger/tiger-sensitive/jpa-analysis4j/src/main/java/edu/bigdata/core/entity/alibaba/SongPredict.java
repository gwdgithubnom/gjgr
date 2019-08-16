package edu.bigdata.core.entity.alibaba;

import java.io.Serializable;

/**
 * Created by gwd on 2016/4/17.
 */
public class SongPredict implements Serializable {
    private static final long serialVersionUID=1L;
    private String artistId;
    private int playes;
    private String ds;

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public int getPlayes() {
        return playes;
    }

    public void setPlayes(int playes) {
        this.playes = playes;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }
}
