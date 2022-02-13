package ru.skillbox;

public class Dimensions {
    private final double length;
    private final double width;
    private final double height;

    public Dimensions(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double volume(){
        return length*width*height;
    }

    public Dimensions setLenght (double length){
        return new Dimensions(length,width,height);
    }
    public Dimensions setWidth (double width){
        return new Dimensions(length,width,height);
    }
    public Dimensions setHeight (double height){
        return new Dimensions(length,width,height);
    }
    public Dimensions setLenghtWidthHeight (double length,double width,double height){
        return new Dimensions(length,width,height);
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
