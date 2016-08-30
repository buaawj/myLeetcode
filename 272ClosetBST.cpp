/*
 * =====================================================================================
 *
Problem Description:

Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 

Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:

Consider implement these two helper functions:
getPredecessor(N), which returns the next smaller node to N.
getSuccessor(N), which returns the next larger node to N.
Try to assume that each node has a parent pointer, it makes the problem much easier.
Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
You would need two stacks to track the path in finding predecessor and successor node separately.
 *
 * =====================================================================================
 */

/*1. max-heap O(nlogk)*/
class mycompare
{

public:
    bool operator()(pair<double, int>a, pair<doule, int>b)
    {
        return a.first < b.first;
    }
};
vector<int> closestKValues(TreeNode* root, double target, int k) {
{
    priority_queue<pair<double, int>, vector<pair<double, int>>, mycompare>q;
    closestKValuesHelper(root, q, target, k);
    vector<int>res;
    while(!q.empty())
    {
        res.push_back(q.top().second);
        q.pop();
    }
    return res;
}

void closestKValuesHelper(TreeNode*root, priority_queue<pair<double, int>, vector<pair<double, int>>, mycompare>&q, int target, int k)
{
    if(!root)
        return;
    q.push(make_pair(abs(target - root->val)), root->val);
    if(q.size() > k)
        q.pop();

    closestKValuesHelper(root->left, q, target, k);
    closestKValuesHelper(root->right, q, target, k);
}

/*2. using iterative stack */
vector<int> closestKValues(TreeNode* root, double target, int k) {
{
    stack<int> pre, suc;
    inorder(root, target, pre, false);
    inorder(root, target, suc, true);
    vector<int>res;
    for(int i=0; i<k; ++i)
    {
        if(pre.empty())
            res.push_back(suc.top()), suc.pop();
        else if(suc.empty())
            res.push_back(pre.top()), pre.pop();
        else if(abs(pre.top()-target) < abs(suc.top()-target))
            res.push_back(pre.top()), pre.pop();
        else
            res.push_back(suc.top()), suc.pop();

    }
    return res;
}

void inorder(TreeNode* root, double target, stack<int>s, bool reversed)
{
    if(!root)
        return;
    inorder(reversed? root->right : root->left, target, s, false );
    if(reverse && root->val <=target || !reversed && root->val>target)
        return;
    s.push(root->val);
    inorder(reversed? root->left : root->right, target, s, reverse);
}