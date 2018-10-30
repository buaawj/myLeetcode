import java.util.ArrayList;

/*
Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

Note:

A word cannot be split into two lines.
The order of words in the sentence must remain unchanged.
Two consecutive words in a line must be separated by a single space.
Total words in the sentence won't exceed 100.
Length of each word is greater than 0 and won't exceed 10.
1 ≤ rows, cols ≤ 20,000.
Example 1:

Input:
rows = 2, cols = 8, sentence = ["hello", "world"]

Output:
1

Explanation:
hello---
world---

The character '-' signifies an empty space on the screen. */
******************************* Solution 1 ********************************************
public int wordsTyping(String[] sentence, int rows, int cols) {
    // eg. dp[index] means if the row start at index then the start of next row is dp[index].
    // dp[index] can be larger than the length of the sentence, in this case, one row can span multiple sentences.
     int[] dp = new int[sentence.length];
    int n = sentence.length;
    for(int i = 0, prev = 0, len = 0; i < sentence.length; ++i) {
        // remove the length of previous word and space
        if(i != 0 && len > 0) len -= sentence[i - 1].length() + 1;
        // calculate the start of next line.
        // it's OK the index is beyond the length of array so that
        // we can use it to count how many words one row has.
        while(len + sentence[prev % n].length() <= cols) len += sentence[prev++ % n].length() + 1;
        dp[i] = prev;
    }
    int count = 0;
    for(int i = 0, k = 0; i < rows; ++i) {
        // count how many words one row has and move to start of next row.
        // It's better to check if d[k] == k but I find there is no test case on it.
        // if(dp[k] == k) return 0;
        count += dp[k] - k;
        k = dp[k] % n;
    }
    // divide by the number of words in sentence
    return count / n;
}

 ************************************************ Solutioin 2 **********************************
 public int wordsTyping(String[] sentence, int rows, int cols) {
       char[] all = (String.join(" ", sentence) + " ").toCharArray();

      int start = 0, len = all.length;
      for(int i=0; i<rows; ++i)
      {
          start += cols;
          if(all[start%len] ==' ')
              ++start;
          else
          {
              while(start>0 && all[start%len]!=' ')--start;
              ++start;
          }
      }

      return start/len;
 }


/// Evaluate division
 public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Union helper = new Union();

        for(int i = 0; i < equations.length; i++) {
            helper.insert(equations[i][0]);
            helper.insert(equations[i][1]);
            helper.union(equations[i][0], equations[i][1], values[i]);
        }

        double [] res = new double [queries.length];
        for(int i = 0; i < queries.length; i++) {
            res[i] = helper.search(queries[i][0], queries[i][1]);
        }

        return res;
    }

    class Union {
        Map<String, String> father;
        Map<String, Double> ratio;

        public Union () {
            father = new HashMap<>();
            ratio = new HashMap<>();
        }

        public void insert(String node) {
            if(!father.containsKey(node)) {
                father.put(node, node);
                ratio.put(node, 1D);
            }
        }

        public String find(String node) {
            String parent = father.get(node);
            if(parent.equals(node)) return parent;
            String root = find(parent);
            father.put(node, root);
            ratio.put(node, ratio.get(node) * ratio.get(parent));
            return root;
        }

        public void union(String a, String b, double val) {
            String f1 = find(a), f2 = find(b);
            if(f1.equals(f2)) return;
            father.put(f2, f1);
            ratio.put(f1, 1D);
            ratio.put(f2, ratio.get(a)/(ratio.get(b) * val));
            return;
        }

        public double search(String a, String b) {
            if(!father.containsKey(a) || !father.containsKey(b)) return -1.0;
            String p1 = find(a), p2 = find(b);
            if(!p1.equals(p2)) return -1.0;

            return ratio.get(a) / ratio.get(b);
        }
    }


































}
