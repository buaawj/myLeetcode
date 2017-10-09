import java.util.ArrayList;

/*
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up: Could you do it using only constant space complexity?
 */

public class Solution {
	public boolean verifyPreorder(int[] preorder) {
					Stack<Integer> s = new Stack<>();
					int min = Integer.MIN_VAL;
					for(int i=0; i<preorder.length; ++i)
					{
						if(preorder[i] < min)
							return false;
						while(!s.isEmpty() && s.peek() <= preorder[i])
							min = s.pop();

						s.push(preorder[i]);
					}

					return true;
	    }
	}







































}
