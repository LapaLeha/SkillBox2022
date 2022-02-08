public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Milk", 40,4,35.4);
        basket.add("Картошка",30,15,2.3);
        basket.add("Пельмени",150,2,3.3);
        basket.print("Покупки");
    }
}
