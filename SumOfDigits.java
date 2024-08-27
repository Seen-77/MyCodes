import java.util.*;
import java.lang.NumberFormatException;
public class SumOfDigits {
        public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            String input = sc.nextLine(); // Example input
            int sum = 0;
    
            for (int i = 0; i < input.length(); i++) {
                char ch = input.charAt(i);
    
                // Check if the character is a digit
                if (Character.isDigit(ch)) {
                    sum += Character.getNumericValue(ch);
                }
            }
    
            System.out.println("The sum of digits in the string is: " + sum);
        }
    }