package UnionFind;

public class UnionFind5 implements UnionFind{
    private int[] parent;
    private int[] rank;

    public UnionFind5 (int size) {
        parent = new int[size];
        rank = new int[size];

        //  每个节点都是独立的树
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
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
            //  路径压缩
            parent[p] = parent[parent[p]];
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
    //  根据两个元素所在树的rank不同判断合并方向，将rank低的集合合并到rank高的集合上
    @Override
    public void unionElements (int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        //  在合并的过程中，简单的将树的根节点指向另一颗树
        //  增加了树的深度，使得find操作的时间复杂度增加
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]){
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}
