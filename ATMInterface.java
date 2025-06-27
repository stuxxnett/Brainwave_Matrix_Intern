import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class ATMInterface {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static Map<String, Double> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String currentAccount = null;
    
    public static void main(String[] args) {
        // Initialize some demo accounts
        accounts.put("123456", 1000.00);
        accounts.put("654321", 2500.00);
        
        System.out.println("====================================");
        System.out.println("      WELCOME TO JAVA ATM BANK      ");
        System.out.println("====================================");
        
        mainMenu();
    }
    
    private static void mainMenu() {
        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("\nThank you for using Java ATM Bank. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void login() {
        System.out.print("\nEnter your account number: ");
        String accountNumber = scanner.nextLine();
        
        if (accounts.containsKey(accountNumber)) {
            currentAccount = accountNumber;
            System.out.println("\nLogin successful!");
            accountMenu();
        } else {
            System.out.println("\nAccount not found. Please try again.");
        }
    }
    
    private static void accountMenu() {
        while (currentAccount != null) {
            System.out.println("\nAccount: " + currentAccount);
            System.out.println("Balance: $" + df.format(accounts.get(currentAccount)));
            System.out.println("\nPlease select an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    logout();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void deposit() {
        System.out.print("\nEnter amount to deposit: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }
        
        double currentBalance = accounts.get(currentAccount);
        accounts.put(currentAccount, currentBalance + amount);
        System.out.println("\nDeposit successful!");
        System.out.println("New balance: $" + df.format(accounts.get(currentAccount)));
    }
    
    private static void withdraw() {
        System.out.print("\nEnter amount to withdraw: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }
        
        double currentBalance = accounts.get(currentAccount);
        
        if (amount > currentBalance) {
            System.out.println("Insufficient funds.");
            return;
        }
        
        accounts.put(currentAccount, currentBalance - amount);
        System.out.println("\nWithdrawal successful!");
        System.out.println("Please take your cash.");
        System.out.println("New balance: $" + df.format(accounts.get(currentAccount)));
    }
    
    private static void checkBalance() {
        System.out.println("\nCurrent balance: $" + df.format(accounts.get(currentAccount)));
    }
    
    private static void logout() {
        currentAccount = null;
        System.out.println("\nYou have been logged out.");
    }
}
