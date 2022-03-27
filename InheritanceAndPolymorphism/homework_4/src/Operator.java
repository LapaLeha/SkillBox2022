public class Operator implements Employee{
    private int wage=40_000;

    @Override
    public int getMonthSalary() {
        return wage;
    }
}
