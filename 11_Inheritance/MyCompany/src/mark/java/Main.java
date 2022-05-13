package mark.java;

import mark.java.employees.Employee;
import mark.java.employees.Manager;
import mark.java.employees.Operator;
import mark.java.employees.TopManager;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static int salaryGenerator(){
        return (int) (100 + Math.random() * 150) * 1000;
    }

    private static List<Employee> employeesGenerator(int count, Company company){
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            switch (i % 3){
                case 0:
                    employees.add(new Manager(salaryGenerator(), company));
                    break;
                case 1:
                    employees.add(new TopManager(salaryGenerator(), company));
                    break;
                case 2:
                    employees.add(new Operator(salaryGenerator(), company));
                    break;
            }
        }
        return employees;
    }


    public static void main(String[] args) {
        Company skillbox = new Company();

        System.out.println("Найм одного сотрудника");
        skillbox.hire(new Manager(salaryGenerator(), skillbox));

        skillbox.getEmployees().forEach(employee -> System.out.println(employee.getMonthSalary()));

        System.out.println("Найм нескольких сотрудника");
        skillbox.hireAll(employeesGenerator(9, skillbox));

        System.out.println("Список по возрастанию");
        skillbox.getTopSalaryStaff(10).forEach(employee -> System.out.println(employee.getMonthSalary()));

        System.out.println("Увольнение сотрудника");
        skillbox.fire(skillbox.getEmployees().get(0));

        System.out.println("Список по убыванию");
        skillbox.getLowestSalaryStaff(10).forEach(employee -> System.out.println(employee.getMonthSalary()));
    }
}
