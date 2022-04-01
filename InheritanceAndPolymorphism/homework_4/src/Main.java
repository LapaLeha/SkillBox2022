import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();

        List<Employee> employee = new ArrayList<>();

        for (int i = 0; i < 180; i++) {
            company.heir (new Operator());
        }

        for (int i = 0; i < 80; i++) {
            employee.add(new Manager());
        }
        for (int i = 0; i < 10; i++) {
            employee.add(new TopManager(company));
        }
        company.heirAll(employee);


        System.out.println("Список самых больших зарплат");
        List<Employee> topSalaryStaff=company.getTopSalaryStaff(15);
        for (Employee e:topSalaryStaff){
            System.out.println(e.getMonthSalary());
        }
        System.out.println("Список самых маленьких зарплат");
        List<Employee> lowestSalaryStaff=company.getLowestSalaryStaff(30);
        for (Employee e:lowestSalaryStaff){
            System.out.println(e.getMonthSalary());
        }

        List<Employee> employees1=company.getEmployeeList();

        System.out.println(employees1.size());
        for (int i=0;i<employees1.size();i+=2){
            company.fire(employees1.get(i));

        }
        System.out.println(company.getEmployeeList().size());


        System.out.println("Список самых больших зарплат");
        List<Employee> topSalaryStaff2=company.getTopSalaryStaff(15);
        for (Employee e:topSalaryStaff2){
            System.out.println(e.getMonthSalary());
        }
        System.out.println("Список самых маленьких зарплат");
        List<Employee> lowestSalaryStaff2=company.getLowestSalaryStaff(30);
        for (Employee e:lowestSalaryStaff2){
            System.out.println(e.getMonthSalary());
        }


    }
}
