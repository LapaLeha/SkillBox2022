import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {

    private int salesAll=15_000_000;
    List<Employee> employeeList=new ArrayList<Employee>();

    public void heir(Employee employee){
        employeeList.add(employee);
    }
    public void heirAll(List<Employee>employee){
        for (Employee e:employee){
            heir(e);
        }
    }

    public void  fire(Employee employee){
        employeeList.remove(employee);
    }

    public List<Employee> getTopSalaryStaff(int count){
        if (count>0&&count<employeeList.size()) {
            Collections.sort(employeeList);
            Collections.reverse(employeeList);
            return employeeList.subList(0,count);
        }
        else{
            System.out.println("ошибка");
            return employeeList.subList(0,0);
        }
    }

   public List<Employee> getLowestSalaryStaff(int count){
       if (count>0&&count<employeeList.size()) {
           Collections.sort(employeeList);
           return employeeList.subList(0,count);
       }else{
           System.out.println("ошибка");
           return employeeList.subList(0,0);
       }
    }

    public int getSalesAll() {
        return salesAll;
    }

    public List<Employee> getEmployeeList() {
        return new ArrayList<>(employeeList);
    }
}
