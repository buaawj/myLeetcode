import java.util.ArrayList;

/*
[LeetCode] Strobogrammatic Number III 对称数之三


A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.
*/

// Leetcode 737. Sentence Similarity II: string union-find

static int count = 0;
public static int strobogrammaticInRange(String low, String high) {

    char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
    for(int len = low.length(); len<=high.length(); ++len)
    {
        char[] chars = new char[len];
        helper(low, high, pairs, chars, 0, len-1);
    }

    return count;
}

private static void helper(String low, String high, char[][] pairs, char[] chars, int left, int right)
{
    if(left > right )
    {
        String s = new String(chars);
        if(chars.length == low.length() && s.compareTo(low) < 0 ||
            chars.length == high.length() && s.compareTo(high) > 0)
            return;
        count++;
        return;
    }

    for(char[] pair : pairs)
    {
        if(left == 0 && pair[0] == '0')
            continue;
        chars[left] = pair[0];
        chars[right] = pair[1];

        if(left < right || left==right && pair[0] == pair[1])
            helper(low, high, pairs, chars, left+1, right-1);
    }
}
