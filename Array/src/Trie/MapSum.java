// 677

package Trie;

import java.util.TreeMap;

public class MapSum {

    private class Node {
        //  使用value表示该节点是不是word的结尾
        public int value;
        public TreeMap<Character, Node> next;

        public Node (int value) {
            this.value = value;
            next = new TreeMap<>();
        }

        public Node () {
            this(0);
        }
    }

    private Node root;

    public MapSum () {
        root = new Node();
    }

    public void insert (String word, int val) {
        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }

            cur = cur.next.get(c);
        }
        cur.value = val;
    }

    public int sum (String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);

            if (cur.next.get(c) == null)
                return 0;

            cur = cur.next.get(c);
        }

        // 遍历以cur为根节点的多叉树，有多少个单词与cur表示的前缀有关，求权重和
        return sum(cur);
    }

    private int sum (Node node) {

        //  遍历到底的情况，可省略
        //  因为size为0，for循环便不执行
        //  if (node.next.size() == 0)
        //      return node.value;

        int res = node.value;

        //  将所有子树的value值加起来
        for (char c: node.next.keySet())
            res += sum(node.next.get(c));

        return res;
    }
}
