import java.io.*;
import java.util.*;

class Expense {
    String category;
    double amount;
    String date;

    Expense(String category, double amount, String date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return category + "," + amount + "," + date;
    }
}

public class ExpenseTracker {

    static String FILE_PATH = "expenses.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Expense Tracker =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View by Category");
            System.out.println("4. Total Amount Spent");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();

            if (ch == 1) addExpense();
            else if (ch == 2) viewAll();
            else if (ch == 3) viewByCategory();
            else if (ch == 4) totalSpent();
            else break;
        }
    }

    static void addExpense() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter category: ");
        String category = sc.nextLine();
        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter date (DD-MM-YYYY): ");
        String date = sc.nextLine();

        Expense e = new Expense(category, amount, date);

        try (FileWriter fw = new FileWriter(FILE_PATH, true)) {
            fw.write(e.toString() + "\n");
            System.out.println("Expense added successfully!");
        } catch (Exception ex) {
            System.out.println("Error saving expense!");
        }
    }

    static void viewAll() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            System.out.println("\n--- All Expenses ---");
            while ((line = br.readLine()) != null) {
                System.out.println(line.replace(",", " | "));
            }
        } catch (Exception e) {
            System.out.println("No data found!");
        }
    }

    static void viewByCategory() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter category: ");
        String cat = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            System.out.println("\n--- Expenses in Category: " + cat + " ---");
            while ((line = br.readLine()) != null) {
                if (line.startsWith(cat)) {
                    System.out.println(line.replace(",", " | "));
                }
            }
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    static void totalSpent() {
        double total = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                total += Double.parseDouble(arr[1]);
            }
        } catch (Exception e) {
            System.out.println("Error!");
        }
        System.out.println("Total Spent = " + total);
    }
}
