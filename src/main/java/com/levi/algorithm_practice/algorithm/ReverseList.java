package com.levi.algorithm_practice.algorithm;

public class ReverseList {


    private static ListNode g = new ListNode(7,null);
    private static ListNode f = new ListNode(6,g);
    private static ListNode e = new ListNode(5,f);
    private static ListNode d = new ListNode(4,e);
    private static ListNode c = new ListNode(3,d);
    private static ListNode b = new ListNode(2,c);
    private static ListNode a = new ListNode(1,b);

    private static ListNode list = a;


    private static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {

//        printList(reverseAllList(list));

//        printList(reverseListToB(list, f));

        printList(reverseKGroup(list, 2));
    }

    private static void printList(ListNode list) {
        while (list != null) {
            System.out.print(list.val + " ");
            list = list.next;
        }
        System.out.println();
    }

    /**
     * 翻转整个链表，迭代方法
     * @param list
     */
    private static ListNode reverseAllList(ListNode list){
        ListNode pre = null, cur = list, nxt = list;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    /**
     * 翻转链表的从开头到节点z部分，迭代方法
     * @param list
     */
    private static ListNode reverseListToZ(ListNode list, ListNode z){
        ListNode pre = null, cur = list, nxt = list;
        while (cur != z) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        // list指向原链表的头结点，也就是翻转部分的最后一个节点，所以下面是把翻转部分和未翻转部分接上
        list.next = z;
        return pre;
    }

    /**
     * k个一组翻转
     * 基本思想是先实现翻转前k个节点的方法，基于此，往后k个k个地翻转，把每部分接上即可
     * @param head
     * @param k
     * @return
     */
    private static ListNode reverseKGroup(ListNode head, int k) {

        if (head == null) return null;
        ListNode z = head, a = head;
        for (int i = 0; i < k; i++) {
            // 如果不够k个，不用翻转，保持原顺序，直接返回head
            if (z == null) return head;
            z = z.next;
        }
        ListNode newHead = reverseListToZ(a, z);
        // 此时a指向前k个翻转后的尾节点
        a.next = reverseKGroup(z, k);
        return newHead;
    }



}
