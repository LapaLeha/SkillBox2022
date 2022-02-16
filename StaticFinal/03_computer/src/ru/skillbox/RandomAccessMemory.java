package ru.skillbox;

public class RandomAccessMemory {
    private final String type;
    private final double volume;
    private final double weight;

    public RandomAccessMemory(String type, double volume, double weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public double getVolume() {
        return volume;
    }

    public double getWeight() {
        return weight;
    }
    public RandomAccessMemory setType (String type){
        return new RandomAccessMemory(type,volume,weight);
    }
    public RandomAccessMemory setVolume (double volume){
        return new RandomAccessMemory(type,volume,weight);
    }
    public RandomAccessMemory setWeight (double weight){
        return new RandomAccessMemory(type,volume,weight);
    }
}
