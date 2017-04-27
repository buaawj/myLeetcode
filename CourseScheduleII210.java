import java.util.ArrayList;

/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 Solution: Topological sort
 */

public class Solution {
    private int seq;
 public  int[] findOrder(int numCourses, int[][] prerequisites) 
    {
    	if(numCourses == 0)
    		return new int[0];
    	int[] result = new int[numCourses];
    	if (prerequisites == null || prerequisites.length == 0) 
    	{
    		for (int i = 0; i < numCourses; i++) 
    		{
    			result[i] = i;
    		}
    		return result;
    	}
    	
    	seq = numCourses - 1;
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
    		if(!visited[node] && hasCycle(graph, visited, path, node, result))
    		{
    			return new int[0];
    		}
    	}
    	
    	
    	return result;
    }
    
    public  boolean hasCycle(ArrayList<ArrayList<Integer>> graph, boolean[] visited, boolean[] path, int source, int[] result)
    {
    	visited[source] = true;
    	path[source] = true;
    	for(int node : graph.get(source))
    	{
    		if(visited[node] ==false )
    		{
    			if(hasCycle(graph, visited, path, node, result))
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
    	result[seq--] = source;
    	return false;
    }
}
   
// Topological sort - BFS
public  static int[] findOrder(int numCourses, int[][] prerequisites) 
{
	if(numCourses == 0)
		return new int[0];
	int[] result = new int[numCourses];
	if (prerequisites == null || prerequisites.length == 0) 
	{
		for (int i = 0; i < numCourses; i++) 
		{
			result[i] = i;
		}
		return result;
	}
	
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
	
	int[] indegree = new int[numCourses];
	Queue<Integer> queue = new LinkedList<>();
	
	for(int i=0; i<prerequisites.length; ++i)
	{
		indegree[prerequisites[i][0]]++;
	}
	
	for(int i=0; i<numCourses; ++i)
	{
		if(indegree[i] == 0)
		{
			queue.offer(i);
		}
	}

	int index = 0;
	while(!queue.isEmpty())
	{
		int node = queue.poll();
		result[index++] = node;
		for(int neighbor : graph.get(node))
		{
			if(--indegree[neighbor] == 0)
			{
				queue.offer(neighbor);
			}
		}
	}
	

		if(index != numCourses)
			return new int[0];
		
	return result;
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
