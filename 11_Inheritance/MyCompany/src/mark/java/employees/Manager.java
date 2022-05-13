package mark.java.employees;

import mark.java.Company;

public class Manager implements Employee{

    private int fixedSalary;
    private final Company company;


    public Manager(int fixedSalary, Company company) {
        this.fixedSalary = fixedSalary;
        this.company = company;
    }


    @Override
    public int getMonthSalary() {
        return fixedSalary + (int) (115000 + Math.random() * 25000);
    }

    public int getFixedSalary() {
        return fixedSalary;
    }

    public void setFixedSalary(int fixedSalary) {
        this.fixedSalary = fixedSalary;
    }
}
