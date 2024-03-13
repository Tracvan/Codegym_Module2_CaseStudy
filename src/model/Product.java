package model;

import controller.LoginController;
import service.AccountService;

public class Product {
    private int id;
    private String name;
    private int quanity;
    private double price;
    private String type;

    public Product(int id, String name, int quanity, double price, String type) {
        this.id = id;
        this.name = name;
        this.quanity = quanity;
        this.price = price;
        this.type = type;
    }
    public Product(int id, String name, int quanity, double price, String type, String username){
        this.id = id;
        this.name = name;
        this.quanity = quanity;
        this.price = price;
        username = LoginController.currentAccount;
    }

    public Product() {

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product has " +
                "id: " + id +
                ", name: '" + name  +
                ", quanity: " + quanity +
                ", price: " + price +
                ", type: " + type  ;
    }
}
