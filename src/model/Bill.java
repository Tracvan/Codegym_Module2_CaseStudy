package model;


import java.util.List;

public class Bill {
    String now;
    String userName;
    List<CartItem> boughtList;

    public Bill(String now, String userName, List<CartItem> boughtList) {
        this.now = now;
        this.userName = userName;
        this.boughtList = boughtList;
    }

    public String getNow() {
        return now;
    }

    public List<CartItem> getBoughtList() {
        return boughtList;
    }
}
