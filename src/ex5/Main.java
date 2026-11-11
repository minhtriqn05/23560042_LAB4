package ex5;

import java.util.Scanner;

import util.ConsoleInput;
import util.Money;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        University university = new University("UIT");
        int choice;
        do {
            System.out.println();
            System.out.println("===== COURSE REGISTRATION AND TUITION - "
                    + university.getName() + " =====");
            System.out.println("1. Add courses | 2. Display all | 3. Search by course code"
                    + " | 4. Statistics | 0. Exit");
            choice = ConsoleInput.readInt(sc, "Your choice: ", 0, 4);
            if (choice >= 2 && university.isEmpty()) {
                System.out.println("There are no courses yet, please add courses first.");
                continue;
            }
            switch (choice) {
                case 1:
                    addCourses(sc, university);
                    break;
                case 2:
                    university.displayAll();
                    break;
                case 3:
                    search(sc, university);
                    break;
                case 4:
                    showStatistics(university);
                    break;
                default:
                    System.out.println("Goodbye!");
            }
        } while (choice != 0);
        sc.close();
    }

    private static void addCourses(Scanner sc, University university) {
        int n = ConsoleInput.readInt(sc, "Number of courses to add: ", 1, 100);
        for (int i = 1; i <= n; i++) {
            System.out.println("--- Course " + i + " ---");
            String type = ConsoleInput.readChoice(sc, "Type (T = Theory, L = Lab): ",
                    "T", "L");
            String code = readNewCode(sc, university);
            String name = ConsoleInput.readText(sc, "Course name: ");
            int credits = ConsoleInput.readInt(sc, "Credits: ", 1, 20);
            int students = ConsoleInput.readInt(sc, "Registered students: ", 0, 10000);
            CourseRegistration c;              // superclass type
            if (type.equals("T")) {
                c = new TheoryCourse(code, name, credits, students);
            } else {
                double fee = ConsoleInput.readDouble(sc,
                        "Equipment fee per student (VND): ", 0);
                c = new LabCourse(code, name, credits, students, fee);
            }
            university.addCourse(c);
            System.out.println("  Added " + c.getType() + " course " + c.getCourseCode()
                    + ", tuition = " + Money.vnd(c.tuitionPerStudent()) + "/student");
        }
    }

    private static String readNewCode(Scanner sc, University university) {
        while (true) {
            String code = ConsoleInput.readText(sc, "Course code: ");
            if (university.findByCode(code) == null) {
                return code;
            }
            System.out.println("  Course " + code + " already exists, please enter"
                    + " another code.");
        }
    }

    private static void search(Scanner sc, University university) {
        String code = ConsoleInput.readText(sc, "Course code to search: ");
        CourseRegistration c = university.findByCode(code);
        if (c == null) {
            System.out.println("  No course has the code " + code + ".");
        } else {
            University.printHeader();
            c.displayInfo();
        }
    }

    private static void showStatistics(University university) {
        System.out.println("Theory courses: " + university.count("Theory")
                + ", Lab courses: " + university.count("Lab"));
        System.out.println("Total expected collection: "
                + Money.vnd(university.totalCollection()));
        CourseRegistration top = university.findLargestCollection();
        System.out.println("Largest expected collection: " + top.getCourseCode() + " - "
                + top.getCourseName() + ", " + Money.vnd(top.expectedCollection()));
        System.out.println("Average tuition per student (weighted): "
                + perStudent(university.averageTuition()));
        System.out.println("  Theory courses only: "
                + perStudent(university.averageTuition("Theory")));
        System.out.println("  Lab courses only   : "
                + perStudent(university.averageTuition("Lab")));
    }

    // -1 means "no registered students", which is shown as N/A.
    private static String perStudent(double average) {
        return (average < 0) ? "N/A" : Money.vnd(average) + "/student";
    }
}
