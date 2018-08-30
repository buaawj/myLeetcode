/**
Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.

Rules for a valid pattern:
Each pattern must connect at least m keys and at most n keys.
All the keys must be distinct.
If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
The order of keys used matters.

Explanation:
| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |
Invalid move: 4 - 1 - 3 - 6
Line 1 - 3 passes through key 2 which had not been selected in the pattern.

Invalid move: 4 - 1 - 9 - 2
Line 1 - 9 passes through key 5 which had not been selected in the pattern.

Valid move: 2 - 4 - 1 - 3 - 6
Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

Valid move: 6 - 5 - 4 - 1 - 9 - 2
Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.

Example:
Given m = 1, n = 1, return 9.
 */
class SummaryRanges {

    class BSTNode {
        Interval interval;
        BSTNode left;
        BSTNode right;
        BSTNode(Interval interval) {
            this.interval = interval;
        }
    }

    BSTNode findMin(BSTNode root) {
        if (root == null) return null;
        if (root.left == null ) return root;
        else return findMin(root.left);
    }

    BSTNode remove(Interval x, BSTNode root) {
        if (root == null) return null;
        else if ( x == null ) return root;
        else if (x.start > root.interval.end ) {
            root.right = remove(x, root.right);
        } else if (x.end < root.interval.start ) {
            root.left = remove(x, root.left);
        } else if (root.left != null && root.right != null) {
            root.interval = findMin(root.right).interval;
            root.right = remove(root.interval, root.right);
        } else {
            root = (root.left != null) ? root.left : root.right;
        }
        return root;
    }

    BSTNode findKey(int val, BSTNode root) {
        if (root == null) return null;
        if (root.interval.start > val) {
            return findKey(val, root.left);
        } else if (root.interval.end < val) {
            return findKey(val, root.right);
        } else return root;
    }

    BSTNode addKey(int val, BSTNode root) {
        if (root == null) {
            root = new BSTNode( new Interval(val, val) );
        } else if (root.interval.start > val) {
            root.left = addKey(val, root.left);
        } else if (root.interval.end < val) {
            root.right = addKey(val, root.right);
        }
        return root;
    }

    void inOrder(BSTNode root) {
        if (root != null) {
            inOrder(root.left);
            list.add(root.interval);
            inOrder(root.right);
        }
    }

    /** Initialize your data structure here. */

    BSTNode root;
    List<Interval> list = new ArrayList();

    public SummaryRanges() {
        root = null;
    }

    public void addNum(int val) {
        if (root == null) root = addKey(val, root);
        else {
            if (findKey(val, root) != null) return;
            BSTNode left = findKey(val - 1, root);
            BSTNode right = findKey(val + 1, root);
            if (left == null && right == null) {
                root = addKey(val, root);
            }
            else if (left != null && right == null) {
                left.interval.end++;
            }
            else if (left == null && right != null) {
                right.interval.start--;
            }
            else {


                Interval l = left.interval;
                int e = right.interval.end;
                root = remove(right.interval, root);
                l.end = e;
            }
        }
    }

    public List<Interval> getIntervals() {
        list = new ArrayList<>();
        inOrder(root);
        return list;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */
