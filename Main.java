package poolset;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	
    	
        UserDAO userDAO = new UserDAO();
        TransactionDAO transactionDAO = new TransactionDAO();
        Scanner scanner = new Scanner(System.in);

        try {
        // Get user info
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter initial balance: ");
            double initialBalance = scanner.nextDouble();

        // Create user
            userDAO.createUser(name, initialBalance);
            System.out.println("User created successfully!");

            boolean exit = false;

            while (!exit) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Check Balance");
                System.out.println("4. Exit");
                System.out.print("Your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        transactionDAO.deposit(1, depositAmount); 
                        System.out.println("Deposited " + depositAmount);
                        break;

                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        transactionDAO.withdraw(1, withdrawAmount); 
                        System.out.println("Withdrew " + withdrawAmount);
                        break;

                    case 3:
                        double balance = userDAO.getBalance(1); 
                        System.out.println("Current Balance: " + balance);
                        break;

                    case 4:
                        exit = true;
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again!");
                }
            }
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        } 
            scanner.close();
        
    }
}
