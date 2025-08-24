package tnsif;

public class operatorss {
	public class OperatorConditionalDemo {
	    public static void main(String[] args) {
	        int a = 10;
	        int b = 4;

	        // Arithmetic & Assignment
	        int sum = a + b;         // 14
	        int diff = a - b;        // 6
	        int prod = a * b;        // 40
	        double quotient = (double) a / b; // 2.5
	        a += 5;                  // a = 15

	        System.out.println("sum = " + sum);
	        System.out.println("diff = " + diff);
	        System.out.println("prod = " + prod);
	        System.out.println("quotient = " + quotient);
	        System.out.println("a after a += 5: a = " + a);

	        // Relational & Logical
	        boolean greater = a > b;          // true
	        boolean bothTrue = (a > b) && (b > 0); // true
	        boolean either = (a < 0) || (b > 0);   // true
	        boolean notEqual = a != b;        // true

	        System.out.println("a > b? " + greater);
	        System.out.println("bothTrue (a > b && b > 0)? " + bothTrue);
	        System.out.println("either (a < 0 || b > 0)? " + either);
	        System.out.println("a != b? " + notEqual);

	        // Ternary Operator
	        int max = (a > b) ? a : b;
	        String parity = (max % 2 == 0) ? "even" : "odd";

	        System.out.println("max = " + max);
	        System.out.println("parity of max: " + parity);

	        // Conditional via if-else
	        System.out.print("max in range: ");
	        if (max > 20) {
	            System.out.println("Greater than 20");
	        } else if (max >= 10) {
	            System.out.println("Between 10 and 20");
	        } else {
	            System.out.println("Less than 10");
	        }

	        // switch-case
	        System.out.println("Switch on diff:");
	        switch (diff) {
	            case 0:
	                System.out.println("diff is zero");
	                break;
	            case 6:
	                System.out.println("diff is six");
	                break;
	            default:
	                System.out.println("diff is something else");
	        }

	        
	        System.out.println("Loop with break/continue:");
	        for (int i = 1; i <= 10; i++) {
	            if (i == 3) {
	                System.out.println("  Skipping " + i);
	                continue; 
	            }
	            if (i == 8) {
	                System.out.println("  Breaking at " + i);
	                break; 
	            }
	            System.out.println("  i = " + i);
	        }
	    }
	}


}
