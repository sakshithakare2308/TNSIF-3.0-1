package tnsif;

public class StaticKeywordExample {
	static int staticCount = 0;  
   
    static {
        System.out.println("Static block executed.");
    }

    
    StaticKeywordExample() {
        staticCount++;
        System.out.println("Object created. Static count = " + staticCount);
    }

    
    static void staticMethod() {
        System.out.println("Static method called. Static count = " + staticCount);
    }

    public static void main(String[] args) {
        StaticKeywordExample.staticMethod(); 

        StaticKeywordExample obj1 = new StaticKeywordExample();
        StaticKeywordExample obj2 = new StaticKeywordExample();

        StaticKeywordExample.staticMethod(); // Shared static value
    }
}

