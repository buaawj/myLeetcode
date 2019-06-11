import java.util.ArrayList;

/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.
*/

class Solution {
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length;
        Queue<Integer> q = new LinkedList<>();
        int[][] dists = new int[m][n];
        for(int[] dist : dists)
            Arrays.fill(dist, Integer.MAX_VALUE);
        
        Map<Integer, String> paths = new HashMap<>();
        q.offer(ball[0]*n+ball[1]);
        dists[ball[0]][ball[1]] = 0;
        int[][] dirs = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        String[] ways = new String[] {"l", "r", "u", "d" };
        
        while(!q.isEmpty())
        {
            int pos = q.poll();
            int x  = pos/n, y = pos%n;

            for(int i=0; i<dirs.length; ++i)
            {
                int newx = x, newy = y;
                int count = dists[x][y];
                String newpath = paths.getOrDefault(x*n+y, "")+ways[i];
                
                while(newx>=0 && newx<m && newy>=0 && newy<n && maze[newx][newy] ==0 && (newx!=hole[0] || newy!=hole[1]))
                {
                    newx += dirs[i][0];
                    newy += dirs[i][1];
                    ++count;
                }
                   
                if(newx!=hole[0] || newy!=hole[1])
                {
                    newx -= dirs[i][0];
                    newy -= dirs[i][1];
                    --count;
                }
                
                if(count < dists[newx][newy])
                {
                    dists[newx][newy] = count;
                    paths.put(newx*n +newy, newpath);
                    q.offer(newx*n+newy);
                }else if(count == dists[newx][newy] && paths.getOrDefault(newx*n+newy, "").compareTo(newpath)  > 0)
                {
                    paths.put(newx*n +newy, newpath);
                    q.offer(newx*n+newy);
                }
            }
        }
        
        return !paths.containsKey(hole[0]*n +hole[1]) ? "impossible" : paths.get(hole[0]*n +hole[1]);
        
    }
}

///////////////////////////////////////////////////









































}
