package ignitersHubAssignment;

import java.util.Scanner;

public class PalindromeChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a string
        System.out.print("Enter a string: ");
        String inputString = scanner.nextLine();

        // Check if the input string is a palindrome
        if (isPalindrome(inputString)) {
            System.out.println("The string '" + inputString + "' is a palindrome.");
        } else {
            System.out.println("The string '" + inputString + "' is not a palindrome.");
        }

        scanner.close();
    }

    // Function to check if a string is a palindrome
    private static boolean isPalindrome(String str) {
        // Removing spaces and converting to lowercase for case-insensitive comparison
        str = str.replaceAll("\\s", "").toLowerCase();

        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false; // Characters at symmetric positions do not match
            }
            left++;
            right--;
        }

        return true; // All characters matched, the string is a palindrome
    }
}

