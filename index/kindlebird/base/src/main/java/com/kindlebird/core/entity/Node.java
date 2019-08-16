package com.kindlebird.core.entity;

/**
 * Created by gwd on 11/4/2016.
 */
public class Node implements Cloneable {

    private double x = 0;
    private double y = 0;

    /**
     * To build a new construct method to init node value
     *
     * @param x
     * @param y
     */
    public Node(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Node() {

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (Double.compare(node.x, x) != 0) return false;
        return Double.compare(node.y, y) == 0;

    }


    public Node clone() {
        Node node = new Node(this.x, this.y);
        return node;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
