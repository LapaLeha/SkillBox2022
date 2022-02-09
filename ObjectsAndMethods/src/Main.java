public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Milk", 40,4,35.4);
        basket.add("Картошка",30,15,2.3);
        basket.add("Пельмени",150,2,3.3);
        basket.print("Покупки");

        Arithmetic arithmetic = new Arithmetic(12, 15);
        int sum = arithmetic.sum();
        int product = arithmetic.product();
        int maxmum = arithmetic.maxumum();
        int minimum = arithmetic.minimum();
        System.out.println(sum);
        System.out.println(product);
        System.out.println(maxmum);
        System.out.println(minimum);

        Printer dox1 = new Printer("dsdf","dox1",34);
        System.out.println(dox1.sumNumberOfPagesForAllTime());

    }
}
