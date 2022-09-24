package Task2;

import java.io.Serializable;
import java.util.ArrayList;

public class EmployeeCollection implements Serializable {
    private ArrayList<Employee> employees = new ArrayList<>();

    public EmployeeCollection() {
    }

    public EmployeeCollection(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
}
