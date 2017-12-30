import java.util.ArrayList;

/*
LeetCode – Find Leaves of Binary Tree (Java)

Given a binary tree,
collect a tree's nodes as if you were doing this: Collect and
 remove all leaves, repeat until the tree is empty.

 Given binary tree
          1
         / \
        2   3
       / \
      4   5
Returns [4, 5, 3], [2], [1].
 */

public class Solution {

    public List<List<Integer>> findLeaves(TreeNode root)
    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(result, root);
        return result;
    }

    private int helper(List<List<Integer>> ret, TreeNode root)
    {
        if(!root)
            return -1;
        int left = helper(ret, root.left);
        int right = helper(ret, root.right);
        int depth = Math.max(left, right)+1;
        if(ret.size()<=depth)
            ret.add(new ArrayList<Integer>());
        ret.get(depth).add(root.val);
        return depth;
    }

}







































}
