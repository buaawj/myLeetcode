import java.util.ArrayList;

/*
We have a grid of 1s and 0s; the 1s in a cell represent bricks.  A brick will not drop if and only if it is directly connected to the top of the grid, or at least one of its (4-way) adjacent bricks will not drop.

We will do some erasures sequentially. Each time we want to do the erasure at the location (i, j), the brick (if it exists) on that location will disappear, and then some other bricks may drop because of that erasure.

Return an array representing the number of bricks that will drop after each erasure in sequence.

Example 1:
Input: 
grid = [[1,0,0,0],[1,1,1,0]]
hits = [[1,0]]
Output: [2]
Explanation: 
If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop. So we should return 2.
Example 2:
Input: 
grid = [[1,0,0,0],[1,1,0,0]]
hits = [[1,1],[1,0]]
Output: [0,0]
Explanation: 
When we erase the brick at (1, 0), the brick at (1, 1) has already disappeared due to the last move. So each erasure will cause no bricks dropping.  Note that the erased brick (1, 0) will not be counted as a dropped brick.
*/

class Solution {
    class UF {
        int[] parents;
        int[] sizes;
        UF (int count) {
            parents = new int[count];
            sizes = new int[count];
            for (int i = 0; i < count; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }
        void union(int i, int j) {
            int iP = find(i);
            int jP = find(j);
            if (iP == jP) {
                return;
            }
            parents[iP] = jP;
            sizes[jP] += sizes[iP];
        }
        int find(int x) {
            while (x != parents[x]) {
                parents[x] = parents[parents[x]];
                x = parents[x];
            }
            return x;
        }
    }
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int m = grid.length;
        int n = grid[0].length;
        UF uf = new UF(m * n + 1);
        for (int[] hit : hits) {
            if (grid[hit[0]][hit[1]] == 1) {
                grid[hit[0]][hit[1]] = 2;
            }
        }
        int[] xs = new int[]{-1, 1, 0, 0};
        int[] ys = new int[]{0, 0, -1, 1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    unionAll(i, j, grid, uf);
                }
            }
        }
        int[] res = new int[hits.length];
        int count = uf.sizes[uf.find(m * n)];
        for (int i = hits.length - 1; i >= 0; i--) {
            int[] hit = hits[i];
            if (grid[hit[0]][hit[1]] == 2) {
                unionAll(hit[0], hit[1], grid, uf);
                grid[hit[0]][hit[1]] = 1;
            }
            int newCount = uf.sizes[uf.find(m * n)];
            // -1 是为了除掉hit的砖块
            res[i] = newCount > count ? newCount - count - 1 : 0;
            count = newCount;
        }
        return res;
    }
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    void unionAll(int i, int j, int[][] g, UF uf) {
        int m = g.length, n = g[0].length;
        for(int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if(x < 0 || x == m || y < 0 || y == n || g[x][y] != 1) continue;
            uf.union(i*n + j, x*n + y);
        }
        
        if(i == 0) uf.union(j, m * n);
    }
}






















}
