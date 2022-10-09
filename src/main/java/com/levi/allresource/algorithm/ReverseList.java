package com.levi.allresource.algorithm;

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
     * ��ת����������������
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
     * ��ת����Ĵӿ�ͷ���ڵ�z���֣���������
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
        // listָ��ԭ�����ͷ��㣬Ҳ���Ƿ�ת���ֵ����һ���ڵ㣬���������ǰѷ�ת���ֺ�δ��ת���ֽ���
        list.next = z;
        return pre;
    }

    /**
     * k��һ�鷭ת
     * ����˼������ʵ�ַ�תǰk���ڵ�ķ��������ڴˣ�����k��k���ط�ת����ÿ���ֽ��ϼ���
     * @param head
     * @param k
     * @return
     */
    private static ListNode reverseKGroup(ListNode head, int k) {

        if (head == null) return null;
        ListNode z = head, a = head;
        for (int i = 0; i < k; i++) {
            // �������k�������÷�ת������ԭ˳��ֱ�ӷ���head
            if (z == null) return head;
            z = z.next;
        }
        ListNode newHead = reverseListToZ(a, z);
        // ��ʱaָ��ǰk����ת���β�ڵ�
        a.next = reverseKGroup(z, k);
        return newHead;
    }



}
