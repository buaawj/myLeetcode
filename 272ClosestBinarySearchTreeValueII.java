import java.util.ArrayList;
/*
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Example:
Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

    4
   / \
  2   5
 / \
1   3

Output: [4,3]
*/
Solution 1:
class Solution {
public  List<Integer> closestKValues(TreeNode root, double target, int k) {
	  Stack<TreeNode> pre = new Stack<>();
	  Stack<TreeNode> suc = new Stack<>();

	  TreeNode node = root;
	  while(node!=null)
	  {
		  if(node.val < target)
		  {
			  pre.push(node);
			  node = node.right;
		  }
		  else
		  {
			  suc.push(node);
			  node = node.left;
		  }
	  }

	  List<Integer> ret = new ArrayList<>();
	  while(k-->0)
	  {
		  if(suc.isEmpty() || (!pre.isEmpty() && target-pre.peek().val < suc.peek().val - target) )
		  {
			  ret.add(pre.peek().val);
			  getPredecessor(pre);
		  }
		  else
		  {
			  ret.add(suc.peek().val);
			  getSuccessor(suc);
		  }
	  }

	  return ret;
  }

   void getPredecessor(Stack<TreeNode> pre)
  {
	  TreeNode node = pre.pop();
	  if(node.left!=null)
	  {
		  pre.push(node.left);
		  TreeNode temp = pre.peek().right;
		  while(temp!=null)
		  {
			  pre.push(temp);
			  temp = temp.right;
		  }
	  }
  }

      void getSuccessor(Stack<TreeNode> suc)
  {
	  TreeNode node = suc.pop();
	  if(node.right!=null)
	  {
		  suc.push(node.right);
		  TreeNode temp = suc.peek().left;
		  while(temp!=null)
		  {
			  suc.push(temp);
			  temp = temp.left;
		  }
	  }
  }
}
/**
 Solution 2
 */

 class Solution {
  public List<Integer> closestKValues(TreeNode root, double target, int k) {
      PriorityQueue<double[]> queue = new PriorityQueue<>((a, b)->b[0]-a[0]>0 ? 1 : -1);

      List<Integer>  ret = new ArrayList<>();
      helper(root, target, k, queue);

      while(!queue.isEmpty())
      {
    	  ret.add((int)queue.poll()[1]);
      }
      return ret;
  }

  void helper(TreeNode root, double target, int k, PriorityQueue<double[]> queue)
  {
      if(root == null)
          return;
        helper(root.left, target, k, queue);
      queue.offer(new double[]{Math.abs(target - root.val), root.val});
      if(queue.size() > k)
          queue.poll();
      helper(root.right, target, k, queue);
  }

}






















}
