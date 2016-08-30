/*
LeetCode 题解(245) : Group Shifted Strings
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Return:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
Note: For the return value, each inner list's elements must follow the lexicographic order.

给的例子太不具说明性了。应该举这个例子：

["eqdf", "qcpr"]。

((‘q’ - 'e') + 26) % 26 = 12, ((‘d’ - 'q') + 26) % 26 = 13, ((‘f’ - 'd') + 26) % 26 = 2

((‘c’ - 'q') + 26) % 26 = 12, ((‘p’ - 'c') + 26) % 26 = 13, ((‘r’ - 'p') + 26) % 26 = 2

所以"eqdf"和"qcpr"是一组shifted strings

*/
vector<vector<string> > groupStrings(vector<string>& strings)
{
    unordered_set<string> maps;
    unordered_map<string, vector<int>> groups;
    for(auto str: strings)
        maps.insert(str);
    for(auto str: strings)
    {
        if(maps.count(str)==0)
        continue;

        string s = str;
        do
        {
            if(maps.count(s))
            {
                groups[str].push_back(i);
                maps.erase(s);
            }
            for(int i=0; i<s.length(); ++i)
            {
                s[i] = (s[i]-'0'+1)%26 + '0';
            }

        } while(s != str);
    }

    vector<vector<string>>res;
    for(auto entry: groups)
    {
        vector<string> one;
        for(int i: entry.second)
        {
            one.push_back(strings[i]);
        }
        res.push_back(one);
    }

    return res;
}

/* Solutions 2*/
vector<vector<string>> groupStrings(vector<string>& strings)
{
    unordered_map<string, vector<int>> groups;
    for(int i=0; i<strings.size(); ++i)
    {
        groups[rollingHash(strings[i])].push_back(i);
    }

    vector<vector<string>>res;
    for(auto entry: groups)
    {
        vector<string> one;
        for(int i: entry.second)
        {
            one.push_back(strings[i]);
        }
        res.push_back(one);
    }

    return res;
}

string rollingHash(string s)
{
    char base = s[0];
    for(auto &c: s)
    {
        c = 'a'+ (c-base + 26)%26;
    }
}
