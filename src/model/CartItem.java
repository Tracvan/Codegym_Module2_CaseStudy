package model;

public class CartItem {
    private String now;
    private int id;
    private String name;
    private int quanity;
    private double unitPrice;
    private double totalAmount;
    private String type;
    private String username;
    public CartItem(){};
    public CartItem(String now, int id, String name, int quanity, double unitPrice, String type, String username) {
        this.now = now;
        this.id = id;
        this.name = name;
        this.quanity = quanity;
        this.unitPrice = unitPrice;
        this.totalAmount = this.quanity * this.unitPrice;
        this.type = type;
        this.username = username;
    }

    public CartItem(int id, String name, int quanity, double unitPrice, String type, String username) {
        this.id = id;
        this.name = name;
        this.quanity = quanity;
        this.unitPrice = unitPrice;
        this.totalAmount = this.quanity * this.unitPrice;
        this.type = type;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
