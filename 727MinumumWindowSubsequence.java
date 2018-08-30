import java.util.ArrayList;

/*
Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

nums: [1,2,3]

Result: [1,2] (of course, [1,3] will also be ok)
Example 2:

nums: [1,2,4,8]

Result: [1,2,4,8]

Create an array divCount[] of same size as input array.
divCount[i] stores size of divisible subset ending with
arr[i] (In sorted array). The minimum value of divCount[i] would be 1
 */

public class Solution {

    public List<Integer> largestDivisibleSubset(int[] nums)
    {
      int n = nums.length;
      int[] dp = new int[n];
      int[] path = new int[n];

      int maxindex = -1;
      int maxvalue = 0;
      Arrays.sort(nums);
      for(int i=0; i<n; ++i)
      {
    	  dp[i] = 1;
          for(int j=0; j<i; ++j)
          {
              if(nums[i]%nums[j]==0 && dp[j]+1 > dp[i])
              {
                  dp[i] = dp[j]+1;
                  path[i] = j;
              }
          }

          if(maxvalue < dp[i])
          {
            maxvalue = dp[i];
            maxindex = i;
          }
      }

      List<Integer> ret = new ArrayList<>();
      for(int i=0; i<maxvalue; ++i)
      {
          ret.add(nums[maxindex]);
          maxindex = path[maxindex];
      }

      return ret;
    }

}







































}
