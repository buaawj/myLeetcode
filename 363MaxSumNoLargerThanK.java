/*
 * =====================================================================================
 *
363. Max Sum of Rectangle No Larger Than K
Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:

Input: matrix = [[1,0,1],[0,-2,3]], k = 2
Output: 2 
Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
             and 2 is the max number no larger than k (k = 2).
Note:

The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?
*/
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        
        int[][] sum = new int[m][n];
        for(int i=0; i<m; ++i)
        {
            for(int j=0; j<n; ++j)
            {
                int cursum = matrix[i][j];
                if(i>0)
                    cursum += sum[i-1][j];
                if(j>0)
                    cursum += sum[i][j-1];
                if(i>0 && j>0)
                    cursum -= sum[i-1][j-1];
                sum[i][j] = cursum;
            }
        }
        
        int ret = Integer.MIN_VALUE;
        for(int row1 = 0; row1<m; ++row1)
        {
            for(int row2 = row1; row2<m; ++row2 )
            {
                TreeSet<Integer> sets = new TreeSet<>();
                sets.add(0);
                for(int j=0; j<n; ++j)
                {
                    int cursum = row1==0 ? sum[row2][j] : sum[row2][j] - sum[row1-1][j];
                    Integer floor = sets.ceiling(cursum-k);
                    if(floor!=null)
                        ret = Math.max(ret, cursum - floor);
                    sets.add(cursum);
                }
            }
        }
        
        return ret;
    }
}
