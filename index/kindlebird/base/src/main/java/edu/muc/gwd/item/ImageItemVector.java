package edu.muc.gwd.item;

public class ImageItemVector<T extends Number> implements ItemInter, Comparable<ImageItemVector> {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4519457391951715668L;
    /**
     * 标志ID
     */
    private int id;
    /**
     * 真实的数据
     */
    private T data[];

    /**
     * 构造方法
     */
    public ImageItemVector() {
        super();
    }

    public T[] getData() {
        return data;
    }

    public void setData(T[] data) {
        this.data = data;
    }

    public int getLength() {
        if (null != this.data) {
            return this.data.length;
        } else {
            return 0;
        }
    }

    public void setLength(int length) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        StringBuilder builder = new StringBuilder();
        builder.append("ID:" + this.id + "; Data:\n");

        for (int i = 1; i <= this.data.length; i++) {
            T t = data[i - 1];
            builder.append(t.toString() + " ");
            if (i % 28 == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public String printImage() {
        // TODO Auto-generated method stub
        StringBuilder builder = new StringBuilder();
        builder.append("ID:" + this.id + "; Data:\n");

        for (int i = 1; i <= this.data.length; i++) {
            T t = data[i - 1];
            builder.append(t.toString() + " ");
            if (i % 28 == 0) {
                builder.append("\n");
            }
        }
        return builder.toString().replace('0', ' ');
    }

    @Override
    public int compareTo(ImageItemVector o) {
        return (this.getId() - o.getId());
    }

}
