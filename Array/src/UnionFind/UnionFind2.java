package UnionFind;

//  第二版
 
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
    //  是否属于一个集合的判断标准是，其所属树的根节点是否相同
    @Override
    public boolean isConnected (int p, int q) {
        return find(p) == find(q);
    }

    //  合并元素p和元素q所属的集合
    //  O(h)复杂度，h为树的高度
    @Override
    public void unionElements (int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        //  在合并的过程中，简单的将树的根节点指向另一颗树
        //  增加了树的深度，使得find操作的时间复杂度增加
        parent[pRoot] = qRoot;
    }
}