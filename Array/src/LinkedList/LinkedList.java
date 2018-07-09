package LinkedList;

public class LinkedList<E> {
    private class Node {
        public E e;
        public Node next;

        public Node (E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node (E e) {
            this(e, null);
        }

        public Node () {
            this(null, null);
        }

        @Override
        public String toString () {
            return e.toString();
        }
    }

    // 使用虚拟头节点技巧完成链表
    private Node dummyHead;
    private int size;

    public LinkedList () {
        dummyHead = new Node(null, null);
        size = 0;
    }

    public int getSize () {
        return size;
    }

    public boolean isEmpty () {
        return size == 0;
    }

    // 在链表的index(0_based)位置添加新的元素e
    // 非常用操作，一般链表不使用索引。
    public void add (int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("add failed, Illegal index");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;

//          Node node = new Node(e);
//          node.next = prev.next;
//          prev.next = node;

        prev.next = new Node(e, prev.next);
        size ++;
    }

    public void addLast(E e) {
        add(size, e);
    }

    // 在链表头添加元素
    public void addFirst (E e) {
//        Node node = new Node(e);
//        node.next = head;
//        head = node;

        add(0, e);
    }

}
