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

	public String alienOrder(String[] words) {
			 int n = words.length;
			 Map<Character, Set<Character>> graph = new HashMap<>();
			 Set<Character> allchars = new HashSet<>();
			 Map<Character, Integer> indegree = new HashMap<>();

			 for(String word : words)
			 {
					 for(char ch : word.toCharArray())
					 {
							 allchars.add(ch);
							 indegree.putIfAbsent(ch, 0);
					 }
			 }

			 for(int i=1; i<n; ++i)
			 {
				 String s = words[i-1];
				 String v = words[i];

				 for(int j=0; j<Math.min(s.length(), v.length()); ++j)
				 {
					 if(s.charAt(j)==v.charAt(j))
						 continue;
					 if(!graph.containsKey(s.charAt(j)))
					 {
						 graph.put(s.charAt(j), new HashSet<>());
						 }

					 graph.get(s.charAt(j)).add(v.charAt(j));
							 break;
					 }
			 }

			 for(char key : graph.keySet())
			 {
				 for(char neighbor : graph.get(key))
					 indegree.put(neighbor, 1+indegree.getOrDefault(neighbor, 0));
			 }

			 Queue<Character> queue = new LinkedList<>();
			 for(char key : indegree.keySet())
			 {
				 if(indegree.get(key)==0)
					 queue.offer(key);
			 }

			 StringBuilder ret = new StringBuilder();
			 while(!queue.isEmpty())
			 {
				 char ch = queue.poll();
				 ret.append(ch);

					 if(graph.containsKey(ch))
					 {
							 for(char neighbor : graph.get(ch))
					 {
						 indegree.put(neighbor, indegree.get(neighbor)-1);
						 if(indegree.get(neighbor)==0)
							 queue.offer(neighbor);
					 }
					 }
			 }

			 // Bug: here it should not check queue.size()!=0
			 return ret.length() !=allchars.size() ? "" : ret.toString();
	 }
}

// dfs:

class Solution {
    public String alienOrder(String[] words) {
        int n = words.length;
        Map<Character, Set<Character>> graph = new HashMap<>();
        Set<Character> allchars = new HashSet<>();

        for(String word : words)
        {
            for(char ch : word.toCharArray())
            {
                allchars.add(ch);
            }
        }

        for(int i=1; i<n; ++i)
        {
        	String s = words[i-1];
        	String v = words[i];

        	for(int j=0; j<Math.min(s.length(), v.length()); ++j)
        	{
        		if(s.charAt(j)==v.charAt(j))
        			continue;
    		    if(!graph.containsKey(s.charAt(j)))
    		    {
    			    graph.put(s.charAt(j), new HashSet<>());
        	    }

    		    graph.get(s.charAt(j)).add(v.charAt(j));
                break;
            }
        }

        Stack<Character> res = new Stack<>();
        Set<Character> path = new HashSet<>();
        Map<Character, Boolean>visited = new HashMap<>();

        for(char s : allchars)
            if (!visited.containsKey(s) && dfs(graph, visited, res, path, s))
            return "";
        StringBuilder ret = new StringBuilder();
        while(!res.isEmpty())
            ret.append(res.pop());
        return ret.length() !=allchars.size() ? "" : ret.toString();
    }

    boolean dfs(Map<Character, Set<Character>> graph, Map<Character, Boolean>visited, Stack<Character> res, Set<Character> path, char s)
    {
        visited.put(s, true);
        path.add(s);

        if(graph.containsKey(s))
        {
            for(char neighbor : graph.get(s))
            {
                if(!visited.containsKey(neighbor))
                {
                	if(dfs(graph, visited, res, path, neighbor))
                		return true;
                }
                else if(path.contains(neighbor))
                {
                    return true;
                }
            }
        }

        path.remove(s);
        res.push(s);
        return false;
    }
}







































}
