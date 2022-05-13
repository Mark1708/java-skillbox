package mark.java.employees;

import mark.java.Company;

public class Operator implements Employee{
    private int fixedSalary;
    private final Company company;


    public Operator(int fixedSalary, Company company) {
        this.fixedSalary = fixedSalary;
        this.company = company;
    }


    @Override
    public int getMonthSalary() {
        return fixedSalary;
    }

    public int getFixedSalary() {
        return fixedSalary;
    }

    public void setFixedSalary(int fixedSalary) {
        this.fixedSalary = fixedSalary;
    }
}
