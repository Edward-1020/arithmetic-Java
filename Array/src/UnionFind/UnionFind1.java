//  第一版
 
public class UnionFind1 implements UnionFind {
    private int[] id;

    public UnionFind1 (int size) {
        id = new int[size];

        //  每个元素都属于不同的id（不同的集合）
        for (int i = 0; i < id.length; i ++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize () {
        return id.length;
    }

    private int find (int p) {
        if (p < 0 && p >= id.length)
            throw new IllegalArgumentException("p is out of bound");

        return id[p]
    }

    //  查看元素p和元素q是否所属一个集合
    @Override
    public boolean isConnected (int p, int q) {
        //  看元素p和q的集合编号是否相当
        return find(p) == find(q);
    }

    //  合并元素p和元素q所属的集合
    //  时间复杂度为O(n)
    @Override
    public void unionElements (int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if  (pId == qID) {
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] = pID)
                id[i] = qID;
        }
    }
}