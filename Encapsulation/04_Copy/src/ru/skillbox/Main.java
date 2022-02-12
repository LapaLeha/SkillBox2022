package ru.skillbox;

public class Main {

    public static void main(String[] args) {
        InfoLoad load = new InfoLoad(32.2,"ул. Пушкина,15",true,"23ф24",false);
        InfoLoad load2=load.setDeliveryAddressAndWeight(23.43,"ул. Тургенева,29");
        System.out.println(load.getWeight()+" "+load.getDeliveryAddress()+" "+
                load.isInvert()+" "+load.getRegistrationNumber()+" "+load.isFragile());
        System.out.println(load2.getWeight()+" "+load2.getDeliveryAddress()+" "+load2.isInvert()+" "+
                load2.getRegistrationNumber()+" "+load2.isFragile());

    }
}
