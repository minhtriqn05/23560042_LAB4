package ex2;

import java.util.Locale;

/**
 * Exercise 2: data shared by every employee. Salary depends on the kind of
 * contract, so calculateSalary() is abstract and Employee itself is abstract.
 */
public abstract class Employee {

    private String id;
    private String fullName;
    private String department;

    public Employee(String id, String fullName, String department) {
        this.id = requireText(id, "Employee ID");
        this.fullName = requireText(fullName, "Full name");
        this.department = requireText(department, "Department");
    }

    // Used by the subclasses too, so it is protected.
    protected static double requireNotNegative(double value, String field) {
        if (value < 0) {
            throw new IllegalArgumentException(field + " must be >= 0.");
        }
        return value;
    }

    private static String requireText(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " cannot be empty.");
        }
        return value.trim();
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDepartment() {
        return department;
    }

    public abstract double calculateSalary();

    // "Full-time" or "Part-time"
    public abstract String getType();

    // Prints the common columns of the payroll row (no line break).
    public void displayInfo() {
        System.out.print(String.format(Locale.US, "%-6s | %-9s | %-18s | %-11s",
                id, getType(), fullName, department));
    }
}
