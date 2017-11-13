import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.ItalicAction;
import javax.swing.tree.TreeNode;

import sun.security.mscapi.KeyStore.ROOT;

/*
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,

   1
    \
     3
    / \
   2   4
        \
         5 
Longest consecutive sequence path is 3-4-5, so return 3.

   2
    \
     3
    / 
   2    
  / 
 1 
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.

 */

public class Solution {
    public int longestConsecutive(TreeNode root) {
        if(root == null){
            return 0;
        }
        return findLongest(root, 0, root.val - 1);
    }

    private  int longestConsecutiveHelper(TreeNode root, int length, int prevVal)
    {
    	if(root ==null)
    		return length;
    
    	int curlen = root.val == prevVal+1 ? length+1 : 1;
    	
    	return Math.max(curlen, Math.max(longestConsecutiveHelper(root.left, curlen, root.val), 
    					longestConsecutiveHelper(root.right, curlen, root.val)));
    
	}






































}
