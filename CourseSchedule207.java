import java.util.ArrayList;

/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 Solution: 1. dfs to check cycle
 */

public class Solution {
	 public static boolean canFinish(int numCourses, int[][] prerequisites) 
	    {
	            if(numCourses == 0 )
	    		return false;    	
	    	  if(prerequisites == null )
	    		return true;
	    	// Cannot make generic arrayList array type
	    	ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	    	for(int i=0; i<numCourses; ++i)
	    	{
	    		graph.add( new ArrayList<Integer>()); 
	    	}
	    	
	    	// Build graph first
	    	for(int i=0; i<prerequisites.length; ++i)
	    	{
	    		graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
	    	}
	    	
	    	boolean[] visited = new boolean[numCourses];
	    	boolean[] path = new boolean[numCourses];

	    	for(int node=0; node<numCourses; ++node)
	    	{
	    		if(!visited[node] && hasCycle(graph, visited, path, node))
	    		{
	    			return false;
	    		}
	    	}
	    	
	    	return true;
	    }
	    
	    public static boolean hasCycle(ArrayList<ArrayList<Integer>> graph, boolean[] visited, boolean[] path, int source)
	    {
	    	visited[source] = true;
	    	path[source] = true;
	    	for(int node : graph.get(source))
	    	{
	    		if(visited[node] ==false )
	    		{
	    			if(hasCycle(graph, visited, path, node))
	    			{
	    				return true;
	    			}
	    		}
	    		else if(path[node])
	    		{
	    			return true;
	    		}
	    	}
	    	
	    	path[source] = false;
	    	return false;
	    }
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
