package edu.bigdata.gwd.plugin;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.Reader;

/**
 * Created by gwd on 2016/4/17.
 */
public class ConnectionFactory {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;
    private static Logger log=Logger.getLogger(ConnectionFactory.class);
    static {
        try{
            reader= Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            log.fatal(e.getMessage());
        }
    }
    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }
}
