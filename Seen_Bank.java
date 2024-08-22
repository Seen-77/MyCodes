import java.util.Scanner;
import java.util.Random;

public class Seen_Bank{
    private String accountNumber;
    private String accountHolder;
    int password;
    private double balance;

    public Seen_Bank(String accountNumber, String accountHolder, Scanner s) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        System.out.println("Please enter a password (4-digit PIN) for your account:");
        this.password = s.nextInt();
        System.out.println("This is your account number: " + accountNumber);
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Amount deposited successfully. Balance: " + balance);
    }

    public void withdraw(double amount, Scanner s) {
        System.out.println("Please enter your 4-digit PIN:");
        int temp_pass = s.nextInt();
        if (temp_pass == password) {
            if (amount > balance) {
                System.out.println("Insufficient balance.");
            } else {
                balance -= amount;
                System.out.println("Amount withdrawn successfully. Remaining Balance: " + balance);
            }
        } else {
            System.out.println("Incorrect password.");
        }
    }

    public void transfer_money(Seen_Bank diffAccount, double amount, Scanner s) {
        System.out.println("Please enter your 4-digit PIN for transfer:");
        int temp_pass = s.nextInt();
        if (temp_pass == password) {
            if (amount <= balance) {
                this.withdraw(amount, s);
                diffAccount.deposit(amount);
                System.out.println("Money transferred successfully. New Balance: " + this.balance);
            } else {
                System.out.println("Insufficient balance for transfer.");
            }
        } else {
            System.out.println("Incorrect password. Transfer failed.");
        }
    }

    public void AccountDetails() {
        System.out.println("Account Number: " + accountNumber + ", Account Holder: " + accountHolder + ", Balance: " + balance);
    }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public void setAccountHolder(String accountHolder) { this.accountHolder = accountHolder; }
    public double getBalance() { return balance; }

    public static void main(String[] args) {
        Seen_Bank user1 = null;
        Seen_Bank user2 = null; // For demonstration, a second user account
        String name;
        int age;
        boolean flag = false;
        System.out.println("HI, welcome to Seen-Bank.");
        System.out.println("If you are a new user, create a new account.");

        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        long acno1 = rand.nextLong(100000000000L);
        long acno2 = rand.nextLong(100000000000L);
        String accno1 = Long.toString(acno1);
        String accno2 = Long.toString(acno2);

        while (true) {
            System.out.println("1 : Create a new account");
            System.out.println("2 : Access existing account");
            System.out.println("3 : Exit bank");
            System.out.println("Enter your choice: [1-3]");
            int cho = sc.nextInt();
            sc.nextLine();
            switch (cho) {
                case 1: {
                    if (flag) {
                        System.out.println("You already created your account.");
                        break;
                    }
                    System.out.println("Please enter your name in block letters:");
                    name = sc.nextLine();
                    System.out.println("Please enter your age:");
                    age = sc.nextInt();
                    if (age < 18) {
                        System.out.println("You are not eligible to create a bank account.");
                        break;
                    }
                    user1 = new  Seen_Bank(accno1, name, sc);
                    user2 = new  Seen_Bank(accno2, "SECOND USER", sc); // Creating a second account for testing
                    flag = true;
                    break;
                }
                case 2: {
                    if (flag) {
                        while (true) {
                            System.out.println("What would you like to do next?");
                            System.out.println("1 : Deposit money");
                            System.out.println("2 : Withdraw money");
                            System.out.println("3 : Get account details");
                            System.out.println("4 : Transfer money");
                            System.out.println("5 : Exit bank");
                            System.out.println("Enter your choice: [1-5]");
                            int icho = sc.nextInt();
                            sc.nextLine();
                            switch (icho) {
                                case 1: {
                                    System.out.println("Please enter the amount to deposit:");
                                    double damo = sc.nextDouble();
                                    sc.nextLine();
                                    user1.deposit(damo);
                                    break;
                                }
                                case 2: {
                                    System.out.println("Please enter the amount to withdraw:");
                                    double damo1 = sc.nextDouble();
                                    sc.nextLine();
                                    user1.withdraw(damo1, sc);
                                    break;
                                }
                                case 3: {
                                    user1.AccountDetails();
                                    break;
                                }
                                case 4: {
                                    System.out.println("Please enter the amount to transfer:");
                                    double tamount = sc.nextDouble();
                                    sc.nextLine();
                                    user1.transfer_money(user2, tamount, sc);
                                    break;
                                }
                                case 5: {
                                    System.out.println("Thank you for using our bank. Have a good day! :)");
                                    System.exit(0);
                                }
                            }
                        }
                    } else {
                        System.out.println("Please create an account first.");
                        break;
                    }
                }
                case 3: {
                    System.out.println("Thank you for using our bank. Have a good day! :)");
                    System.exit(0);
                }
            }
        }
    }
}