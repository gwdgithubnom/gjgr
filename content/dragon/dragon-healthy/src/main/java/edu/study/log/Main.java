package edu.study.log;

/**
 * Created by gwd on 2016/6/7.
 */
public class Main {
    public static void main(String args[]){
        MucLogger logger1=new MucLogger();
        logger1.start();
        logger1.setName("Yiwen");
        MucLogger logger2=new MucLogger();
        logger2.start();
        logger2.setName("Job tracker");
        MucLogger logger3=new MucLogger();
        logger3.start();
        logger3.setName("hadoop");
        MucLogger logger4=new MucLogger();
        MucLogger logger5=new MucLogger();
        MucLogger logger6=new MucLogger();
        MucLogger logger7=new MucLogger();
        logger4.start();
        logger4.setName("hbase");
        logger5.start();
        logger5.setName("mysql");
        logger6.start();
        logger6.setName("webjs");
        logger7.start();
        logger7.setName("system");
    }
}
