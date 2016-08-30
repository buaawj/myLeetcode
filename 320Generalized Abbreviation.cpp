/*
 * =====================================================================================
 *
 Generalized Abbreviation [leetcode 320]
 Write a function to generate the generalized abbreviations of a word.

Example:
Given word = “word”, return the following list (order does not matter):
[“word”, “1ord”, “w1rd”, “wo1d”, “wor1”, “2rd”, “w2d”, “wo2”, “1o1d”, “1or1”, “w1r1”, “1o2”, “2r1”, “3d”, “w3”, “4”] 
 *
 * =====================================================================================
 */

vector<string> vector<string> generateAbbreviations(string word)
{
    vector<string> res;
    int len = word.size();
    for(int i= 0; i<(1<<len); ++i)
    {
        string one;
        int count = 0;
        for(int j=0; j<len; ++j)
        {
            if(i &(1<<j))
            {
                count++;
            }
            else
            {
                if(count)
                {
                    one += to_string(count);
                    count = 0;
                }

                one += word[j];
            }
        }

        if(count)
            one += to_string(count);
        res.push_back(one);
    }
    return res;
}

