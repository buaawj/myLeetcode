import java.util.ArrayList;

/*
 * 
 * 314.	Binary Tree Vertical Order Traversal
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
 

return its vertical order traversal as:

[
  [9],
  [3,15],
  [20],
  [7]
]
 

Given binary tree [3,9,20,4,5,2,7],

    _3_
   /   \
  9    20
 / \   / \
4   5 2   7
 

return its vertical order traversal as:

[
  [4],
  [9],
  [3,5,2],
  [20],
  [7]
]

 */

class Solution {
    class Node
    {
        TreeNode node;
        int depth;
        Node(TreeNode node, int depth)
        {
            this.node = node;
            this.depth = depth;
        }
    }
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if(root == null)
            return ret;
        Map<Integer, List<Integer>> map  = new TreeMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, 0));
        
        while(!queue.isEmpty())
        {
            Node node = queue.poll();
            TreeNode cur = node.node;
            int width = node.depth;
            map.putIfAbsent(width, new ArrayList<>());
            map.get(width).add(cur.val);
            
            if(cur.left !=null)
            {
                queue.offer(new Node(cur.left, width-1));
            }
            
            if(cur.right !=null)
            {
                queue.offer(new Node(cur.right, width+1));
            } 
        }
               
        for(int key : map.keySet())
        {
            ret.add(map.get(key));
        }
        
        return ret;
    }
}


public class Solution {

	 public static List<List<Integer>> verticalOrder(TreeNode root) 
	    {
	    	List<List<Integer>> ret = new ArrayList<>();
	    	TreeMap<Integer, List<Integer>> map = new TreeMap<>();
	    	verticalorderHelper(root, map, 0);
	    	for(Entry<Integer, List<Integer>> entry : map.entrySet())
	    	{
	    		ret.add(entry.getValue());
	    		System.out.println(entry.getValue());
	    	}
	    	
	    	return ret;
	    }
	    
	    private static void	verticalorderHelper(TreeNode node, TreeMap<Integer, List<Integer>> map, int depth)
	    {
	    	if(node == null)
	    	{
	    		return;
	    	}
	    	
	    	if(!map.containsKey(depth))
	    	{
	    		map.put(depth, new ArrayList<Integer>());
	    	}
	    	
	    	map.get(depth).add(node.val);
	    	verticalorderHelper(node.left, map, depth-1);
	    	verticalorderHelper(node.right, map, depth+1);
	    }
	    
}







































}
