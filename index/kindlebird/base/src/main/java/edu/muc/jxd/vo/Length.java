package edu.muc.jxd.vo;

public class Length implements Comparable<Length> {

    private int ID;

    private int length;

    public Length(int iD, int length) {
        super();
        ID = iD;
        this.length = length;
    }

    @Override
    public int compareTo(Length o) {
        // TODO Auto-generated method stub
        return this.length - o.length;
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.ID + ":" + this.length;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


}
