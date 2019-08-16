package org.base.hadoop.framework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.hadoop.config.annotation.EnableHadoop;
import org.springframework.data.hadoop.config.annotation.SpringHadoopConfigurerAdapter;
import org.springframework.data.hadoop.config.annotation.builders.HadoopConfigConfigurer;

/**
 * File Name : qmonkey - com.rocket.db.dssc.core
 * CopyRright (c) 1949-xxxx:
 * File Number：
 * Author：gwd
 * Date：on 6/18/2017
 * Modify：gwd
 * Time ：
 * Comment：
 * Description：
 * Version：
 */
@Configuration
@EnableHadoop
@ImportResource("classpath:/application-context.xml")
@PropertySource("classpath:/hbase.properties")
@ComponentScan({"com.rocket"})
public class BigDataConfig extends SpringHadoopConfigurerAdapter{

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public void configure(HadoopConfigConfigurer config) throws Exception {
        config
                .withResources().resource("classpath:/hbase-site.xml");
    }


}
