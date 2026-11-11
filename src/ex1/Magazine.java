package ex1;

import java.util.Locale;

public class Magazine extends LibraryItem {
    private static final int FEE_PER_DAY = 3000;
    private static final int FIXED_FEE = 2000;

    private int issueNumber;

    public Magazine(String id, String title, String author, int days, int issueNumber) {
        super(id, title, author, days);
        if (issueNumber < 1) {
            throw new IllegalArgumentException("Issue number must be >= 1.");
        }
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    // 3,000 x days + 2,000
    @Override
    public double calculateFee() {
        return FEE_PER_DAY * getDays() + FIXED_FEE;
    }

    @Override
    public String getType() {
        return "Magazine";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.print(String.format(Locale.US, " | issue %5d", issueNumber));
    }
}
