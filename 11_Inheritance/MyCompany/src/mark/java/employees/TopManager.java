package mark.java.employees;

import mark.java.Company;

public class TopManager implements Employee{
    private int fixedSalary;
    private final Company company;

    public TopManager(int fixedSalary, Company company) {
        this.fixedSalary = fixedSalary;
        this.company = company;
    }


    @Override
    public int getMonthSalary() {
        return (int) (fixedSalary +  ((company.getIncome() > 10000000) ? fixedSalary * 1.5 : 0));
    }

    public int getFixedSalary() {
        return fixedSalary;
    }

    public void setFixedSalary(int fixedSalary) {
        this.fixedSalary = fixedSalary;
    }
}
