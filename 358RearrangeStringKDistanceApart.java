import java.util.ArrayList;

/*
Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:
str = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.
Example 2:
str = "aaabc", k = 3

Answer: ""

It is not possible to rearrange the string.
Example 3:
str = "aaadbbcc", k = 2

Answer: "abacabcd"

Another possible answer is: "abcabcda"

The same letters are at least distance 2 from each other.
Credits:
Special thanks to @elmirap for adding this problem and creating all test cases.
 */

 public static String rearrangeString(String str, int k)
     {
         int len = str.length();
         Map<Character, Integer> count = new HashMap<>();
         for(int i=0; i<str.length(); ++i)
         {
             char ch = str.charAt(i);
             count.put(ch, count.getOrDefault(ch, 0)+1);
         }

         //PriorityQueue<Character> queue = new PriorityQueue<>( (a, b)-> count.get(b) - count.get(a) );
         PriorityQueue<Character> queue = new PriorityQueue<>( (c1, c2)->   count.get(c2).intValue()!=count.get(c1).intValue()
              ?count.get(c2)-count.get(c1): c1.compareTo(c2)
         );

         for(char ch : count.keySet())
             queue.offer(ch);

         StringBuilder sb = new StringBuilder();
         while(!queue.isEmpty())
         {
             List<Character> temp = new ArrayList<>();
             int cnt = Math.min(k, len);
             for(int i=0; i<cnt; ++i)
             {
                 if(queue.isEmpty())
                     return "";
                 char ch = queue.poll();
                 sb.append(ch);
                 count.put(ch, count.get(ch)-1);
                 if(count.get(ch)>0)
                 {
                     temp.add(ch);
                 }
                 --len;
             }

             for(char ch : temp)
                 queue.offer(ch);
         }

         return sb.toString();
     }
     























}
