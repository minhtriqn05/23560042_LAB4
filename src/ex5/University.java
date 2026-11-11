package ex5;

import java.util.ArrayList;

/**
 * Keeps theory and lab courses together in one list of type CourseRegistration.
 */
public class University {
    private String name;                       // name of the university
    private ArrayList<CourseRegistration> courses = new ArrayList<>();

    public University(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addCourse(CourseRegistration c) {
        if (findByCode(c.getCourseCode()) != null) {
            return false;
        }
        courses.add(c);
        return true;
    }

    public CourseRegistration findByCode(String code) {
        for (CourseRegistration c : courses) {
            if (c.getCourseCode().equalsIgnoreCase(code.trim())) {
                return c;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return courses.isEmpty();
    }

    public static void printHeader() {
        System.out.println("Code    | Type   | Course name                  | Cr | Stud "
                + "| Tuition/student  |   Expected total");
    }

    public void displayAll() {
        printHeader();
        for (CourseRegistration c : courses) {
            c.displayInfo();                   // LabCourse uses its overridden version
        }
    }

    public int count(String type) {
        int result = 0;
        for (CourseRegistration c : courses) {
            if (c.getType().equals(type)) {
                result++;
            }
        }
        return result;
    }

    public double totalCollection() {
        double total = 0;
        for (CourseRegistration c : courses) {
            total += c.expectedCollection();
        }
        return total;
    }

    // Course with the largest expected collection (first one in a tie).
    public CourseRegistration findLargestCollection() {
        CourseRegistration best = null;
        for (CourseRegistration c : courses) {
            if (best == null || c.expectedCollection() > best.expectedCollection()) {
                best = c;
            }
        }
        return best;
    }

    // Overloading 1: average tuition per student over ALL registered students,
    // weighted by student counts = total collected / total students.
    // Returns -1 when nobody has registered (the caller prints N/A).
    public double averageTuition() {
        return averageTuition("All");
    }

    // Overloading 2: the same average for one type only ("Theory" or "Lab").
    public double averageTuition(String type) {
        double collected = 0;
        int students = 0;
        for (CourseRegistration c : courses) {
            if (type.equals("All") || c.getType().equals(type)) {
                collected += c.expectedCollection();
                students += c.getRegisteredStudents();
            }
        }
        if (students == 0) {
            return -1;
        }
        return collected / students;
    }
}
