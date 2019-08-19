package org.gjgr.example.entity;


import com.alibaba.fastjson.JSONObject;
import com.rocket.tools.JSONTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * File Name : qmonkey - com.rocket.db.dssc.entity.hbase
 * CopyRright (c) 1949-xxxx:
 * File Number：
 * Author：gwd
 * Date：on 6/17/2017
 * Modify：gwd
 * Time ：
 * Comment：
 * Description：
 * Version：
 */
public class MetaStructure {

    private String table;
    private HashMap<String, List<String>> columns;
    private JSONObject jsonObject;

    public MetaStructure(){
        columns=new HashMap<>();
        jsonObject=new JSONObject();
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public HashMap<String, List<String>> getColumns() {
        return columns;
    }

    public void setColumns(HashMap<String, List<String>> columns) {
        this.columns = columns;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public static void main(String args[]){
        MetaStructure metaStructure=new MetaStructure();
        metaStructure.setTable("test");
       // HashMap<String,String> hashMap=new HashMap<>();
        //hashMap.put("qualifier1")
        List<String> list=new ArrayList<>();
        list.add("qualifier1");
        list.add("qualifier2");
        metaStructure.getColumns().put("family1",list);
        System.out.println(JSONTool.toJson(metaStructure));
    }
}
