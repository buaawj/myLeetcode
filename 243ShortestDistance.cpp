/*
Shortest Word Distance
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = "makes", word2 = "coding", return 1.

Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

*/

int shortestDistance(vector<string> &words, string &word1, string &word2)
{
    int id1 = -1, id2=-1;
    int shortest = INT_MAX;
    for(int i=0; i<words.size(); ++i)
    {
        if(words[i]==word1)
        {
            id1 = i;
            if(id2!=-1)
            {
                shotest = min(shortest, id1-id2);
            }
        }

        if(words[i]==word2)
        {
            id2 = i;
            if(id1!=-1)
            {
                shotest = min(shortest, id2-id1);
            }
        }
    }
    return shortest;
}

/*
Shortest Word Distance II
This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called
repeatedly many times with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and
return the shortest distance between these two words in the list.

For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = "makes", word2 = "coding", return 1.

Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/
int shortestDistanceII(vector<string> &words, string &word1, string &word2)
{
    unordered_map<string, vector<int>> map;
    for(int i=0; i<words.size(); ++i)
    {
        map[words[i]].push_back(i);
    }

    vector<int> &v1 = map[word1];
    vector<int> &v2 = map[word2];
    int shortest = INT_MAX;
    int i=0, j=0;
    while(i<v1.size() && j<v2.size())
    {
        shortest = min(shortest, abs(i-j));
        if(v1[i] < v2[j])
        {
            ++i;
        }
        else
        {
            --j;
        }
    }

    return shortest;
}

/*
Shortest Word Distance III
This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “makes”, word2 = “coding”, return 1. Given word1 = "makes", word2 = "makes", return 3.

Note: You may assume word1 and word2 are both in the list.
*/
//https://segmentfault.com/a/1190000003906667
int shortestDistanceIII(vector<string> &words, string &word1, string &word2)
{
    int id1 = -1, id2=-1;
    int shortest = INT_MAX;
    int turn = 0;
    int inc = word1==word2 ? 1: 0;
    for(int i=0; i<words.size(); ++i)
    {
        if(words[i]==word1 && turn%2==0)
        {
            id1 = i;
            if(id2!=-1)
            {
                shotest = min(shortest, id1-id2);
            }
            turn += inc;
        }
        else if(words[i]==word2)
        {
            id2 = i;
            if(id1!=-1)
            {
                shotest = min(shortest, id2-id1);
            }
            turn += inc;
        }
    }
    return shortest;
}

    public int shortestWordDistance(String[] words, String word1, String word2) {
         int idx = -1, ret = Integer.MAX_VALUE;
        for(int i=0; i<words.length; ++i)
        {
            if(words[i].equals(word1) || words[i].equals(word2))
            {
                if(idx!=-1 && (word1.equals(word2) || !words[i].equals(words[idx])))
                {
                    ret = Math.min(ret, i-idx);
                }
                
                idx = i;
            }
        }
        
        return ret;
    }

bool canFinish(int numCourses, vector<pair<int, int>>& prerequisites) {
    unordered_map<int, vector<int>>graph;
    for(auto &p: prerequisites)
    {
        graph[p.first].push_back(p.second);
    }
    vector<bool> visited(numCourses, false);
    vector<bool> path(numCourses, false);

    for(int i=0; i<numCourses; ++i)
    {
        if(!visited[i] && dfs(graph, i, visited, path))
            return false;
    }

    return true;
}

bool dfs(unordered_map<int, vector<int> > &graph, int src, vector<bool> &visited, vector<bool> &path)
{
    visited[src] = true;
    path[src] = true;
    for(int v: graph[src])
    {
        if(!visited[v])
        {
            if(dfs(graph, v, visited, path))
                return true;
        }
        else if(path[v])
            return true;
    }

    path[src] = false;
    return false;
}


/* Word Break II*/
class Solution {
public:
    vector<string> wordBreak(string s, unordered_set<string>& wordDict)
    {
        int n =s.length();
        vector<vector<bool>> dp(n, vector<bool>(n, false));
        for(int len=1; len<=n; ++len)
        {
            for(int i=0; i<=n-len; ++i)
            {
                int j = i+len-1;
                if(wordDict.find(s.substr(i, len)))
                {
                    dp[i][j] = true;
                    continue;
                }
                for(int k=i+1; k<=j; ++k)
                {
                    if(dp[i][k-1] && wordDict.find(s.substr(k, j-k+1))!=wordDict.end())
                    {
                        dp[i][j] = true;
                        break;
                    }
                }
            }
        }

        vector<string> res;
        dfs(res, "", dp, 0, s);
        return res;
    }

    void dfs(vector<string>&res, string path, vector<vector<bool>> &dp, int start, string &s)
    {
        int n=s.length();
        if(start == n)
        {
            res.push_back(path);
            return;
        }

        if(!path.empty()) path.append(" ");
        for(int i=start; i<n; ++i)
        {
            if(dp[start][i]==false)
                continue;
            dfs(res, path+s.substr(start, i-start+1), i+1, s);
        }
    }
};


class Solution {
public:
    int compareVersion(string version1, string version2)
    {
        int n1=version1.length(), n2= version2.length();
        for(int i=0, j=0; i<n1 || j<n2; ++i, ++j)
        {
            int sum1 = 0, sum2=0;
            while(i<n1 && version1[i] !='.')
            {
                sum1 = sum1*10 + version1[i]-'0';
            }

            while(j<n2 && version1[j] !='.')
            {
                sum2 = sum2*10 + version1[j]-'0';
            }

            if(sum1 > sum2)
                return 1;
            if(sum1 < sum2)
                return -1;

        }

        return 0;
    }

};
