package ru.skillbox;

public class Main {
    public static void main(String[] args) {
        ArithmeticCalculator ar1 = new ArithmeticCalculator(3.0,6);

        System.out.println(ar1.getValueB());
        System.out.println(ar1.calculate(Operation.SUBTRACT));
    }

}
