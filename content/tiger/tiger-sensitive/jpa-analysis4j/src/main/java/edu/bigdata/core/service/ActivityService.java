package edu.bigdata.core.service;

import edu.bigdata.core.entity.Activity;
import edu.bigdata.core.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gwd on 2016/4/15.
 */
@Service
@Transactional
public class ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    public Activity getAcitvityByID(int id){
        return activityMapper.getActivityByID(id);
    }

}
