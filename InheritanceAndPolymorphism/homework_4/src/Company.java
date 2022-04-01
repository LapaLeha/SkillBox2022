import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {

    List<Employee> employeeList=new ArrayList<Employee>();

    public void heir(Employee employee){
        employee.setCompany(this);
        employeeList.add(employee);
    }
    public void heirAll(List<Employee>employee){
        for (Employee e:employee){
            heir(e);
        }
    }

    public void  fire(Employee employee){
        employee.setCompany(null);
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
        int salesAll=0;
        for(Employee e:employeeList){
            if (e instanceof Manager){
               salesAll+= ((Manager) e).getSale();
            }
        }
        return salesAll;
    }

    public List<Employee> getEmployeeList() {
        return new ArrayList<>(employeeList);
    }
}
