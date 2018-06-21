public class Array {
    private int[] data;
    private int size;

    // 构造函数，capacity为容量
    public Array(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    //  无参构造函数，默认容量10
    public Array() {
        this(10);
    }

    //  获取元素个数
    public int getSize() {
        return size;
    }

    //  获取容量
    public int getCapacity() {
        return data.length;
    }

    // 是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 添加
    public void addLast(int e) {
        add(size, e);
    }

    public void addFirst(int e) {
        add(0, e);
    }

    // 插入
    public void add(int index, int e) {
        if (size == data.length)
            throw new IllegalArgumentException("AddLast failed. Array is full");

        if (index < 0 || index > size)
            throw new IllegalArgumentException("AddLast failed. Array is full");


        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];

        data[index] = e;
        size ++;
    }

    int get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal");
        return data[index];
    }

    void Set(int index, int e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal");
        data[index] = e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i ++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(", ");
        }
        res.append("]");
        return res.toString();
    }
}
