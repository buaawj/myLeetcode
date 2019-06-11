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
	 
	 
	 
	 class Solution {
    class Node
    {
        int cnt;
        char ch;
        Node(int cnt, char ch)
        {
            this.cnt = cnt;
            this.ch = ch;
        }
    }
    public String rearrangeString(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) return s;
        Map<Character, Integer> count = new HashMap<>();
        //Bug: need to rerange the sort funciton:
        // PriorityQueue<Node> queue = new PriorityQueue<>((a, b)-> b.cnt-a.cnt);
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b)-> a.cnt != b.cnt ? b.cnt - a.cnt : a.ch - b.ch);

        for(int i=0; i<s.length(); ++i)
        {
            count.put(s.charAt(i), count.getOrDefault(s.charAt(i), 0)+1);
        }
        for(char ch : count.keySet())
            queue.add(new Node(count.get(ch), ch));
        
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        while(!queue.isEmpty())
        {
            List<Node> temp = new ArrayList<>();
            int loop = Math.min(k, len);
            for(int i=0; i<loop; ++i)
            {
                if(queue.isEmpty()) return "";
                Node node = queue.poll();
                sb.append(node.ch);
                if(--node.cnt >0) temp.add(node);
                --len;
            }
            
            for(Node node : temp) queue.add(node);
        }
        
        return sb.toString();
    }
}
??????????????????????????????????????????

class Solution {
    
            //先记录str中的char及它出现在次数，存在count[]里，用valid[]来记录这个char最小出现的位置。
    //每一次把count值最大的数选出来，append到新的string后面
    public int selectedValue(int[] count, int[] valid, int i) {
        int select = Integer.MIN_VALUE;
        int val = -1;
        for (int j = 0; j < count.length; j++) {
            if (count[j] > 0 && i >= valid[j] && count[j] > select) {
                select = count[j];
                val = j;
            }
        }
        return val;
    }

    
    public String rearrangeString(String str, int k) {
        int[] count = new int[26];
        int[] valid = new int[26];
        //把每个出现了的char的个数记下来
        for (char c : str.toCharArray()) {
            count[c - 'a']++;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            //选出剩下需要出现次数最多又满足条件的字母，即是我们最应该先放的数
            int curt = selectedValue(count, valid, i);
            //如果不符合条件，返回“”
            if (curt == -1) return "";
            //选择好后，count要减少，valid要到下一个k distance之后
            count[curt]--;
            valid[curt] = i + k;
            sb.append((char)('a' + curt));
        }
        
        return sb.toString();
    }

}
     























}
