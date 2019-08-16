package com.kindlebird.core.entity.view;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by gwd on 2016/4/28.
 */
public class Message {
    private String code="A00000";
    private Object data;
    private String msg="Success";
    private Message(){
    }

    public static Message getListInstance(){
        Message  message=new Message();
        List<Object> data=new LinkedList<Object>();
        message.setData(data);
        return  message;
    }

    public static Message getHashMapInstance(){
        Message  message=new Message();
        Map<String,Object> data=new HashMap<String,Object>();
        message.setData(data);
        return  message;
    }

    public static Message getInstance(){
         Message  message=new Message();
        Object data=new Object();
        message.setData(data);
        return  message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object datas) {
        this.data = datas;
    }

}
