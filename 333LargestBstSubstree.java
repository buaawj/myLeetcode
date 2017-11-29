import java.util.ArrayList;

/*
[LeetCode] Largest BST Subtree 最大的二分搜索子树
 

Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here's an example:

    10
    / \
   5  15
  / \   \ 
 1   8   7
The Largest BST Subtree in this case is the highlighted one. 
The return value is the subtree's size, which is 3.
 */

 public static class Result
    {
    	int lower, upper;
    	int size;
    	boolean isBst;
    	public Result() {
			// TODO Auto-generated constructor stub
    		size = 0;
    		lower = Integer.MAX_VALUE;
    		upper = Integer.MIN_VALUE;
    		isBst = false;
		}
    }
    
    public static int largestBSTSubtree(TreeNode root) 
    {
        return helper(root).size;
    }
    
    private static Result helper(TreeNode root)
    {
    	Result result = new Result();
    	if(root == null)
    	{
    		result.isBst = true;
    		return result;
    	}
    	
    	Result left = helper(root.left);
    	Result right = helper(root.right);

    	result.lower = Math.min(left.lower, root.val);
    	result.upper = Math.max(right.upper, root.val);
    	if(left.isBst && right.isBst)
    	{
    		if(root.val>left.upper && root.val<right.lower)
    		{
    			result.size = left.size+right.size+1;
    			result.isBst = true;
    			return result;
    		}
    	}
    	
    	result.size = Math.max(left.size, right.size);
    	return result;
    }























}
