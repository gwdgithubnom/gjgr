package edu.bigdata.core.entity.alibaba;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gwd on 2016/4/17.
 */
public class FactorCollection {
    private String column1;
    private String column2;
    private String column3;
    private String column4;
    private String column5;
    private String column6;
    private String column7;
    private String column8;
    private String column9;
    private Map<String,String> columnNames=new HashMap<String,String>();
    private Map<String,String> columnTypes=new HashMap<String,String>();

    public Map<String, String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(Map<String, String> columnNames) {
        this.columnNames = columnNames;
    }

    public Map<String, String> getColumnTypes() {
        return columnTypes;
    }

    public void setColumnTypes(Map<String, String> columnTypes) {
        this.columnTypes = columnTypes;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    public String getColumn4() {
        return column4;
    }

    public void setColumn4(String column4) {
        this.column4 = column4;
    }

    public String getColumn5() {
        return column5;
    }

    public void setColumn5(String column5) {
        this.column5 = column5;
    }

    public String getColumn6() {
        return column6;
    }

    public void setColumn6(String column6) {
        this.column6 = column6;
    }

    public String getColumn7() {
        return column7;
    }

    public void setColumn7(String column7) {
        this.column7 = column7;
    }

    public String getColumn8() {
        return column8;
    }

    public void setColumn8(String column8) {
        this.column8 = column8;
    }

    public String getColumn9() {
        return column9;
    }

    public void setColumn9(String column9) {
        this.column9 = column9;
    }

    public String getColumnValue(String name) throws NoSuchFieldException {
        if(columnNames.containsKey(name)){
            return this.getClass().getField(name).toString();
        }else
            return null;
    }
}
