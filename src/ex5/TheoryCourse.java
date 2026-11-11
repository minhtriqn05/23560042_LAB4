package ex5;

public class TheoryCourse extends CourseRegistration {
    private static final double PRICE_PER_CREDIT = 450000;

    public TheoryCourse(String courseCode, String courseName, int credits,
                        int registeredStudents) {
        super(courseCode, courseName, credits, registeredStudents);
    }

    // credits x 450,000
    @Override
    public double tuitionPerStudent() {
        return getCredits() * PRICE_PER_CREDIT;
    }

    @Override
    public String getType() {
        return "Theory";
    }
}
