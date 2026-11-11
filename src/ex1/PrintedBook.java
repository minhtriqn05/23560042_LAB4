package ex1;

import java.util.Locale;

public class PrintedBook extends LibraryItem {
    private static final int FEE_PER_DAY = 5000;
    private static final int FEE_PER_PAGE = 10;

    private int pages;

    public PrintedBook(String id, String title, String author, int days, int pages) {
        super(id, title, author, days);        // the superclass checks the common data
        if (pages < 1) {
            throw new IllegalArgumentException("Pages must be >= 1.");
        }
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

    // 5,000 x days + 10 x pages
    @Override
    public double calculateFee() {
        return FEE_PER_DAY * getDays() + FEE_PER_PAGE * pages;
    }

    @Override
    public String getType() {
        return "PrintedBook";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();                   // common columns first
        System.out.print(String.format(Locale.US, " | %5d pages", pages));
    }
}
