package com.kindlebird.tools;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.betwixt.io.BeanWriter;
import org.xml.sax.SAXException;

/**
 * Created by gwd on 9/17/2016.
 */
public class CommonsBetwixt {

    /**
     * To save a object to file a xml stander file
     *
     * @param t
     * @param objecTname
     * @param filename
     * @param <T>
     * @return
     */
    public static <T> boolean persistObjectToFile(T t, String objecTname, String filename) {

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(PathKit.getRootClassPath() + "/" + filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        BeanWriter writer = new BeanWriter(out);
        writer.setEndTagForEmptyElement(true);

        try {
            if (objecTname == null)
                objecTname = filename;
            writer.write(objecTname, t);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static <T> boolean persistObjectToFile(T t) {

        String filename = "betwixt.xml";
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(new File(PathKit.getRootClassPath() + "/" + filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        BeanWriter writer = new BeanWriter(out);
        writer.setEndTagForEmptyElement(true);

        try {

            writer.write(BeanWriter.class.getName(), t);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * a method to save a object to string to print
     *
     * @param t
     * @param objectName
     * @param <T>
     * @return
     */
    public static <T> String persistObjectToString(T t, String objectName) {

        StringWriter stringWriter = new StringWriter();
        BeanWriter beanWriter = new BeanWriter(stringWriter);
        beanWriter.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
        beanWriter.getBindingConfiguration().setMapIDs(false);
        beanWriter.enablePrettyPrint();

        // If the base element is not passed in, Betwixt will guess
        // But let's write example bean as base element 'person'
        try {

            beanWriter.write(objectName, t);
            beanWriter.flush();
            beanWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

        // Write to System.out
        // (We could have used the empty constructor for BeanWriter
        // but this way is more instructive)

        // Betwixt writes fragments not documents so does not automatically
        // close
        // writers or streams.
        // This example will do no more writing so close the writer now.
        return stringWriter.toString();
    }

}
