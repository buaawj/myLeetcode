/*
Strobogrammatic Number I
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.

*/

public class Solution {
    bool isStrobogrammatic(string num) {
        unordered_map<char, char> map;
        map['1'] = '1';
        map['0'] = '0';
        map['6'] = '9';
        map['9'] = '6';
        map['8'] = '8';
        int left = 0, right = num.length() - 1;
        while(left<=right)
        {
            if(map.find( num[left] )==map.end() || map[num[left]]!=num[right])
                return false;
            ++left, --right;
        }
        return true;
    }
};

/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example, Given n = 2, return ["11","69","88","96"].
*/
vector<string> FindStrobogrammatic(int n)
{
    unordered_map<char, char> map;
    map['1'] = '1';
    map['0'] = '0';
    map['6'] = '9';
    map['9'] = '6';
    map['8'] = '8';
    vector<string> res;
    return FindStrobogrammaticHeper(res, 0, n-1);
}

vector<string> FindStrobogrammatic(unordered_map<char, char> map, int start, int end)
{
    vector<string> res;
    if(start > end)
    {
        res.push_back("");
        return res;
    }
    if(start == end)
    {
        return vector<string>{"1", "0", "8"};
    }

    vector<string> res1 =  FindStrobogrammatic(map, start+1, end-1);
    for(int i=0; i<res1.size(); ++i)
    {
        string str = res1[i];
        for(auto &entry: map)
        {
            if(start == 0 && entry.fist =='0')
                continue;
            str.insert(str.begin(), entry.first);
            str.push_back(entry.second);
            res.push_back(str);
        }
    }

    return res;
}

/*
LeetCode: Strobogrammatic Number III

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.
*/
int StrobogrammaticNumberIII(string low, string high)
{
    int left = low.length(), right = high.length();
    int res = 0;
    unordered_map<char, char> map;
    map['1'] = '1';
    map['0'] = '0';
    map['6'] = '9';
    map['9'] = '6';
    map['8'] = '8';
    for(int i=left; i<=right; ++i)
    {
        string temp(i, ' ');
        StrobogrammaticNumberIIIHelper(temp, low,high, 0, i-1, res, map);
    }
    return res;
}

void StrobogrammaticNumberIIIHelper(string &temp, string low, string high, int start, int end, int &res, unordered_map<char, char> &map)
{
    if(start > end)
    {
        if((temp[0]!='0' || temp.length()==1 ) && less(low, temp) && less(temp, high))
        {
            res++;
        }
        return;
    }
    for(atuo &entry : map)
    {
        temp[start] = entry.first;
        temp[end] = entry.second;
        if( (start==end && entry.first == entry.second) || start<end )
            StrobogrammaticNumberIIIHelper(temp, low, high, start+1, end-1, res, map);
    }
}

/* A function to see if s1 is less than s2 */
bool less(string &s1, string &s2)
{
    int m = s1.length(), n = s2.length();
    if(m!=n) return m < n;
    int i = 0;
    while(i<m && s1[i]==s2[i])
    {
        ++i;
    }

    return i==m || s1[i]<s2[i];
}
