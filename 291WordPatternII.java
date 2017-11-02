import java.util.ArrayList;

/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:

pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.


Notes:
You may assume both pattern and str contains only lowercase letters.
 */

public class Solution {
  public  boolean wordPatternMatch(String pattern, String str) {
     	    if(pattern.length()==0 && str.length()==0)
     	        return true;
     	    if(pattern.length()==0)
     	        return false;

     	    Map<Character, String> map1 = new HashMap<>();
     	    Set<String> set = new HashSet<>();

     	    return helper(map1, set, pattern, str, 0, 0);
     	}

       public  boolean helper(Map<Character, String> map1, Set<String> set, String pattern, String str, int pidx, int sidx)
       {
     	  if(pidx == pattern.length() && sidx == str.length())
     	  {
     		  return true;
     	  }

     	  if(pidx ==pattern.length() || sidx==str.length())
     		  return false;

 		  char ch = pattern.charAt(pidx);

     	  for(int i=sidx; i<str.length(); ++i)
     	  {
     		  String substr = str.substring(sidx, i+1);
     		  if(!map1.containsKey(ch) && !set.contains(substr))
     		  {
     			  map1.put(ch, substr);
     			  set.add(substr);
     			  if(helper(map1, set, pattern, str, pidx+1, i+1))
     				  return true;
     			  map1.remove(ch);
     			  set.remove(substr);
     		  }
     		  else if(map1.containsKey(ch) && map1.get(ch).equals(substr))
     		  {
     			  if(helper(map1, set, pattern, str, pidx+1, i+1))
     				  return true;
     		  }

     	  }

     	  return false;

       }
}







































}
