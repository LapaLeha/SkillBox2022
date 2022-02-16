package ru.skillbox;

public class ComputerCPU {
    private final double frequency;
    private final String manufacturer;
    private final NumberOfCores numberOfCores;
    private final double weight;

    public ComputerCPU(double frequency, String manufacturer, NumberOfCores numberOfCores, double weight) {
        this.frequency = frequency;
        this.manufacturer = manufacturer;
        this.weight = weight;
        this.numberOfCores = numberOfCores;
    }

    public ComputerCPU setFrequency (double frequency){
        return new ComputerCPU(frequency,manufacturer,numberOfCores,weight);
    }
    public ComputerCPU setManufacturer (String manufacturer){
        return new ComputerCPU(frequency,manufacturer,numberOfCores,weight);
    }
    public ComputerCPU setNumberOfCores (NumberOfCores numberOfCores){
        return new ComputerCPU (frequency,manufacturer,numberOfCores,weight);
    }
    public ComputerCPU setWeight (double weight){
        return new ComputerCPU (frequency,manufacturer,numberOfCores,weight);
    }

    public double getFrequency() {
        return frequency;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getWeight() {
        return weight;
    }

    public NumberOfCores getNumberOfCores() {
        return numberOfCores;
    }


}
