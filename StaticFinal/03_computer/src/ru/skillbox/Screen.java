package ru.skillbox;

public class Screen {
    private final double diagonal;
    private final TypeScreen typeScreen;
    private final double weight;

    public Screen(double diagonal, TypeScreen typeScreen, double weight) {
        this.diagonal = diagonal;
        this.typeScreen = typeScreen;
        this.weight = weight;
    }
    public Screen setDiagonal (double diagonal){
        return new Screen(diagonal,typeScreen,weight);
    }
    public Screen setTypeScreen (TypeScreen typeScreen){
        return new Screen(diagonal,typeScreen,weight);
    }
    public Screen setWeight (double weight){
        return new Screen(diagonal,typeScreen,weight);
    }

    public double getDiagonal() {
        return diagonal;
    }

    public TypeScreen getTypeScreen() {
        return typeScreen;
    }

    public double getWeight() {
        return weight;
    }
}
