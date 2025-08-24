package tnsif;



final class FinalDemo {

    final int number = 100; 

    // Final method
    final void displayMessage() {
        System.out.println("This is a final method. Number = " + number);
    }

    
}


public class FinalKeywordExample {
    public static void main(String[] args) {
        FinalDemo obj = new FinalDemo();
        obj.displayMessage();
    }
}


