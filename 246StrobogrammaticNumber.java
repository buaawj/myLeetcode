import java.util.ArrayList;

/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

Example 1:

Input:  "69"
Output: true
Example 2:

Input:  "88"
Output: true
Example 3:

Input:  "962"
Output: false
*/

// Leetcode 737. Sentence Similarity II: string union-find
bool isStrobogrammatic(string num) {

        Map<Character, Character> mapping = new HashMap<>();
        mapping.put('0', '0');
        mapping.put('1', '1');
        mapping.put('8', '8');
        mapping.put('6', '9');
        mapping.put('9', '6');

        int i = 0, j = num.length()-1;
        while(i<=j)
        {
          if(!mapping.containsKey(nums.charAt(i)) || mapping.containsKey(nums.charAt(i)) != nums.charAt(j))
          {
            return false;
          }

          ++i;
          --j;
        }

        return true;



















}
