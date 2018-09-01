package SegmentTree;

// 给定一个数组，求出数组从索引i到索引j( i<= k <= j)的总和。其实i和j之间的数值不可变
// - 题干
// ```
// 给定 nums = [-2， 0， 3， -5， -2， -1]，求和函数为sumRange。

// ```
// - 求解
// ```
// sumRange(0, 2) -> 1
// ```

class NumArray {
    private SegmentTree<Integer> segmentTree;

    public NumArray (int[] nums) {
        if (nums.length > 0) {
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
            segmentTree = new SegmentTree<Integer>(data, (a, b) -> a + b);
        }
    }

    public int sumRange (int i, int j) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("Segment Tree is null");
        }

        return segmentTree.query(i, j);
    }
}