package com.kindlebird.core.entity.view;

import com.kindlebird.core.entity.ItemStyle;
import com.kindlebird.core.entity.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by gwd on 11/4/2016.
 */
public class NodeList {
    private String name = "undefine";
    private String type = "scatter";
    private boolean large = true;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    private String symbol = "circle";
    private int symbolSize = 13;
    private List<Node> data = new ArrayList<Node>();
    private ItemStyle itemStyle = new ItemStyle();

    public ItemStyle getItemStyle() {
        return itemStyle;
    }


    public void setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
    }

    public boolean add(Node node) {
        data.add(0, node);
        return true;
    }

    public boolean setData(List<Node> data) {
        this.data = data;
        return true;
    }

    public List<Node> getData() {
        return data;
    }

    public boolean remove(Node node) {
        Iterator<Node> iterable = data.iterator();
        while (iterable.hasNext()) {
            Node temp = iterable.next();
            if (node.equals(temp)) {
                data.remove(temp);
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isLarge() {
        return large;
    }

    public void setLarge(boolean large) {
        this.large = large;
    }

    public int getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(int symbolSize) {
        this.symbolSize = symbolSize;
    }
}
