import java.util.ArrayList;

/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
 */

class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        Queue<int[]> queue = new LinkedList<>();
        
        int m = maze.length, n = maze[0].length;
        int[][] dist = new int[m][n];
        queue.offer(new int[] {start[0],start[1]});
        for(int i=0; i<m; ++i)
        {
        	for(int j=0; j<n; ++j)
        		dist[i][j] = Integer.MAX_VALUE;
        }
        
        dist[start[0]][start[1]]=0;
        int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0,1}, {0,-1}};
        while(!queue.isEmpty())
        {            
        	int[] front = queue.poll();
        	for(int[] dir : dirs)
        	{
            	int x = front[0], y = front[1];
            	int dist1 = dist[front[0]][front[1]];

        		while(x>=0&&x<m&&y>=0&&y<n && maze[x][y]==0)
        		{
        			x += dir[0];
        			y += dir[1];
        			++dist1;
        		}
        		
        		x-=dir[0];
        		y-=dir[1];
        		--dist1;
        		if(dist[x][y] > dist1)
        		{
        			dist[x][y] = dist1;
        			if(x!=destination[0] || y!=destination[1] )
        				queue.offer(new int[] {x, y});
        		}
        	}
        }
        
        return dist[destination[0]][destination[1]]==Integer.MAX_VALUE ? -1 :dist[destination[0]][destination[1]] ;
    }
    
}






































}
