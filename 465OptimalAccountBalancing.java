import java.util.ArrayList;

/*
A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.

Note:

A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6

Input:
[[0,1,10], [2,0,5]]

Output:
2

Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.

Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.

Input:
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

Output:
1

Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.

Therefore, person #1 only need to give person #0 $4, and all debt is settled.

 */
dp:

public static int minTransfers(int[][] transactions) {
	        Map<Integer, Integer> debt = new HashMap<>();
	        for (int[] t : transactions) {
	            debt.put(t[0], debt.getOrDefault(t[0], 0) - t[2]);
	            debt.put(t[1], debt.getOrDefault(t[1], 0) + t[2]);
	        }

	        int[] account = new int[debt.size()];
	        int len = 0;
	        for (int v : debt.values()) {
	            if (v != 0) {
	                account[len++] = v;
	            }
	        }

	        if (len == 0) {
	            return 0;
	        }

	        int[] dp = new int[1 << len];
	        Arrays.fill(dp, Integer.MAX_VALUE/2 - 1);
	        for (int i = 1;i < dp.length;i++) { // go through all the subsets
	            int sum = 0, count = 0;
	            for (int j = 0; j < len; j++) {
	                if (((1 << j) & i) != 0) { // this subset has jth person
	                    sum += account[j];
	                    count++;               // minimum transactions to balance accounts
	                }
	            }

	            if (sum == 0) { // if balance = 0, it is a subse problem
	                dp[i] = count - 1;
	                for (int j = 1; j < i; j++) {
	                    if (((i & j) == j) && dp[j] + dp[i-j] < dp[i]) {
	                        dp[i] = dp[j] + dp[i-j];
	                    }
	                }
	            }
	        }

	        return dp[dp.length - 1];
	    }

??????????????????????????????????????????????????????????????????????????????????
dfs:

public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int[] trans : transactions)
        {
        	map.put(trans[0], trans[2]+map.getOrDefault(trans[0], 0));
        	map.put(trans[1], -trans[2]+map.getOrDefault(trans[1], 0));
        }

        List<Integer> accnt = new ArrayList<>();
        for(int key : map.keySet())
        {
            int balance = map.get(key);
            if(balance !=0)
                accnt.add(balance);
        }

        return helper(accnt, 0, 0);
    }

    int helper(List<Integer> accnt, int start, int count)
    {
        int ret = Integer.MAX_VALUE, n=accnt.size();
        while(start<n && accnt.get(start)==0) ++start;

        for(int i=start+1; i<n; ++i)
        {
            if( ((accnt.get(start) <0 ) ^ (accnt.get(i)<0)))
            {
                accnt.set(i, accnt.get(i)+accnt.get(start));
                ret = Math.min(ret, helper(accnt, start+1, count+1));
                accnt.set(i, accnt.get(i)-accnt.get(start));
            }
        }

        return ret == Integer.MAX_VALUE ? count : ret;
    }






































}
