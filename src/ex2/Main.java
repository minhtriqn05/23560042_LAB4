package ex2;

import java.util.ArrayList;
import java.util.Scanner;

import util.ConsoleInput;
import util.Money;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Company company = new Company("ABC Company");
        int choice;
        do {
            System.out.println();
            System.out.println("===== EMPLOYEE PAYROLL MANAGEMENT - " + company.getName()
                    + " =====");
            System.out.println("1. Add employees | 2. Display payroll | 3. Find by ID"
                    + " | 4. Find by name + department");
            System.out.println("5. Find by department | 6. Statistics | 0. Exit");
            choice = ConsoleInput.readInt(sc, "Your choice: ", 0, 6);
            if (choice >= 2 && choice <= 6 && company.isEmpty()) {
                System.out.println("The list is empty, please add employees first.");
                continue;
            }
            switch (choice) {
                case 1:
                    addEmployees(sc, company);
                    break;
                case 2:
                    company.displayPayroll();
                    break;
                case 3:
                    findById(sc, company);
                    break;
                case 4:
                    findByNameAndDepartment(sc, company);
                    break;
                case 5:
                    findByDepartment(sc, company);
                    break;
                case 6:
                    showStatistics(company);
                    break;
                default:
                    System.out.println("Goodbye!");
            }
        } while (choice != 0);
        sc.close();
    }

    private static void addEmployees(Scanner sc, Company company) {
        int n = ConsoleInput.readInt(sc, "Number of employees to add: ", 1, 100);
        for (int i = 1; i <= n; i++) {
            System.out.println("--- Employee " + i + " ---");
            String type = ConsoleInput.readChoice(sc,
                    "Type (F = Full-time, P = Part-time): ", "F", "P");
            String id = readNewId(sc, company);
            String name = ConsoleInput.readText(sc, "Full name: ");
            String department = ConsoleInput.readText(sc, "Department: ");
            Employee e;                        // superclass type
            if (type.equals("F")) {
                double base = ConsoleInput.readDouble(sc, "Base salary (VND): ", 0);
                double allowance = ConsoleInput.readDouble(sc, "Allowance (VND): ", 0);
                int workdays = ConsoleInput.readInt(sc, "Workdays (0-31): ", 0, 31);
                e = new FullTimeEmployee(id, name, department, base, allowance, workdays);
            } else {
                double hours = ConsoleInput.readDouble(sc, "Hours worked: ", 0);
                double rate = ConsoleInput.readDouble(sc, "Hourly rate (VND): ", 0);
                e = new PartTimeEmployee(id, name, department, hours, rate);
            }
            company.addEmployee(e);
            System.out.println("  Added " + e.getType() + " employee " + e.getId()
                    + ", salary = " + Money.vnd(e.calculateSalary()));
        }
    }

    // Asks again until the ID is not used by another employee.
    private static String readNewId(Scanner sc, Company company) {
        while (true) {
            String id = ConsoleInput.readText(sc, "Employee ID: ");
            if (company.findEmployee(id) == null) {
                return id;
            }
            System.out.println("  ID " + id + " already exists, please enter another ID.");
        }
    }

    private static void findById(Scanner sc, Company company) {
        String id = ConsoleInput.readText(sc, "Employee ID: ");
        Employee e = company.findEmployee(id);                 // overload 1
        if (e == null) {
            System.out.println("  No employee has the ID " + id + ".");
        } else {
            Company.printHeader();
            Company.printRow(e);
        }
    }

    private static void findByNameAndDepartment(Scanner sc, Company company) {
        String name = ConsoleInput.readText(sc, "Full name: ");
        String department = ConsoleInput.readText(sc, "Department: ");
        printResult(company.findEmployee(name, department),    // overload 2
                "No employee named " + name + " works in " + department + ".");
    }

    private static void findByDepartment(Scanner sc, Company company) {
        String department = ConsoleInput.readText(sc, "Department: ");
        printResult(company.findByDepartment(department),
                "No employee works in " + department + ".");
    }

    private static void printResult(ArrayList<Employee> list, String notFoundMessage) {
        if (list.isEmpty()) {
            System.out.println("  " + notFoundMessage);
        } else {
            System.out.println("  Found " + list.size() + " employee(s):");
            Company.printList(list);
        }
    }

    private static void showStatistics(Company company) {
        System.out.println("Number of employees: " + company.size()
                + " (Full-time: " + company.count("Full-time")
                + ", Part-time: " + company.count("Part-time") + ")");
        System.out.println("Total payroll      : " + Money.vnd(company.totalPayroll()));
        Employee top = company.findHighestSalary();
        System.out.println("Highest salary     : " + top.getId() + " - " + top.getFullName()
                + " (" + top.getType() + ", " + top.getDepartment() + "), "
                + Money.vnd(top.calculateSalary()));
    }
}
