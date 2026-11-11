# 23560042_LAB4 – Comprehensive OOP Review (Preparation for Lab 5)

Object-Oriented Programming (CSBU107.R11.KHBC) – Lab 4
Student: Nguyễn Minh Trí – 23560042

## Files (folder `src`)
| Package | Classes | Content |
|---|---|---|
| util | ConsoleInput, Money | Reads and checks console input; formats amounts as `17,000 VND` (used by all exercises) |
| ex1 | LibraryItem, PrintedBook, Magazine, Library, Main | Exercise 1 – Library borrowing fee management |
| ex2 | Employee, FullTimeEmployee, PartTimeEmployee, Company, Main | Exercise 2 – Employee payroll management |
| ex3 | Delivery, StandardDelivery, ExpressDelivery, DeliveryService, Main | Exercise 3 – Online order delivery management |
| ex4 | Ticket, RegularTicket, VIPTicket, Cinema, Main | Exercise 4 – Cinema ticket sales management |
| ex5 | CourseRegistration, TheoryCourse, LabCourse, University, Main | Exercise 5 – University course registration and tuition |

Every exercise has an abstract superclass, two subclasses, a class that keeps the objects in an
`ArrayList` of the superclass type, and a `Main` class with a console menu.

Other files:
- `test-input/exN_input.txt` – the test data used for every exercise.

## How to run
1. Install JDK 11 or later and Visual Studio Code with the Extension Pack for Java.
2. Open this folder in VS Code, open `src/exN/Main.java`, click **Run** above `main()` and use the menu in the Terminal.
3. To run with the test data instead of typing, use a terminal in this folder (after VS Code has compiled the project into `bin`):
   ```
   java -Decho=true -cp bin ex1.Main < test-input/ex1_input.txt
   ```
   `-Decho=true` makes the program print every value it reads, so the output looks the same as typed input.
