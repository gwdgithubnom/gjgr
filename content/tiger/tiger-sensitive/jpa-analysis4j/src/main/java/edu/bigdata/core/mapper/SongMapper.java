package edu.bigdata.core.mapper;

import edu.bigdata.core.entity.alibaba.SongPredict;

import java.util.List;

/**
 * Created by gwd on 2016/4/17.
 */
public interface SongMapper {
    public List<SongPredict> getSongPredictCollection();
}
