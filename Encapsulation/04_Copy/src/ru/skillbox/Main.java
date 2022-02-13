package ru.skillbox;

public class Main {

    public static void main(String[] args) {
        Dimensions dimensions1 = new Dimensions(12.3,234,1);
        Dimensions dimensions2= dimensions1.setLenghtWidthHeight(1,3,7);
        InfoLoad load = new InfoLoad(dimensions2,32.2,"ул. Пушкина,15",
                true,"23ф24",false);
        InfoLoad load2=load.setDimensionsDeliveryAddressAndWeight(dimensions2,23.43,"ул. Тургенева,29");
        System.out.println(load.getDimensions()+" "+load.getWeight()+" "+load.getDeliveryAddress()+" "+
                load.isInvert()+" "+load.getRegistrationNumber()+" "+load.isFragile());
        System.out.println(load2.getWeight()+" "+load2.getDeliveryAddress()+" "+load2.isInvert()+" "+
                load2.getRegistrationNumber()+" "+load2.isFragile());

    }
}
