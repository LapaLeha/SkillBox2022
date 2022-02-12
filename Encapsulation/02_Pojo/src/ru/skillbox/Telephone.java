package ru.skillbox;

public class Telephone {
    String firm;
    String operatingSystem;
    int  price;
    boolean haveCamera = true;

    public Telephone(String firm, int price) {
        this.firm = firm;
        this.price = price;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isHaveCamera() {
        return haveCamera;
    }

    public void setHaveCamera(boolean haveCamera) {
        this.haveCamera = haveCamera;
    }
}
