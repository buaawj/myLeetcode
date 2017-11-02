import java.util.ArrayList;

/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Hint:

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree? Show More Hint Note:
you can assume that no duplicate edges will appear in edges. Since all edges are undirected,
[0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */

public class Solution {
	public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
				for(int i=0; i<edges.length; ++i)
				{
					int x = edges[i][0], y = edges[i][1];
					if(uf.find(x)==uf.find(y))
						return false;
					uf.doUnion(x, y);
				}

				return uf.count==1;
    }

		public class UnionFind
		{
				private int[] parents;
				private int[] ranks;
				prviate int count;

				public UnionFind(int n)
				{
					parent = new int[n];
					for(int i=0; i<n; ++i)
						parents[i] = i;
					ranks = new int[n];
					count = n;
				}

				public int Find(int x)
				{
					while(x!=parent(x))
						x = parent(x);

						return x;
				}

				public void doUnion(int x, int y)
				{
						int px = find(x);
						int py = find(y);
						if(px==py)
							return ;

						if(ranks[px]<ranks[py])
						{
							parents[px] = py;
						}
						else
						{
							parents[py] = px;
							if(ranks[px]==ranks[py])
								++ranks[px];
						}
						--count;
				}
		}


}







































}
