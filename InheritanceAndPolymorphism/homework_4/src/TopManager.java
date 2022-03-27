public class TopManager implements Employee {
    private int wage = 60_000;
    private Company company;

    public TopManager(Company company) {
        this.company = company;
    }

    @Override
    public int getMonthSalary() {
        int salesAll = company.getSalesAll();
        if (salesAll > 10_000_000) {
            double result = wage+1.5*wage;
            return (int)result;
        }
        return wage;
    }
}
