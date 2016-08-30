/*
 * =====================================================================================
 *
[LeetCode] Binary Tree Longest Consecutive Sequence
Problem Description:

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
 *
 * =====================================================================================
 */

/**
 2  * Definition for a binary tree node.
 3  * struct TreeNode {
 4  *     int val;
 5  *     TreeNode *left;
 6  *     TreeNode *right;
 7  *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 8  * };
 9  */

class Solution {
 public:
     int longestConsecutive(TreeNode* root) {
         return longest(root, NULL, 0);
     }

     int longest(TreeNode*root, TreeNode*prev, int curlen)
     {
        if(!root)
            return curlen;
        curlen = prev&& prev->val+1 == root->val ? curlen+1 : curlen;
        return max(curlen, max(longest(root->left, root, curlen), longest(root->right, root, curlen)));
     }

 };

