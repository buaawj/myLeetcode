import java.util.ArrayList;

/*
[LeetCode] Word Squares 单词平方


Given a set of words (without duplicates), find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y
Note:

There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.


Example 1:

Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).


Example 2:

Input:
["abat","baba","atan","atal"]

Output:
[
  [ "baba",
    "abat",
    "baba",
    "atan"
  ],
  [ "baba",
    "abat",
    "baba",
    "atal"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).。
 */

public class Solution {

	/* Hashmap to store prefix mapping*/
    private static List<List<String>> wordSquares(List<String> words)
    			    {
    			    	Map<String, List<String>> table = new HashMap<>();
    			    	for(String word : words)
    			    	{
    			    		for(int i=1; i<=word.length(); ++i)
    			    		{
    			    			String substr = word.substring(0, i);
    				    		if(!table.containsKey(substr))
    				    		{
    				    			table.put(substr, new ArrayList<>());
    				    		}

    				    		table.get(substr).add(word);
    			    		}
    			    	}

    			    	List<List<String>> ret = new ArrayList<>();
    			    	for(String word: words)
    			    	{
    			    		List<String> path = new ArrayList<>();
    			    		path.add(word);
    			    		wordSquaresHelper(ret, path, table);
    			    	}

    			    	return ret;

    			    }

    			    private static void wordSquaresHelper(List<List<String>> ret, List<String> path, Map<String, List<String>> table)
    			    {
    			    	if(path.size() == path.get(0).length())
    			    	{
    			    		ret.add(new ArrayList<>(path));
    			    		return;
    			    	}

    			    	StringBuilder sBuilder = new StringBuilder();
    			    	for(String str : path)
    			    	{
    			    		sBuilder.append(str.charAt(path.size()));
    			    	}

    			    	if(table.containsKey(sBuilder.toString()))
    			    	{
    			    		for(String stringValue : table.get(sBuilder.toString()))
    			    		{
    			    			path.add(stringValue);
    			    			wordSquaresHelper(ret, path, table);
    			    			path.remove(path.size()-1);
    			    		}
    			    	}
    			    }

    /* Solution 2: Trie tree */
    public static class TrieNode
   			    {
   			    	List<String> startWith;
   			    	TrieNode[] children;

   			    	TrieNode()
   			    	{
   			    		startWith = new ArrayList<>();
   			    		children = new TrieNode[26];
   			    	}
   			    }

   			    public static class TrieTree
   			    {
   			    	TrieNode root = null;
   			    	TrieTree(List<String> words)
   			    	{
   			    		root = new TrieNode();
   			    		for(String word : words)
   			    		{
   			    			TrieNode ptTrieNode = root;
   			    			for(char ch : word.toCharArray())
   			    			{
   			    				int index = ch-'a';
   			    				if(ptTrieNode.children[index]==null)
   			    					ptTrieNode.children[index] = new TrieNode();
   			    				ptTrieNode.children[index].startWith.add(word);
   			    				ptTrieNode = ptTrieNode.children[index];
   			    			}
   			    		}
   			    	}

   			    	List<String> SearchWords(String startWith)
   			    	{
   			    		TrieNode cur = root;
   			    		List<String> ret = new ArrayList<>();
   			    		for(char ch : startWith.toCharArray())
   			    		{
   		    				int index = ch-'a';
   		    				if(cur.children[index] == null)
   		    					return ret;
   		    				cur = cur.children[index];
   			    		}

   			    		if(cur!=null)
   			    			ret.addAll(cur.startWith);
   			    		return ret;
   			    	}

   			    }

   			    private static List<List<String>> wordSquares(List<String> words)
   			    {
   			    	TrieTree tree = new TrieTree(words);

   			    	List<List<String>> ret = new ArrayList<>();
   			    	for(String word: words)
   			    	{
   			    		List<String> path = new ArrayList<>();
   			    		path.add(word);
   			    		wordSquaresHelper(ret, path, tree);
   			    	}

   			    	return ret;

   			    }

   			    private static void wordSquaresHelper(List<List<String>> ret, List<String> path, TrieTree tree)
   			    {
   			    	if(path.size() == path.get(0).length())
   			    	{
   			    		ret.add(new ArrayList<>(path));
   			    		return;
   			    	}

   			    	StringBuilder sBuilder = new StringBuilder();
   			    	for(String str : path)
   			    	{
   			    		sBuilder.append(str.charAt(path.size()));
   			    	}

   			    	for(String stringValue : tree.SearchWords(sBuilder.toString()))
   			    	{
   			    		path.add(stringValue);
   			    		wordSquaresHelper(ret, path, tree);
   			    		path.remove(path.size()-1);
   			    	}
   			    }




































}
