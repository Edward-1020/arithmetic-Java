package leetcodeSolution.linkedList;

public class Solution2 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        // 虚拟头节点统一链表对头节点和中间节点的区别
        // 这里就不需要对第一个节点进行特殊对待了
        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                // 不需要关心将删除节点的引用置为空
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return dummyHead.next;
    }
}
