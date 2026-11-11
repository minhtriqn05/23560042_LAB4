package ex5;

import util.Money;

public class LabCourse extends CourseRegistration {
    private static final double PRICE_PER_CREDIT = 600000;

    private double equipmentFee;               // per student

    public LabCourse(String courseCode, String courseName, int credits,
                     int registeredStudents, double equipmentFee) {
        super(courseCode, courseName, credits, registeredStudents);
        if (equipmentFee < 0) {
            throw new IllegalArgumentException("Equipment fee must be >= 0.");
        }
        this.equipmentFee = equipmentFee;
    }

    public double getEquipmentFee() {
        return equipmentFee;
    }

    // credits x 600,000 + equipment fee
    @Override
    public double tuitionPerStudent() {
        return getCredits() * PRICE_PER_CREDIT + equipmentFee;
    }

    @Override
    public String getType() {
        return "Lab";
    }

    // A lab course also shows its equipment fee under the common row.
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("        (includes equipment fee " + Money.vnd(equipmentFee)
                + " per student)");
    }
}
