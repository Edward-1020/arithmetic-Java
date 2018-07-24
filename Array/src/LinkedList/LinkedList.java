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

    //  获得链表的第index（0-based）个位置的元素
    //  在链表中不是一个常用的操作，练习用
    public E get (int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed, iLLegal index.");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i ++)
            cur = cur.next;
        return cur.e;
    }

    //  获得链表第一个元素
    public E getFirst () {
        return get(0);
    }

    //  获得链表的最后一个元素
    public E getLast () {
        return get(size - 1);
    }

    //  修改链表的第index（0-based）个位置的元素
    //  在链表中不是一个常用的操作，练习用
    public void set (int index, E e) {
        if (index < 0 || index >= size)
            throw new  IllegalArgumentException("Set failed. Illegal index.");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i ++)
            cur = cur.next;
        cur.e = e;
    }

    //  查找链表中是否有元素e
    public boolean contains (E e) {
        Node cur = dummyHead.next;

        while (cur != null) {
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString () {
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while (cur != null) {
            res.append(cur + "=>");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }

    //  删除链表的第index（0-based）个位置的元素
    //  在链表中不是一个常用的操作，练习用
    public E remove (int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size --;

        return retNode.e;
    }

    //  从链表中删除第一个元素，返回删除的元素
    public E removeFirst () {
        return remove(0);
    }

    //  从链表中删除最后一个元素，返回删除的元素
    public E removeLast () {
        return remove(size - 1);
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);
    }
}
