package tnsif;
import java.util.Scanner;

// The TicketBooking class
// This class is not public because there can only be one public class per .java file,
// and that public class must have the same name as the file (Main in this case).
class TicketBooking {
    // Private attributes as specified
    private String stageEvent;
    private String customer;
    private int noOfSeats;

    // Default constructor
    public TicketBooking() {
        this.stageEvent = "Unknown Event";
        this.customer = "Guest";
        this.noOfSeats = 0;
    }

    // Parameterized constructor as specified
    public TicketBooking(String stageEvent, String customer, int noOfSeats) {
        this.stageEvent = stageEvent;
        this.customer = customer;
        this.noOfSeats = noOfSeats;
    }

    // Getters for all attributes
    public String getStageEvent() {
        return stageEvent;
    }

    public String getCustomer() {
        return customer;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    // Setters for all attributes
    public void setStageEvent(String stageEvent) {
        this.stageEvent = stageEvent;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    // Overloaded makePayment methods

    /**
     * This method is for cash payment.
     * This method accepts amount as input and displays the transaction detail.
     */
    public void makePayment(double amount) {
        System.out.println("Stage event:" + this.stageEvent);
        System.out.println("Customer:" + this.customer);
        System.out.println("Number of seats:" + this.noOfSeats);
        System.out.printf("Amount %.1f paid in cash\n", amount); // Format to one decimal place
    }

    /**
     * This method is for wallet payment.
     * This method accepts amount and wallet number as input and displays the transaction detail.
     */
    public void makePayment(String walletNumber, double amount) {
        System.out.println("Stage event:" + this.stageEvent);
        System.out.println("Customer:" + this.customer);
        System.out.println("Number of seats:" + this.noOfSeats);
        System.out.printf("Amount %.1f paid using wallet number %s\n", amount, walletNumber); // Format to one decimal place
    }

    /**
     * This method is for credit card payment.
     * This method accepts credit card detail, ccv, card holder name, and amount as input
     * and displays the transaction detail.
     * Note: The problem statement sample output indicates the order: cardholder name, amount, card type, CCV.
     * I've adjusted parameters for consistency with that output for better flow of user input.
     */
    public void makePayment(String cardholderName, double amount, String creditCardType, String ccvNumber) {
        System.out.println("Stage event:" + this.stageEvent);
        System.out.println("Customer:" + this.customer);
        System.out.println("Number of seats:" + this.noOfSeats);
        System.out.println("Holder name:" + cardholderName);
        System.out.printf("Amount %.1f paid using %s card\n", amount, creditCardType); // Format to one decimal place
        System.out.println("CCV:" + ccvNumber);
    }
}

// The Main driver class
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Obtain ticket booking details (stageEvent,customer,noOfSeats separated by a comma)
        String bookingDetailsLine = scanner.nextLine();
        String[] details = bookingDetailsLine.split(",");

        // Basic validation for input format
        if (details.length != 3) {
            System.out.println("Invalid booking details format. Expected: stageEvent,customer,noOfSeats");
            scanner.close();
            return;
        }

        String stageEvent = details[0].trim();
        String customer = details[1].trim();
        int noOfSeats = 0;
        try {
            noOfSeats = Integer.parseInt(details[2].trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number of seats. Please enter an integer.");
            scanner.close();
            return;
        }

        TicketBooking booking = new TicketBooking(stageEvent, customer, noOfSeats);

        // 2. Obtain Payment mode
        int paymentMode = 0;
        try {
            paymentMode = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid choice. Payment mode must be an integer.");
            scanner.close();
            return;
        }
        scanner.nextLine(); // Consume the remaining newline character after reading the integer

        // Call appropriate makePayment method based on payment mode
        switch (paymentMode) {
            case 1: // Cash payment
                double cashAmount = 0.0;
                try {
                    cashAmount = scanner.nextDouble();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid amount. Please enter a number.");
                    scanner.close();
                    return;
                }
                booking.makePayment(cashAmount);
                break;

            case 2: // Wallet payment
                double walletAmount = 0.0;
                try {
                    walletAmount = scanner.nextDouble();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid amount. Please enter a number.");
                    scanner.close();
                    return;
                }
                scanner.nextLine(); // Consume newline after amount
                String walletNumber = scanner.nextLine();
                booking.makePayment(walletNumber, walletAmount);
                break;

            case 3: // Credit card payment
                String cardholderName = scanner.nextLine();
                double creditCardAmount = 0.0;
                try {
                    creditCardAmount = scanner.nextDouble();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid amount. Please enter a number.");
                    scanner.close();
                    return;
                }
                scanner.nextLine(); // Consume newline after amount
                String creditCardType = scanner.nextLine(); // e.g., "Master", "Visa"
                String ccvNumber = scanner.nextLine();
                booking.makePayment(cardholderName, creditCardAmount, creditCardType, ccvNumber);
                break;

            default:
                System.out.println("Invalid choice");
        }

        scanner.close(); // Close the scanner to prevent resource leaks
    }
}