package ru.skillbox;

public class ArithmeticCalculator {

    private double valueA;
    private double valueB;

    public double getValueA() {
        return valueA;
    }

    public double getValueB() {
        return valueB;
    }

    public ArithmeticCalculator(double valueA, double valueB) {
        this.valueA = valueA;
        this.valueB = valueB;
    }

    public double calculate(Operation type) {
        double result = 0;
        if (type == Operation.ADD) {
            result = getValueA() + getValueB();
        }
        if (type == Operation.SUBTRACT) {
            result = getValueA() - getValueB();
        }
        if (type == Operation.MULTIPLY) {
            result = getValueA() * getValueB();
        }
        return result;
    }
}
