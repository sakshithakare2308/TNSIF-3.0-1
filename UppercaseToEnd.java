package tnsif;

import java.util.Scanner;

public class UppercaseToEnd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        StringBuilder lower = new StringBuilder();
        StringBuilder upper = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                upper.append(ch);
            } else {
                lower.append(ch);
            }
        }

        // Combine lowercase followed by uppercase
        System.out.println(lower.toString() + upper.toString());
    }
}
