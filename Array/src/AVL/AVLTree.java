package AVL;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V>{
    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node (K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree () {
        root = null;
        size = 0;
    }

    public int getSize () {
        return size;
    }

    public boolean isEmpty () {
        return size == 0;
    }

    //  判断是否是一棵二分搜索树
    public boolean isBST () {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++)
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0)
                return false;
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null)
            return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    // 判断该二叉树是否是一棵平衡二叉树
    public boolean isBalanced () {
        return isBalanced(root);
    }

    //  判断以node为根的二叉树是否是一颗平衡二叉树，递归算法
    private boolean isBalanced (Node node) {
        if (node == null)
            return true;

        int balanceFactor = getBalanceFactor(node);

        if (Math.abs(balanceFactor) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    //  获得节点node的高度
    private int getHeight (Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    //  获得节点node的平衡因子
    private int getBalanceFactor (Node node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    public void add (K key, V value) {
        root = add(root, key ,value);
    }

    // 向以node为根的二分搜索树中插入元素key-val，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add (Node node, K key, V value) {
        //  对null，也认为是一个节点
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value );
            // e.compareTo(node.e) > 0
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
            //key.compareTo(node.key) > 0 == 0
        } else {
            node.value = value;
        }

        //  更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //  平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            System.out.println("unbalanced:" + balanceFactor);
        }

        //  对于每一个节点，都维护平衡性，维护完成后，返回给上一层
        //  当前节点平衡因子大于1，不平衡。此时，左子树的平衡因子 》= 0, 及左子树高度大于右子树高度
        //  右旋转
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)

        return node;
    }

    //  返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode (Node node, K key) {
        if (node == null)
            return null;

        if (key.compareTo(node.key) == 0)
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else
            return getNode(node.right, key);
    }

    public boolean contains (K key) {
        return getNode(root ,key) != null;
    }

    public V get (K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set (K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    //  返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum (Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    //  删除掉以node为根的二分搜索树的最小节点
    //  返回删除节点后新的二分搜索树的根
    private Node removeMin (Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public V remove (K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }

        return null;
    }

    private Node remove (Node node, K key) {
        if (node == null)
            return null;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }
}
