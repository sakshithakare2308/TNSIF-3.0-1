package tnsif;

public class Loopd {

	    public static void main(String[] args) {
	        // 1️⃣ For loop: known number of iterations
	        System.out.println("For loop:");
	        for (int i = 1; i <= 5; i++) {
	            System.out.print(i + " ");
	        }
	        System.out.println("\n");

	        // 2️⃣ While loop: condition-checked before entry
	        System.out.println("While loop:");
	        int j = 1;
	        while (j <= 5) {
	            System.out.print(j + " ");
	            j++;
	        }
	        System.out.println("\n");

	        // 3️⃣ Do-while loop: guarantee at least one iteration
	        System.out.println("Do-while loop:");
	        int k = 1;
	        do {
	            System.out.print(k + " ");
	            k++;
	        } while (k <= 5);
	        System.out.println("\n");

	    
	    }
	}


