package MaxHeap;

import Array.Array;

import java.util.Random;

public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap (int capacity) {
        data = new Array<E>(capacity);
    }

    public MaxHeap () {
        data = new Array<E>();
    }

    public MaxHeap (E[] arr) {
        data = new Array<E>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--)
            siftDown(i);
    }

    //  返回堆中的元素个数
    public int size () {
        return data.getSize();
    }

    //  返回一个布尔值，表示堆中是否为空
    public boolean isEmpty () {
        return data.isEmpty();
    }

    //  返回完全二叉树的数组表示中，一个索引表示的元素的父节点的索引
    private int parent (int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent");
        return (index - 1) / 2;
    }

    //  返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild (int index) {
        return index * 2 + 1;
    }

    //  返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int rightChild (int index) {
        return index * 2 + 2;
    }

    //  向堆中添加元素
    public void add (E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp (int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    //  看堆中的最大元素
    public E findMax () {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("heap is empty");
        return data.get(0);
    }

    //  取出堆中最大的元素
    public E extractMax () {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);

        return ret;
    }

    private void siftDown (int k) {
        //  左孩子越界，所以肯定没有子树了
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            //  k节点可能没有右孩子
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                // 右孩子值比左孩子大
                //  j++;
                j = rightChild(k);
            }
            //  此时data[j]是左右两个孩子中的最大值
            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;

            data.swap(k, j);
            k = j;
        }
    }

    //  取出堆中的最大元素，并且替换成元素e
    public E replace (E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }


    private static double testHeap(Integer[] testData, boolean isHeapify) {
        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<Integer>(testData);
        } else {
            maxHeap = new MaxHeap<Integer>();
            for (int num: testData)
                maxHeap.add(num);
        }

        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++)
            arr[i] = maxHeap.extractMax();

        for (int i = 1; i < testData.length; i++)
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("error");

        System.out.println("Test MaxHeap completed.");

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int n = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>();
        Random random = new Random();
        Integer[] testData = new Integer[n];
        for (int i = 0; i < n; i++)
            testData[i] = random.nextInt(Integer.MAX_VALUE);

        double time1 = testHeap(testData, false);
        System.out.println(time1);

        double time2 = testHeap(testData, true);
        System.out.println(time2);
    }
}
