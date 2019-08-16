package edu.muc.jxd.vo;

public class Result implements Comparable<Result> {

    private int id;

    private int p;

    private int delta;

    private int addResult;

    private int mutiplyResult;

    public Result() {

    }

    public Result(int id, int p, int delta) {
        this.id = id;
        this.p = p;
        this.delta = delta;
        this.addResult = this.p + this.delta;
        this.mutiplyResult = this.p * this.delta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public int getAddResult() {
        return addResult;
    }

    public void setAddResult(int addResult) {
        this.addResult = addResult;
    }

    public int getMutiplyResult() {
        return mutiplyResult;
    }

    public void setMutiplyResult(int mutiplyResult) {
        this.mutiplyResult = mutiplyResult;
    }

    @Override
    public int compareTo(Result o) {
        // TODO Auto-generated method stub
        return (this.mutiplyResult + this.addResult - (o.addResult + o.mutiplyResult));
    }

}
