package ex2;

import java.util.ArrayList;
import java.util.Locale;

import util.Money;

/**
 * Keeps all employees in one list of the abstract type Employee.
 */
public class Company {
    private String name;                       // name of the company
    private ArrayList<Employee> employees = new ArrayList<>();

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Adds the employee only if the ID is not used yet.
    public boolean addEmployee(Employee e) {
        if (findEmployee(e.getId()) != null) {
            return false;
        }
        employees.add(e);
        return true;
    }

    // Overloading 1: search by ID (IDs are unique, so at most one result).
    public Employee findEmployee(String id) {
        for (Employee e : employees) {
            if (e.getId().equalsIgnoreCase(id.trim())) {
                return e;
            }
        }
        return null;
    }

    // Overloading 2: search by full name AND department; several people may match.
    public ArrayList<Employee> findEmployee(String name, String department) {
        ArrayList<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getFullName().equalsIgnoreCase(name.trim())
                    && e.getDepartment().equalsIgnoreCase(department.trim())) {
                result.add(e);
            }
        }
        return result;
    }

    // All employees of a department, not case-sensitive ("it" finds "IT").
    public ArrayList<Employee> findByDepartment(String department) {
        ArrayList<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getDepartment().equalsIgnoreCase(department.trim())) {
                result.add(e);
            }
        }
        return result;
    }

    public boolean isEmpty() {
        return employees.isEmpty();
    }

    public static void printHeader() {
        System.out.println("ID     | Type      | Full name          | Department  "
                + "| Details                                        |           Salary");
    }

    // One payroll row; both calls go through an Employee reference.
    public static void printRow(Employee e) {
        e.displayInfo();
        System.out.println(String.format(Locale.US, " | %16s",
                Money.vnd(e.calculateSalary())));
    }

    public static void printList(ArrayList<Employee> list) {
        printHeader();
        for (Employee e : list) {
            printRow(e);
        }
    }

    public void displayPayroll() {
        printList(employees);
    }

    public int count(String type) {
        int result = 0;
        for (Employee e : employees) {
            if (e.getType().equals(type)) {
                result++;
            }
        }
        return result;
    }

    public int size() {
        return employees.size();
    }

    public double totalPayroll() {
        double total = 0;
        for (Employee e : employees) {
            total += e.calculateSalary();      // the right formula is chosen at runtime
        }
        return total;
    }

    // Employee with the highest salary (first one in a tie); null if empty.
    public Employee findHighestSalary() {
        Employee best = null;
        for (Employee e : employees) {
            if (best == null || e.calculateSalary() > best.calculateSalary()) {
                best = e;
            }
        }
        return best;
    }
}
