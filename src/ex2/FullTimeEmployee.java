package ex2;

import java.util.Locale;

import util.Money;

public class FullTimeEmployee extends Employee {
    private static final int STANDARD_DAYS = 26;   // full salary for 26 workdays

    private double baseSalary;
    private double allowance;
    private int workdays;

    public FullTimeEmployee(String id, String fullName, String department,
                            double baseSalary, double allowance, int workdays) {
        super(id, fullName, department);
        if (workdays < 0 || workdays > 31) {
            throw new IllegalArgumentException("Workdays must be from 0 to 31.");
        }
        this.baseSalary = requireNotNegative(baseSalary, "Base salary");
        this.allowance = requireNotNegative(allowance, "Allowance");
        this.workdays = workdays;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public double getAllowance() {
        return allowance;
    }

    public int getWorkdays() {
        return workdays;
    }

    // base salary x min(workdays, 26) / 26 + allowance
    @Override
    public double calculateSalary() {
        return baseSalary * Math.min(workdays, STANDARD_DAYS) / STANDARD_DAYS + allowance;
    }

    @Override
    public String getType() {
        return "Full-time";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        String details = "base " + Money.plain(baseSalary) + ", allowance "
                + Money.plain(allowance) + ", " + workdays + " days";
        System.out.print(String.format(Locale.US, " | %-46s", details));
    }
}
