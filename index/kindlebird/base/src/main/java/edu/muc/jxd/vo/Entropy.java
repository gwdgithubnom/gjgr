package edu.muc.jxd.vo;

public class Entropy implements Comparable<Entropy> {

    public double dc;

    public double entropy;

    public Entropy() {
    }

    public Entropy(double dc, double entropy) {
        this.dc = dc;
        this.entropy = entropy;
    }

    @Override
    public int compareTo(Entropy o) {
        // TODO Auto-generated method stub
        return (int) ((this.entropy - o.entropy) * 1000);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.dc + ":" + this.entropy;
    }

}
