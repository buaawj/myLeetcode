import java.util.ArrayList;

/*
[LeetCode] Strobogrammatic Number II 对称数之二


A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].

Hint:

Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
*/

// Leetcode 737. Sentence Similarity II: string union-find
public static List<String> findStrobogrammatic(int n) {

       Map<Character, Character> mapping = new HashMap<>();
       mapping.put('0', '0');
       mapping.put('1', '1');
       mapping.put('8', '8');
       mapping.put('6', '9');
       mapping.put('9', '6');

       return helper(mapping, 0, n-1);
}

private static List<String> helper(Map<Character, Character> mapping, int start, int end)
{
 List<String> ret = new ArrayList<>();
 if(start > end)
 {
   ret.add("");
   return ret;
 }

 if(start == end)
 {
   ret.add("0");
   ret.add("1");
   ret.add("8");
   return ret;
 }

 List<String> temp = helper(mapping, start+1, end-1);
 for(char key : mapping.keySet())
 {
   if(start==0 && key=='0')
     continue;
   for(String res : temp)
   {
     StringBuilder sb = new StringBuilder();
     sb.append(key);
     sb.append(res);
     sb.append(mapping.get(key));
     ret.add(sb.toString());
   }

 }

 return ret;
}
