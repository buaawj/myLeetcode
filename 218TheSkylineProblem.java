class Solution {
     class Node
    {
        int height;
        boolean isStart;
        Node(int h, boolean start)
        {
            this.height = h;
            this.isStart = start;
        }
    }

    public List<int[]> getSkyline(int[][] buildings) {
        TreeMap<Integer, List<Node>> map = new TreeMap<>();
        for(int[] building :  buildings)
        {
            int start = building[0];
            int end = building[1];
            int height = building[2];

            map.putIfAbsent(start, new ArrayList<>());
            map.putIfAbsent(end, new ArrayList<>());
            map.get(start).add(new Node(height, true));
            map.get(end).add(new Node(height, false));
        }

        TreeMap<Integer, Integer> tree = new TreeMap<>();
        List<int[]> ret = new ArrayList<>();
        int prevHeight = 0, prevx = -1;
        for(int key : map.keySet())
        {
            for(Node node : map.get(key))
            {
                tree.putIfAbsent(node.height, 0);
                if(node.isStart)
                {
                    tree.put(node.height, tree.get(node.height)+1);
                }
                else
                {
                    tree.put(node.height, tree.get(node.height)-1);
                    if(tree.get(node.height) == 0)
                    {
                        tree.remove(node.height);
                    }
                }
            }

            int curHeight = tree.isEmpty() ? 0 : tree.lastKey();
            if(prevHeight != curHeight)
            {
                if(prevx >= 0)
                    ret.add(new int[]{prevx, prevHeight});
                prevx = key;
                prevHeight = curHeight;
            }
        }

       if(prevx >= 0)
            ret.add(new int[]{prevx, prevHeight});
        return ret;
    }
}
