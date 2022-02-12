package ru.skillbox;

public class ProductChange {
    private final String name;
    private int price;
    private final int barCode;

    public ProductChange(String name, int barCode) {
        this.name = name;
        this.barCode = barCode;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
