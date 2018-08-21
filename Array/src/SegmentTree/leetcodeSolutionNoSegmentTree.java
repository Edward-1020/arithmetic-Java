// 给定一个数组，求出数组从索引i到索引j( i<= k <= j)的总和。其实i和j之间的数值不可变
// - 题干
// ```
// 给定 nums = [-2， 0， 3， -5， -2， -1]，求和函数为sumRange。

// ```
// - 求解
// ```
// sumRange(0, 2) -> 1
// ```

//  时间复杂度更低
class NumArray {
    private int[] sum;//  sum[i]存储前i个元素和
    private int[] data;

    private SegmentTree<Integer> segmentTree;

    public NumArray (int[] nums) {
        data = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            data[i] = nums[i];

        sum = new int[num.length + 1];
        sum[0] = 0;

        for (int i = 1; i < sum.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
    }

    public void update (int index, int val) {
        data[index] = val;
        for (int i = index + 1; i < sum.length; i++)
            sum[i] = sum[i - 1] + data[i - 1];
    }

    public int sumRange (int i, int j) {
        return sum[j + 1] - sum[i];
    }
}