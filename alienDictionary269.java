import java.util.ArrayList;

/*
Alien Dictionary
There is a new alien language which uses the latin alphabet. However,
the order among letters are unknown to you. You receive a list of words from the dictionary,
where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

For example, Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note: You may assume all letters are in lowercase. If the order is invalid,
return an empty string. There may be multiple valid order of letters, return any one of them is fine.
 */

public class Solution {

	public  String alienOrder(String[] words)
		{
			Map<Character, Set<Character>> graph = new HashMap<>();

			for(int i=1; i<words.length; ++i)
			{
				String word1 = words[i-1];
				String word2 = words[i];
				int len = Math.min(word1.length(), word2.length());
				for(int j=0; j<len; ++j)
				{
					char s = word1.charAt(j);
					char v = word2.charAt(j);

					if(s==v)
						continue;
					if(!graph.containsKey(s))
						graph.put(s, new HashSet<Character>());
					graph.get(s).add(v);
				}
			}

			Map<Character, Integer> indegree = new HashMap<>();
			for(Character key: graph.keySet())
			{
				if(!indegree.containsKey(key))
				{
					indegree.put(key, 0);
				}
				for(Character ch: graph.get(key))
					indegree.put(ch, indegree.getOrDefault(ch, 0)+1);
			}

			Queue<Character> queue = new LinkedList<>();
			for(Character key : indegree.keySet())
			{
				if(indegree.get(key)==0)
					queue.offer(key);
			}

			StringBuilder sBuilder = new StringBuilder();
			while(!queue.isEmpty())
			{
				Character node = queue.poll();
				sBuilder.append(node);
				if(graph.containsKey(node))
				{
					for(Character v : graph.get(node))
					{
						indegree.put(v, indegree.get(v)-1);
						if( indegree.get(v)==0)
							queue.offer(v);
					}
				}

			}

			return queue.isEmpty() ? sBuilder.toString() : "";

		}


}







































}
