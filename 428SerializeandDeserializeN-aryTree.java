import java.util.ArrayList;

/*
Serialization is the process of converting a data structure or object into a sequence of bits so that
it can be stored in a file or memory buffer, or transmitted across a network connection link to be
reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree
in which each node has no more than N children. There is no restriction on how your
serialization/deserialization algorithm should work. You just need to ensure that an N-ary
tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following 3-ary tree

                  1
                3 2  4
              5 6

as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {

        if (root == null)
          return "";

        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    void serializeHelper(Node root, StringBuilder sb)
    {
        if (root == null)
          return;
        sb.append(root.val+",");
        for(Node child : root.children)
          serializeHelper(child, sb);
        sb.append("#" + ",");
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        int[] index = new int[1];
        String[] datas = data.split(",");

        return deserializeHelper(datas, index);
    }

    Node deserializeHelper(String[] datas, int[] index)
    {
      if (index[0] == datas.length)
        return null;
      Node root = new Node(Integer.parseInt(datas[index[0]]), new ArrayList<Node>());
      ++index[0];
      while(index[0] < datas.length && !datas[index[0]].equals("#")) {
            root.children.add(deserializeHelper(datas, index));
    }

    // 此时表示遍历current node的children结束，遇见 #，所以pass该marker
    ++index[0];
    return root;
}






















}
