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

    public boolean isEmpty() {
        return size == 0;
    }
}
