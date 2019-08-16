package edu.bigdata.core.mapper;

import edu.bigdata.core.entity.Activity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by gwd on 2016/4/15.
 */
public interface ActivityMapper {

    @Select("select * from kb_activity where id=#{id}")
    public Activity getActivityByID(@Param("id") int id);

    @Select("select * from kb_activity where title=#{title}")
    public Activity getActivityByTitle(@Param("title") String title);
    //SELECT * FROM kb_activity WHERE atime>=Date("2016-04-15")
    @Select("select * from kb_activity where atime=Date('#{atime}')")
    public List<Activity> getActivitysByDate(@Param("atime") String atime);
}
