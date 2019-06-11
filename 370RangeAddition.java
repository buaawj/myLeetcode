/*
 * =====================================================================================
 *
370. Range Addition
Assume you have an array of length n initialized with all 0's and are given k update operations.

Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.

Return the modified array after all k operations were executed.

Example:

Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
Output: [-2,0,3,5,3]
Explanation:

Initial state:
[0,0,0,0,0]

After applying operation [1,3,2]:
[0,2,2,2,0]

After applying operation [2,4,3]:
[0,2,5,5,3]

After applying operation [0,2,-2]:
[-2,0,3,5,3]
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
