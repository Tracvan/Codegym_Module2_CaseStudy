package model;

public class Code {
    private String code;
    private double cost;
    private boolean avaiable;

    public Code(String code, double cost, boolean avaiable) {
        this.code = code;
        this.cost = cost;
        this.avaiable = avaiable;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isAvaiable() {
        return avaiable;
    }

    public void setAvaiable(boolean avaiable) {
        this.avaiable = avaiable;
    }
}
