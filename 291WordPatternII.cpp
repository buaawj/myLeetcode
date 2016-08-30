/*
 * =====================================================================================
 *
[LeetCode] Word Pattern II
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:
pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
 *
 * =====================================================================================
 */

class Solution {
 public:
  bool wordPatternMatchHelper(string &pattern, string &str, unordered_map<char, string> &hashp2s, unordered_map<string, char> hashs2p, int startp, int starts)
{
  if(startp == pattern.length() && starts == str.length())
    {
    return true;
    }    

    if(startp == pattern.length() || starts==str.length())
    return false;

    if(hashp2s.count(pattern[startp]))
    {
    string w = hashp2s[pattern[startp]];
        if(w== str.substr(starts, w.size()))
      return wordPatternMatchHelper(pattern, str, hashp2s, hashs2p, startp+1, starts+w.size());
        else
            return false;
    }
    else
    {
    for(int i = starts; i<str.length(); ++i)
        {
      string s1 = str.substr(starts, i-starts+1);
            if(!hashs2p.count(s1))
            {
        hashp2s[pattern[startp]] = s1;
        hashs2p[s1] = pattern[startp];
        if(wordPatternMatchHelper(pattern, str,  hashp2s, hashs2p, starts+1, i+1))
          return true;

        hashp2s.erase(pattern[startp]);
        hashs2p.erase(s1);
            }
          }
  }
  return false;
}


 };


