package ex2;

import java.util.Locale;

import util.Money;

public class PartTimeEmployee extends Employee {
    private double hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String id, String fullName, String department,
                            double hoursWorked, double hourlyRate) {
        super(id, fullName, department);
        this.hoursWorked = requireNotNegative(hoursWorked, "Hours worked");
        this.hourlyRate = requireNotNegative(hourlyRate, "Hourly rate");
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    // hours worked x hourly rate
    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }

    @Override
    public String getType() {
        return "Part-time";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        String details = Money.plain(hoursWorked) + " hours x "
                + Money.plain(hourlyRate) + " VND/hour";
        System.out.print(String.format(Locale.US, " | %-46s", details));
    }
}
