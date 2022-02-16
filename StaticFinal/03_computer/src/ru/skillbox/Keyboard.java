package ru.skillbox;

public class Keyboard {
    private final String type;
    private final IlluminationKeyboard illuminationKeyboard;
    private final double weight;

    public Keyboard(String type, IlluminationKeyboard illuminationKeyboard, double weight) {
        this.type = type;
        this.illuminationKeyboard = illuminationKeyboard;
        this.weight = weight;
    }

    public Keyboard setType(String type) {
        return new Keyboard(type, illuminationKeyboard, weight);
    }

    public Keyboard setIlluminationKeyboard(IlluminationKeyboard illuminationKeyboard) {
        return new Keyboard(type, illuminationKeyboard, weight);
    }

    public Keyboard setweight(double weight) {
        return new Keyboard(type, illuminationKeyboard, weight);
    }

    public String getType() {
        return type;
    }

    public IlluminationKeyboard getIlluminationKeyboard() {
        return illuminationKeyboard;
    }

    public double getWeight() {
        return weight;
    }
}
