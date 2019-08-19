package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.hadoop.config.annotation.EnableHadoop;

import java.io.IOException;

/**
 * File Name : ht - org.base.hadoop.framework
 * CopyRright (c) 1949-xxxx:
 * File Number：
 * Author：gwd
 * Date：on 8/19/2017
 * Modify：gwd
 * Time ：
 * Comment：
 * Description：
 * Version：
 */
@Configuration
@EnableHadoop
public class HadoopConfiguration {
    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public void configure(HadoopConfigConfigurer config) throws Exception {
        org.apache.hadoop.conf.Configuration conf = getHadoopConfig("classpath:/mapred-site.xml");

        config
                .withResources()
                .resource("classpath:/yarn-site.xml")
                .resource("classpath:/hbase-site.xml")
                .resource("classpath:/mapred-site.xml")
                .and()
                .withProperties()
                .property("mapreduce.app-submission.cross-platform", "true")
                .property("mapreduce.framework.name", "yarn")
                .property("mapreduce.application.framework.path", "")
                .property("mapreduce.map.log.level", "INFO")
                .property("mapreduce.reduce.log.level", "INFO")
                .property("hbase.client.scanner.caching", "1")
                .property("hbase.client.scanner.timeout.period", "300000")
                .property("hbase.rpc.timeout", "300000");
    }

    private org.apache.hadoop.conf.Configuration getHadoopConfig(String mrSiteFile) throws IOException {
        Resource mapRedSite = resourceLoader.getResource(mrSiteFile);
        org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
        conf.addResource(mapRedSite.getInputStream());
        return conf;
    }
}
