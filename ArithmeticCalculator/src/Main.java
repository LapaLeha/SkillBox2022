public class Main {
    public static void main(String[] args) {
        ArithmeticCalculator ar1 = new ArithmeticCalculator(3.4,6);

        System.out.println(ar1.getValueB());
        System.out.println(ar1.calculate(Operation.MULTIPLY));
    }
}
