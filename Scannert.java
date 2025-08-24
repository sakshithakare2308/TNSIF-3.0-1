package tnsif;
import java.util.Scanner;

public class Scannert {
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in); // Step 1: Import & create Scanner

	        // Read String (whole line)
	        System.out.print("Enter your name: ");
	        String name = sc.nextLine();

	        // Read single word (token)
	        System.out.print("Enter your city: ");
	        String city = sc.next(); // stops at whitespace

	        // Read integer
	        System.out.print("Enter your age: ");
	        int age = sc.nextInt();

	        // Read double
	        System.out.print("Enter your GPA: ");
	        double gpa = sc.nextDouble();

	        // Read boolean
	        System.out.print("Are you a student? (true/false): ");
	        boolean student = sc.nextBoolean();

	        System.out.println("\n— Summary —");
	        System.out.println("Name: " + name);
	        System.out.println("City: " + city);
	        System.out.println("Age: " + age);
	        System.out.println("GPA: " + gpa);
	        System.out.println("Is student? " + student);

	        sc.close();
	    }
	}


