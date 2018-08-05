package BST;

public class BST1<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left, right;

        public Node (E e)   {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST1 () {
        root = null;
        size = 0;
    }

    public int size () {
        return size;
    }

    public boolean isEmpty () {
        return size == 0;
    }

    public void add (E e) {
        root = add(root, e);
    }

    // 向以node为根的二分搜索树中插入元素e，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add (Node node, E e) {
        //  对null，也认为是一个节点
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
            // e.compareTo(node.e) > 0
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    // 以node为根的二分搜索树中是否包含元素e，递归算法
    public boolean contains (E e) {
        return contains(root, e);
    }

    private boolean contains (Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    //  二分搜索树的前序遍历
    public void preOrder () {
        preOrder(root);
    }

    private void preOrder (Node node) {
        if (node == null)
            return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void main (String[] args) {
        BST1<Integer> bst = new BST1<Integer>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for(int num: nums)
            bst.add(num);
        bst.preOrder();
    }
}
