public class Manager implements Employee {
    private int wage = 45_000;
    private int sale;
    private static int salesAll;

    public Manager() {
        sale = (int) Math.random() * (140_000 - 115_000) + 115_000;
        salesAll = salesAll + sale;
    }

    public static int getSalesAll() {
        return salesAll;
    }

    @Override
    public int getMonthSalary() {
        wage += sale * 0.05;
        return wage;
    }
}
