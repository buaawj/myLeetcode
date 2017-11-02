import java.util.ArrayList;

/*
There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note: n and k are non-negative integers.

S[n]: last two posts have the same color, D[n]: last two posts have different colors.
S[n] = D[n-1];
D[n] = (k-1)(S[n-1]+D[n-1]);

return D[n]+S[n];
 */

public class Solution {

	 public int numWays(int n, int k)
	 {
		 int same = 0, diff = k;
		 for(int i=2; i<=n; ++i)
		 {
			 	int t = diff;
				diff = (same + diff)*(k-1);
				same = diff;
		 }

		 return same + diff;
	 }
}







































}
