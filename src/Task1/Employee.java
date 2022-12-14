package Task1;

import java.io.Serializable;

public class Employee implements Serializable {
    private String surname;
    private int age;
    private String jobTitle;
    private double salary;

    public Employee() {
    }

    public Employee(String surname, int age, String jobTitle, double salary) {
        this.surname = surname;
        this.age = age;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
