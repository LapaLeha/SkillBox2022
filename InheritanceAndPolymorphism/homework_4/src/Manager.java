public class Manager implements Employee {
    private int wage = 45_000;
    private int sale;
    //private int wageWithBonus = (int) (wage+sale * 0.05);

    public Manager() {
        sale = (int) Math.random() * (140_000 - 115_000) + 115_000;
    }

    @Override
    public int getMonthSalary() {
        return (int) (wage+sale * 0.05);
    }
    public int getSale (){
        return sale;
    }
}
