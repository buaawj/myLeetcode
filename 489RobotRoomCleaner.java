/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */
class Solution {
      // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    int[][] dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public void cleanRoom(Robot robot) {
        Set<String> visited = new HashSet<>();
        dfs(robot, 0, 0, 0, visited);
    }
    
    void dfs(Robot robot, int x, int y, int dir, Set<String>visited)
    {
        robot.clean();
        visited.add(x + "-" + y);
        for(int i=0; i<4; ++i)
        {
            int next = (i+dir)%4, newx = x+dirs[next][0], newy = y+dirs[next][1];
            if(!visited.contains(newx+"-"+newy) && robot.move())
            {
                dfs(robot, newx, newy, next, visited);
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnLeft();
                robot.turnLeft();
            }
            
            // turn the robot following chosen direction : clockwise
            robot.turnRight();
        }
    }
}