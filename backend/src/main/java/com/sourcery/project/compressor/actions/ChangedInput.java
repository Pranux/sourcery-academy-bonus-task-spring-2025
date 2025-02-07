package com.sourcery.project.compressor.actions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangedInput {
    
    // Method for compressing String input
    // Method uses simple iteration, it checks how much does a char repeat and then adds char and that number
    public static String compressed(String input) {

        // Using StringBuilder because it has useful methods to (like append)
        StringBuilder result = new StringBuilder(); 
        int len = input.length(); 
        
        // If method receives empty input - it return empty String
        if(len == 0) {
            return "";
        }
        
        char currentChar;
        char lastChar = input.charAt(0);
        int count = 1;  // Number of repeating chars
        
        // Iterating though every char in String
        for(int i = 1; i < len; ++i) {
            currentChar = input.charAt(i);
            if(currentChar == lastChar) {
                ++count;   // If current char is the same as the last one, we increase char counter
            } else {
                result.append(lastChar).append(count);  // Else, we append our char and the number of times it repeats 
                count = 1;  // Then we make count to 1, because a char always repeats at least one time 
            }
            lastChar = currentChar; // We make last char same as the current, because our current char later becomes last char
        }
        result.append(lastChar).append(count); // After iterating we are left with char that we did not append, so we append it
        return result.toString(); // We return StringBuilder as String
    }

    // Method for decompressing String input
    // Methods iterates from right to left, that way the first thing it gets is number of times the char should repeat and only then the char itself  
    public static String decompressed(String input) {
        
        // Using StringBuilder because it has useful methods to (like append, repeat, reverse)
        StringBuilder result = new StringBuilder();
        int len = input.length();

        // If method receives empty input - it return empty String
        if(len == 0) {
            return "";
        }

        char currentChar;
        int count = 0; // Number of repeating characters (digits)
        int num = 0; // Number that char needs to be repeated

        // Iterating from right to left, that way method works faster, it does not need to go back and forth when going through chars in string
        for(int i = len-1; i >= 0; --i) {
            currentChar = input.charAt(i);
            if(Character.isDigit(currentChar)) {
                // Getting the number of times char needs to be repeated
                // It works by firstly getting the number from string as an int
                // Then it multiplies that number by digits position from the right side of the number
                // For example 123 would be gotten this way: 3 + 20 + 100, etc.
                num += ((int) currentChar - 48) * power(10, count);
                ++count; // Adding count to digits in a numebr
            } else {
                result.append(String.valueOf(currentChar).repeat(num)); // If we find a letter, we add it to StringBuilder as many times as needed
                count = 0; // We make our number of digits found 0
                num = 0; // We make our number 0
            }
        }
        return result.reverse().toString(); // Because we read String from right to left we need to reverse results at the end
    }
    
    // Helper function to get power from two integers
    private static int power(int a, int b) {
        int ans = 1; // Our result
        if(b == 0) {
            return 1; // If b == 0, then no matter what is a, we will get 1
        }
        for(int i = 0; i < b; ++i) {
            ans *= a;
        }
        return ans;
    }
}
