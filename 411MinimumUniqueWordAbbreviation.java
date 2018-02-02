import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

/*
A string such as "word" contains the following abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Given a target string and a set of strings in a dictionary, find an abbreviation of this target string with thesmallest possible length such that it does not conflict with abbreviations of the strings in the dictionary.

Each number or letter in the abbreviation is considered length = 1. For example, the abbreviation "a32bc" has length = 4.

Note:

In the case of multiple answers as shown in the second example below, you may return any one of them.
Assume length of target string = m, and dictionary size = n. You may assume that m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.


Examples:

"apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")

"apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").
 */

 public static String minAbbreviation(String target, List<String> dictionary)
     {
         PriorityQueue<pair> queue = new PriorityQueue<>((p1, p2)-> p1.size-p2.size);
         Generate(target, queue);

         while(!queue.isEmpty())
         {
             pair top = queue.poll();
             boolean no_confilct = true;
             for(String str : dictionary)
             {
                 if(isValid(top.abbr, str))
                     no_confilct = false;
             }

             if(no_confilct)
                 return top.abbr;
         }

         return "";
     }

     private static void Generate(String target, PriorityQueue<pair> queue)
     {
         int n = target.length();
         for(int i=0; i<(1<<n); ++i)
         {
             int cnt = 0, size = 0;
             StringBuilder sb = new StringBuilder();
             for(int j=0; j<n; ++j)
             {
                 if(((i>>j) &1) !=0)
                     cnt ++;
                 else
                 {
                     if(cnt!=0)
                     {
                         sb.append(cnt);
                         cnt = 0;
                         ++size;
                     }

                     sb.append(target.charAt(j));
                     ++size;
                 }
             }

             // dont forget this
             if(cnt!=0)
             {
                 sb.append(cnt);
                 ++size;
             }

             queue.offer(new pair(size, sb.toString()));
         }



     }

     private static boolean isValid(String abbr, String target)
     {
         int p = 0;
         int cnt = 0;
         for(int i=0; i<abbr.length(); ++i)
         {
             char ch = abbr.charAt(i);
             if(ch>='0' && ch<='9')
             {
                 if(cnt==0 && ch=='0') return false;
                 cnt = cnt*10+ch-'0';
             }
             else
             {
                 if(cnt!=0)
                 {
                     p += cnt;
                     cnt = 0;
                 }

                 if(ch!=target.charAt(p))
                     return false;
                 ++p;
             }
         }

         // dont forget this
         if(cnt!=0)
             p += cnt;

         return p==target.length();
     }






































}
