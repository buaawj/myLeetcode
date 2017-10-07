import java.util.ArrayList;
import java.util.HashMap;

import com.sun.javafx.collections.MappingChange.Map;
import com.sun.org.apache.xpath.internal.operations.String;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/*
 Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form. 
For example: 
Given s = "aabb", return ["abba", "baab"]. 
Given s = "abc", return []. 
Hint: 
If a palindromic permutation exists, we just need to generate the first half of the string. 
To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II or Next Permutation. 
https://discuss.leetcode.com/topic/30850/java-6ms-solution-no-reversal-or-concat
https://discuss.leetcode.com/topic/22204/my-accepted-java-solution
The idea is to start recursion from the mid position, and copy char to left and right in the char array in-place until we reach the left and right ends.
elegant!. preaclloc a buffer and use String(char[]) constructor!
one suggestion to make less parameter is : left value is enough, since right can be calculated from left.
 */

public class Solution {
	 public static List<String> generatePalindromes(String s)    
		{
			Map<Character, Integer> countMap = new HashMap<>();
			String mid = "";
			StringBuilder sb = new StringBuilder();
			List<String> ret = new ArrayList<String>();
			int odd = 0;
			for (int i = 0; i < s.length(); i++)		
			{
				char ch = s.charAt(i);
				countMap.put(ch, countMap.getOrDefault(ch, 0)+1);
				odd += countMap.get(ch)%2==0 ? 1 : -1;
			}
			
			if(odd > 1)
				return ret;
			
			for(Map.Entry<Character, Integer> entry: countMap.entrySet())
			{
				char key = entry.getKey();
				int value = entry.getValue();
				
				if(value%2!=0) mid += key;
				for(int i=0; i<value/2; ++i)
					sb.append(key);
			}
			
			permuationHelper(ret, sb.toString().toCharArray(), mid, 0, sb.length()-1);
			return ret;

	    }
	 
	    public static void permuationHelper(List<String> ret, char[] s, String mid, int begin, int end)
	    {
	    	if(begin >= end)
	    	{
	    		String string = String.valueOf(s);

	    		ret.add(string+mid+new StringBuilder(string).reverse().toString() );
	    		return;
	    	}
	    	
	    	for(int i=begin; i<=end; ++i)
	    	{
	    		swap(s, begin, i);
	    		permuationHelper(ret, s, mid, begin+1, end);
	    		swap(s, begin, i);
	    	}
	    }
	    
	    public static void swap(char[] s, int l, int r)
	    {
	    	char temp = s[l];
	    	s[l] = s[r];
	    	s[r] = temp;
	    }

	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
