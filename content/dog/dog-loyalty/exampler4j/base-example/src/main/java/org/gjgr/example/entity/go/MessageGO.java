package org.gjgr.example.entity.go;

/**
 * File Name : essen-apps - com.essen.apps.moniter.entity
 * CopyRright (c) 1949-xxxx:
 * File Number：
 * Author：gwd
 * Date：on 8/2/2017
 * Modify：gwd
 * Time ：
 * Comment：
 * Description：
 * Version：
 */
public class MessageGO {
    private int code;
    private String status;
    private String description;
    private Object message;
    private Object info;
    private Object more;

    public String getDescription() {
        return description;
    }

    public MessageGO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Object getMessage() {
        return message;
    }

    public MessageGO setMessage(Object message) {
        this.message = message;
        return this;
    }

    public Object getMore() {
        return more;
    }

    public MessageGO setMore(Object more) {
        this.more = more;
        return this;
    }

    public int getCode() {
        return code;
    }

    public MessageGO setCode(int code) {
        this.code = code;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public MessageGO setStatus(String status) {
        this.status = status;
        return this;
    }

    public Object getInfo() {
        return info;
    }

    public MessageGO setInfo(Object info) {
        this.info = info;
        return this;
    }
}
