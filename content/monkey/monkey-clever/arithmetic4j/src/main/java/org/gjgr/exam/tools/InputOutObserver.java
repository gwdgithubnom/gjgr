package org.gjgr.exam.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;

/**
 * File Name : arithmetic4j - org.gjgr.arithmetic.tools
 * CopyRright (c) 1949-xxxx:
 * File Number：
 * Author：gwd
 * Date：on 8/20/2017
 * Modify：gwd
 * Time ：
 * Comment：
 * Description：
 * Version：
 */
public class InputOutObserver {

    private static Logger logger= LoggerFactory.getLogger(InputOutObserver.class.getName());

    public InputOutObserver(){}

    public static FileInputStream getLocalInputFileStream(Class clazz){
        String s=null;
        String name = clazz.getSimpleName()+".input";
        FileInputStream fileInputStream=null;
           try{
               URL uri=clazz.getClass().getResource(name);
               fileInputStream=new FileInputStream(clazz.getResource(name).getFile());
           }catch (Exception e){
               e.printStackTrace();
           }
           return fileInputStream;
    }

    public static FileOutputStream getLocalOutFileStream(Class clazz){
        String name = clazz.getSimpleName()+".input";
        FileOutputStream fileOutputStream=null;
        try{
            fileOutputStream=new FileOutputStream(new File(name));
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileOutputStream;
    }



}
