package mark.java;

import mark.java.employees.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Company {



    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getTopSalaryStaff(int count){
        if (count <= employees.size() && count > 0) {
            employees.sort(new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    return Integer.compare(o1.getMonthSalary(), o2.getMonthSalary()) * -1;
                }
            });
            return employees.subList(0, count);
        }
        System.out.println("Формат неправильный, поэтому выведу весь список");
        return employees;
    }

    public List<Employee> getLowestSalaryStaff(int count){
        if (count <= employees.size() && count > 0) {
            employees.sort(new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    return Integer.compare(o1.getMonthSalary(), o2.getMonthSalary());
                }
            });
            return employees.subList(0, count);
        }
        System.out.println("Формат неправильный, поэтому выведу весь список");
        return employees;
    }

    public void hire(Employee employee){
        employees.add(employee);
    }

    public void hireAll(List<Employee> employeeList){
        employees.addAll(employeeList);
    }

    public void fire(Employee employee) {
        if (employees.stream().anyMatch(employeeInList -> employeeInList.getMonthSalary() == employee.getMonthSalary())) {
            employees.remove(employees.stream().filter(employeeInList -> employeeInList.getMonthSalary() == employee.getMonthSalary()).findFirst().get());
        } else {
            System.out.println("Такого сотрудника нет");
        }
    }

    public int getIncome(){
        return (int) (12 + Math.random() * 20) * 1000000;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
