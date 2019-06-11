/*
 * =====================================================================================
 *
356. Line Reflection

Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

Example 1:

Input: [[1,1],[-1,1]]
Output: true
Example 2:

Input: [[1,1],[-1,-1]]
Output: false
Follow up:
Could you do better than O(n2) ?
*/

class Solution {
    public boolean isReflected(int[][] points) {
        int xmin = Integer.MAX_VALUE, xmax = Integer.MIN_VALUE;
        Map<Integer, HashSet<Integer>> sets = new HashMap<>();
        for(int[] point : points)
        {
            xmin = Math.min(xmin, point[0]);
            xmax= Math.max(xmax, point[0]);
            sets.putIfAbsent(point[0], new HashSet<>());
            sets.get(point[0]).add(point[1]);
        }

        double y = (double)(xmin+xmax)/2.0;
        for(int[] point : points)
        {
            int t =(int) (2*y-point[0]);
            if(!sets.containsKey(t) || !sets.get(t).contains(point[1]))
                return false;
        }
        
        return true;
    }
}
