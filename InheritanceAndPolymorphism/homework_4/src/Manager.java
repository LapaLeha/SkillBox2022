public class Manager implements Employee{
    private int wage=45_000;
    private int sale;

    public Manager() {
        sale=(int) Math.random()*(140_000-115_000)+115_000;
        wage+= sale*0.05;
    }

    @Override
    public int getMonthSalary() {
        return wage;
    }
}
