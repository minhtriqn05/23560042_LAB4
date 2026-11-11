package ex5;

import java.util.Locale;

import util.Money;

/**
 * Exercise 5: registration data of one course. Theory and laboratory courses
 * charge different tuition, so tuitionPerStudent() is abstract.
 */
public abstract class CourseRegistration {

    private String courseCode;
    private String courseName;
    private int credits;
    private int registeredStudents;

    public CourseRegistration(String courseCode, String courseName, int credits,
                              int registeredStudents) {
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be empty.");
        }
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty.");
        }
        if (credits < 1 || registeredStudents < 0) {
            throw new IllegalArgumentException("Credits must be >= 1 and students >= 0.");
        }
        this.courseCode = courseCode.trim();
        this.courseName = courseName.trim();
        this.credits = credits;
        this.registeredStudents = registeredStudents;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public int getRegisteredStudents() {
        return registeredStudents;
    }

    public abstract double tuitionPerStudent();

    // "Theory" or "Lab"
    public abstract String getType();

    // Expected total = tuition per student x registered students (same for both types).
    public double expectedCollection() {
        return tuitionPerStudent() * registeredStudents;
    }

    // Common row; tuitionPerStudent() inside it runs the subclass version.
    public void displayInfo() {
        System.out.println(String.format(Locale.US,
                "%-7s | %-6s | %-28s | %2d | %4d | %16s | %16s",
                courseCode, getType(), courseName, credits, registeredStudents,
                Money.vnd(tuitionPerStudent()), Money.vnd(expectedCollection())));
    }
}
