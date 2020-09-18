package entity;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 8106166529605963550L;
    private int pid;
    private String pname;
    private int price;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
