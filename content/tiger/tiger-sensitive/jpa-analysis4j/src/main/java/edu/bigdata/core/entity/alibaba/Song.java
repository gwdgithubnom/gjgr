package edu.bigdata.core.entity.alibaba;

import java.io.Serializable;

/**
 * Created by gwd on 2016/4/17.
 */
public class Song implements Serializable {
    private static final long serialVersionUID = 1L;
    private String songId;
    private String artistId;
    private int publishTime;
    private int songInitPlays;
    private int language;
    private int gender;

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public int getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(int publishTime) {
        this.publishTime = publishTime;
    }

    public int getSongInitPlays() {
        return songInitPlays;
    }

    public void setSongInitPlays(int songInitPlays) {
        this.songInitPlays = songInitPlays;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
