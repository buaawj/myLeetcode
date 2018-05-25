import java.util.ArrayList;

/*
Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

Example 1:

Input:
org: [1,2,3], seqs: [[1,2],[1,3]]

Output:
false

Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
Example 2:

Input:
org: [1,2,3], seqs: [[1,2]]

Output:
false

Explanation:
The reconstructed sequence can only be [1,2].
Example 3:

Input:
org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]

Output:
true

Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
Example 4:

Input:
org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]

Output:
true
*/

/*
Topological Sort: This problem is to determine if there‘s one, and only
one sequence to sort a DAG. The method is to check if the queue‘s size
is always 1 or not. If the queue has over 1 size when we‘re conducting
topological sort, we return false, which implies that there exists more
than 1 sequence to sort this DAG
*/

public boolean sequenceReconstruction(int[] org, int[][] seqs)
{
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    int[] indegree = new int[org.length+1];

    // Build the graph first
    for(int i=0; i<org.length; ++i)
    {
        graph.put(org[i], new HashSet<Integer>();
    }

    for(int[] seq : seqs)
    {
        for(int i=1; i<seq.length; ++i)
        {
            int src = seq[i-1], dst = seq[i];
            graph.get(src).add(dst);
            indegree[dst]++;
        }
    }

    // BFS
    Queue<Integer> queue = New LinkedList<>();
    for(int i=1; i<indegree.length; ++i)
        if(indegree[i]==0)
            queue.offer(i);

    int cnt = 0;
    while(q.size()==1)
    {
        int top = queue.poll();
        for(int next : graph.get(top))
        {
            if(--indegree[next]==0)
                queue.offer(next);
        }
        if(top!=org[cnt])
            return false;
        ++cnt;
    }

    return cnt == org.length;

}
