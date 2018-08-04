package BST;

public class BST<E extends Comparable<E>> {
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

    public BST () {
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
        if (root == null) {
            root = new Node(e);
            size++;
        } else {
            add(root, e);
        }
    }

    // 向以node为根的二分搜索树中插入元素e，递归算法
    private void add (Node node, E e) {
        // 因为对null作了特殊处理，所以写了臃肿的代码，臃肿的比较；在bst1中，将null也看做一个节点
        // 第一轮比较，看看孩子是否为空
        if (e.equals(node.e)) {
            return;
        } else if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size ++;
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }

        // 不为空再递归向下查找
        if (e.compareTo(node.e) < 0) {
            add(node.left, e);
            // e.compareTo(node.e) > 0
        } else {
            add(node.right, e);
        }
    }
}
