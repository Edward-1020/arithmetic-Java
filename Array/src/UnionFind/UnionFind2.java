 //  第一版
 
public class UnionFind2 implements UnionFind {
    private int[] parent;

    public UnionFind2 (int size) {
        parent = new int[size];

        //  每个节点都是独立的树
        for (int i = 0; i < size; i++)
            parent[i] = i;
    }

    @Override
    public int getSize () {
        return parent.length;
    }

    //  查找过程，查找元素p所对应的集合编号
    //  O(h)复杂度，h为树的高度
    private int find (int p) {
        if (p < 0 && p >= parent.length)
            throw new IllegalArgumentException("p is out of bound");

        while (p != parent[p]) {
            p = parent[p];
        }

        return p;
    }

    //  查看元素p和元素q是否所属于一个集合
    @Override
    public boolean isConnected (int p, int q) {
        return find(p) == find(q);
    }
}