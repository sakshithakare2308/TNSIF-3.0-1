package tnsif;

public class breaking {
    public static void main(String[] args) {
	        System.out.println("Using break:");
	        for (int i = 1; i <= 10; i++) {
	            if (i == 5) {
	                System.out.println("  Break at i = " + i);
	                break;  // Exit the loop completely when i is 5
	            }
	            System.out.print(i + " ");
	        }
	        System.out.println("\n");

	        System.out.println("Using continue:");
	        for (int i = 1; i <= 10; i++) {
	            if (i % 2 == 0) {
	                continue;  // Skip the rest of this iteration when i is even
	            }
	            System.out.print(i + " ");
	        }
	        System.out.println();
	    }
	}



