import java.util.ArrayList;

/*
Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

Begin with the first character and then the number of characters abbreviated, which followed by the last character.
If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
If the abbreviation doesn't make the word shorter, then keep it as original.
Example:
Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
*/

class Solution {
 public static List<String> wordsAbbreviation(List<String> dict) {
	        Map<String, List<Integer>> map = new HashMap<>();
	        int n = dict.size();
	        for(int i=0; i<n; ++i)
	        {
                String str = getShortestAbbrev(dict.get(i));

                if(!map.containsKey(str))
                {
                	map.put(str, new ArrayList<>());
                }

                map.get(str).add(i);
	        }

	        List<String> ret = new ArrayList<>();
	        for(int i=0; i<n; ++i)
	        	ret.add("");
	        for(String key : map.keySet())
	        {
	        	List<Integer> pos = map.get(key);
	        	if(pos.size()==1)
	        	{
	        		ret.set(pos.get(0), key);
	        		continue;
	        	}

	        	TrieNode root = new TrieNode();
	        	for(int p : pos)
	        	{
	        		BuildTrie(dict.get(p), root);
	        	}

	        	for(int p : pos)
	        	{
	        		TrieNode cur = root;
	        		String s = dict.get(p);
	        		int i=0, m = s.length();
	        		while(i<m && cur.children.get(s.charAt(i)).count > 1)
	        			cur = cur.children.get(s.charAt(i++));

	        		if(i>=m-3)
	        		{
	        			ret.set(p, s);
	        		}
	        		else
	        		{
	        			ret.set(p, s.substring(0, i+1)+(m-i-2)+s.charAt(m-1));
	        		}
	        	}
	        }

	        return ret;

	    }

	    private static String getShortestAbbrev(String str)
	    {
	    	return str.length()>3 ? str.charAt(0)+""+(str.length()-2)+""+str.charAt(str.length()-1) : str;
	    }


    	static void BuildTrie(String s, TrieNode root)
    	{
    		TrieNode cur = root;
    		for(int i=0; i<s.length(); ++i)
    		{
    			char ch = s.charAt(i);
    			if(!cur.children.containsKey(ch))
    			{
    				cur.children.put(ch, new TrieNode());
    			}

    			cur = cur.children.get(ch);
    			++cur.count;
    		}
    	}

	    static class TrieNode
	    {
	    	int count = 0;
	    	Map<Character, TrieNode> children = new HashMap<>();
	    }
}






















}
