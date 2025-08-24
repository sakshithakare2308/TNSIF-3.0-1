package tnsif;
import java.util.Scanner;
public class Atm {
	
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        int balance = 50_000;
	        String lastTransaction = "No transactions yet";
	        final int PIN = 1234;

	        System.out.print("Enter your PIN: ");
	        if (sc.nextInt() != PIN) {
	            System.out.println("❌ Access denied. Incorrect PIN.");
	            return;
	        }

	        while (true) {
	            System.out.println("\n--- ATM Menu ---");
	            System.out.println("1. Withdraw");
	            System.out.println("2. Deposit");
	            System.out.println("3. Check Balance");
	            System.out.println("4. Last Transaction");
	            System.out.println("5. Exit");
	            System.out.print("Choose option (1-5): ");

	            int choice = sc.nextInt();
	            switch (choice) {
	                case 1:
	                    System.out.print("Enter amount to withdraw: ");
	                    int w = sc.nextInt();
	                    if (w > 0 && w <= balance) {
	                        balance -= w;
	                        lastTransaction = "Withdrawn: ₹" + w;
	                        System.out.println("✅ Please take your cash.");
	                    } else {
	                        System.out.println("❌ Invalid or insufficient funds.");
	                    }
	                    break;
	                case 2:
	                    System.out.print("Enter amount to deposit: ");
	                    int d = sc.nextInt();
	                    if (d > 0) {
	                        balance += d;
	                        lastTransaction = "Deposited: ₹" + d;
	                        System.out.println(" Deposit successful.");
	                    } else {
	                        System.out.println("Invalid amount.");
	                    }
	                    break;
	                case 3:
	                    System.out.println(" Your balance is: ₹" + balance);
	                    break;
	                case 4:
	                    System.out.println(" Last transaction: " + lastTransaction);
	                    break;
	                case 5:
	                    System.out.println("Thank you for using the ATM!");
	                    sc.close();
	                    return;
	                default:
	                    System.out.println("Invalid option. Please choose 1-5.");
	            }
	        }
	    }
	}


