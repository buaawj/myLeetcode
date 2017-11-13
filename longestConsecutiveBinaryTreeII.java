import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.ItalicAction;
import javax.swing.tree.TreeNode;

import sun.security.mscapi.KeyStore.ROOT;

/*
[LeetCode] Binary Tree Longest Consecutive Sequence II 二叉树最长连续序列之二
 

Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:

Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
 

Example 2:

Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 

Note: All the values of tree nodes are in the range of [-1e7, 1e7].

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
