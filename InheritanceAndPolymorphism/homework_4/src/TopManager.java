    public class TopManager extends Company implements Employee {
    private int wage = 60_000;
    static Company company;
    int a;


    @Override
    public void heir(Employee employee) {
        this.company=(Company)employee;
        super.heir(employee);
    }

    @Override
    public int getMonthSalary() {
        int salesAll = Manager.getSalesAll();
        if (salesAll > 10_000_000) {
            double result = wage+1.5*wage;
            return (int)result;
        }
        return wage;
    }
}
