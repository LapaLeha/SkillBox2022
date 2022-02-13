package ru.skillbox;

public class  InfoLoad {
    private final Dimensions dimensions;
    private final double weight;
    private final String deliveryAddress;
    private final boolean invert;
    private final String registrationNumber;
    private final boolean fragile;

    public InfoLoad(Dimensions dimensions, double weight, String deliveryAddress,
                    boolean invert, String registrationNumber, boolean fragile) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.invert = invert;
        this.registrationNumber = registrationNumber;
        this.fragile = fragile;
    }

    public InfoLoad setDimensions (Dimensions dimensions) {
        return new InfoLoad(dimensions,weight, deliveryAddress, invert, registrationNumber, fragile);
    }

    public InfoLoad setDeliveryAddress(String deliveryAddress) {
        return new InfoLoad(dimensions,weight, deliveryAddress, invert, registrationNumber, fragile);
    }

    public InfoLoad setWeight(double weight) {
        return new InfoLoad(dimensions,weight, deliveryAddress, invert, registrationNumber, fragile);
    }

    public InfoLoad setDimensionsDeliveryAddressAndWeight(Dimensions dimensions,double weight,String deliveryAddress) {
        return new InfoLoad(dimensions,weight, deliveryAddress, invert, registrationNumber, fragile);
    }

    public double getWeight() {
        return weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public boolean isInvert() {
        return invert;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public boolean isFragile() {
        return fragile;
    }

    public String getDimensions() {
        return dimensions.getLength()+"X"+dimensions.getWidth()+"X"+dimensions.getHeight();
    }
}
