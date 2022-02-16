package ru.skillbox;

public class InformationStorage {
    private final TypeInformationStorage typeInformationStorage;
    private final double memory;
    private final double weight;

    public InformationStorage(TypeInformationStorage typeInformationStorage, double memory, double weight) {
        this.typeInformationStorage = typeInformationStorage;
        this.memory = memory;
        this.weight = weight;
    }

    public InformationStorage setTypeInformationStorage (TypeInformationStorage typeInformationStorage){
        return new InformationStorage(typeInformationStorage,memory,weight);
    }
    public InformationStorage setMemory (double memory){
        return new InformationStorage(typeInformationStorage,memory,weight);
    }
    public InformationStorage setWeight (double weight){
        return new InformationStorage(typeInformationStorage,memory,weight);
    }

    public TypeInformationStorage getTypeInformationStorage() {
        return typeInformationStorage;
    }

    public double getMemory() {
        return memory;
    }

    public double getWeight() {
        return weight;
    }
}
