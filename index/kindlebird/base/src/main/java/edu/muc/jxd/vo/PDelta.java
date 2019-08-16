package edu.muc.jxd.vo;

public class PDelta implements Comparable<PDelta> {

    private Integer ID;

    private Integer p;

    private Integer d;

    private Integer pd;

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "ID:" + this.ID + "\t" + p;
    }

    public PDelta(Integer iD, Integer p) {
        ID = iD;
        this.p = p;
    }

    public PDelta(Integer iD, Integer p, Integer d) {
        ID = iD;
        this.p = p;
        this.d = d;
        this.pd = p * d;
    }

    @Override
    public int compareTo(PDelta arg0) {
        // TODO Auto-generated method stub
        return (int) (this.pd - arg0.pd);
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer iD) {
        ID = iD;
    }

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }

    public Integer getPd() {
        return pd;
    }

    public void setPd(Integer pd) {
        this.pd = pd;
    }

}
