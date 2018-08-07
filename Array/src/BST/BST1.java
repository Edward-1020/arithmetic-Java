package BST;

import java.util.*;

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

    //  非递归前序遍历
    //  使用自己的栈模拟系统栈
    public void preOrderNR () {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    public void inOrder () {
        inOrder(root);
    }


    //  中序遍历打印二分搜索树
    //  中序遍历打印结果就是数组在二分搜索树排序的结果
    //  因为中序遍历先遍历里比root节点小的左子树，再打印root,再遍历比root节点大的右子树。
    //  因此二分搜索树又叫排序树
    private void inOrder (Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }


    //  后序遍历
    //  一般用于针对先治理孩子节点的问题
    public void postOrder () {
        postOrder(root);
    }

    private void postOrder (Node node) {
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    //  二分搜索树的层序遍历
    public void levelOrder () {
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node cur = q.remove();
            System.out.println(cur.e);

            if (cur.left != null)
                q.add(cur.left);

            if (cur.right != null)
                q.add(cur.right);
        }
    }

    //  寻找二分搜索树的最小值
    public E minimum () {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }

        return minimum(root).e;
    }

    private Node minimum (Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    //  寻找二分搜索树的最大值
    public E maximum () {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }
        return maximum(root).e;
    }

    //  返回以node为根的二分搜索树的最大值所在的节点
    private Node maximum (Node node) {
        if (node.right == null)
            return node;
        return maximum(node.right);
    }

    //  删除二分搜索树的最小值所在的节点，并返回
    public E removeMin () {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    //  删除以node为根的二分搜索树的最小节点
    //  返回删除节点后的新的二分搜索树的根
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

    //  从二分搜索树中删除最大值所在的点
    public E removeMax () {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    //  删除掉以node为根的二分搜索树中的最大节点
    //  返回删除节点后新的二分搜索树的根
    private Node removeMax (Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    //  从搜索树删除元素为e的节点
    public void remove (E e) {
        root = remove(root, e);
    }

    //  删除以node为根的二分搜索树中值为e的节点，递归算法
    //  返回删除节点后新的二分搜索树的根
    private Node remove (Node node, E e) {
        if (node == null)
            return null;

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.left = remove(node.right, e);
            return node;
            // e = node.e
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            Node successor = minimum(node.right);
            //  removeMin中size--了，但是successor指着min，所以白减了
            successor.right = removeMin(node.right);
            successor.left = node.left;

            //  此时node被删除了,此时size--，与上面抵消了
            node.left = node.right = null;

            return successor;
        }
    }

    //  前序遍历打印二分搜索树
    @Override
    public String toString () {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString (int depth) {
        StringBuilder res = new StringBuilder();
        for (int i =0; i < depth; i++)
            res.append("--");
        return res.toString();
    }

    public static void main (String[] args) {
        BST1<Integer> bst = new BST1<Integer>();
//        int[] nums = {5, 3, 6, 8, 4, 2};
//        for(int num: nums)
//            bst.add(num);
//        bst.preOrder();
//        System.out.println();
//        bst.inOrder();
//
//        System.out.println();
//
//        System.out.println(bst);

        Random random = new Random();

        int n = 1000;

        for (int i = 0; i < n; i++)
            bst.add(random.nextInt(10000));

        ArrayList<Integer> nums = new ArrayList<>();
        while(!bst.isEmpty())
            nums.add(bst.removeMin());

        System.out.println(nums);
        for (int i = 1; i < nums.size(); i++)
            if (nums.get(i - 1) > nums.get(i))
                throw new IllegalArgumentException("Error");
        System.out.println("removeMin test completed");
    }
}
