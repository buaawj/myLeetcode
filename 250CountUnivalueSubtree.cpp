/*
LeetCode 题解(265) : Count Univalue Subtrees
Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
return 4.

*/
int countUnivalSubtrees(TreeNode* root)
{
    int res = 0;
    countUnivalSubtreesHelper(root, res);
    return res;
}

int countUnivalSubtreesHelper(TreeNode*root, int &res)
{
    if(!root)
        return 0;
    int left = countUnivalSubtreesHelper(root->left, res);
    bool isUnivalue = true;
    if(left == -1 || (left>0 && root->val != root->left->val))
    {
        isUnivalue = false;
    }

    int right = countUnivalSubtreesHelper(root->right, res);
    if(right == -1 || (right>0 && root->val != root->right->val))
    {
        isUnivalue = false;
    }

    if(isUnivalue)
    {
        int total = left + right + 1;
        res += total;
        return total;
    }
    else
        return -1;

}

// Solution 2
bool countUnivalSubtreesHelper(TreeNode*root, int &count)
{
    if(!root)
        return true;
    bool left = countUnivalSubtreesHelper(root->left, count);
    bool right = countUnivalSubtreesHelper(root->right, count);
    if(left && right && (!root->left || root->val == root->left->val) && (!root->right || root->val == root->right->val))
    {
        ++count;
        return true;
    }

    return false;
}
