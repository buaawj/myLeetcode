/*
 * =====================================================================================
 *
 [LeetCode#265] Paint House II
Problem:

There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?
 *
 * =====================================================================================
 */

/* O(nk^2)*/
public int minCostII(int[][] costs) 
{
    int n = costs.size(), k = costs[0].size();
    vector<vector<int>> dp(n, vector<int>(k, 0));

    for(int i=0; i<n; ++i)
    {
        for(int j=0; j<k; ++j)
        {
            dp[i][j] = INT_MAX;
            for(int p = 0; p<k; ++p)
            {
                if(p != j)
                {
                    dp[i][j] = min(dp[i][j], i==0 ? cost[i][j] : dp[i-1][p]+cost[i][j]);
                }
            }
        }
    }

    int res = INT_MAX;
    for(int i=0; i<k; ++i)
        res = min(res, dp[n-1][i]);
    return res;
}

/* Optimization 1: O(nk) */
public int minCostII(int[][] costs) 
{
    int n = costs.size(), k = costs[0].size();
    vector<vector<int>> dp(n, vector<int>(k, 0));

    for(int i=0; i<n; ++i)
    {
        for(int j=0; j<k; ++j)
        {
            dp[i][j] = INT_MAX;
            for(int p = 0; p<k; ++p)
            {
                if(p != j)
                {
                    dp[i][j] = min(dp[i][j], i==0 ? cost[i][j] : dp[i-1][p]+cost[i][j]);
                }
            }
        }
    }

    int res = INT_MAX;
    for(int i=0; i<k; ++i)
        res = min(res, dp[n-1][i]);
    return res;
}