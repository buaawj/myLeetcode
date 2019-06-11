/*
 * =====================================================================================
 *
360. Sort Transformed Array
 Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

Example 1:

Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
Output: [3,9,15,33]
Example 2:

Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
Output: [-23,-5,1,7]
*/
class Solution {
    public int maxKilledEnemies(char[][] grid) { 
        int m = grid.length;
        if(m==0)
            return 0;
        int n = grid[0].length;
        int[][] up = new int[m][n], down = new int[m][n], left = new int[m][n], right = new int[m][n];
        for(int i=0; i<m; ++i)
        {
            for(int j=0; j<n; ++j)
            {
                int t = (i==0 || grid[i][j] == 'W')  ? 0 : up[i-1][j];
                up[i][j] = (grid[i][j] =='E') ? 1+t : t; 
            }
        }
        
        for(int i=m-1; i>=0; --i)
        {
            for(int j=0; j<n; ++j)
            {
                int t = (i==m-1 || grid[i][j] == 'W') ? 0 : down[i+1][j];
                down[i][j] = (grid[i][j] =='E') ? 1+t : t; 
            }
        }
        
        for(int j=0; j<n; ++j)
        {
            for(int i=0; i<m; ++i)
            {
                int t = (j==0 || grid[i][j] == 'W') ? 0 : left[i][j-1];
                left[i][j] = (grid[i][j] =='E') ? 1+t : t; 
            }
        }
        
        for(int j=n-1; j>=0; --j)
        {
            for(int i=0; i<m; ++i)
            {
                int t = (j==n-1 || grid[i][j] == 'W') ? 0 : right[i][j+1];
                right[i][j] = (grid[i][j] =='E') ? 1+t : t; 
            }
        }
        
        int ret = 0;
        for(int i=0; i<m; ++i)
            for(int j=0; j<n; ++j)
                if(grid[i][j] =='0')
                    ret = Math.max(ret, up[i][j]+down[i][j]+left[i][j]+right[i][j]);
        return ret;
    }
}
