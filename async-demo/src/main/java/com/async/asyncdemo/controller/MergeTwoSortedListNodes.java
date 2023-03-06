package com.async.asyncdemo.controller;

import java.util.Arrays;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

public class MergeTwoSortedListNodes {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2);
        List<Integer> list2 = Arrays.asList(1, 3, 4);
        ListNode node1 = createLinkedList(list1);
        ListNode node2 = createLinkedList(list2);
        System.out.println(node1);
        System.out.println(node2);

        System.out.println(mergeSortedNodes(node1, node2));
    }

    private static ListNode mergeSortedNodes(ListNode node1, ListNode node2) {
        if(node1 == null && node2 == null)
            return new ListNode();
        else if(node1 != null && node2 == null)
            return node1;
        else if(node1 == null)
            return node2;

        ListNode head = new ListNode();
        ListNode curr = head;
        while (node1 != null && node2 != null) {
            if(node1.val == node2.val) {
                curr.next = new ListNode(node1.val); // {0, {1,none}}
                curr = curr.next;
                curr.next = new ListNode(node2.val);
                curr = curr.next;
                node1 = node1.next;
                node2 = node2.next;
            } else if(node1.val < node2.val) {
                curr.next = new ListNode(node1.val);
                curr = curr.next;
                node1 = node1.next;
            } else {
                curr.next = new ListNode(node2.val);
                curr = curr.next;
                node2 = node2.next;
            }
        }

        while(node1 != null) {
            curr.next = new ListNode(node1.val);
            curr = curr.next;
            node1 = node1.next;
        }

        while(node2 != null) {
            curr.next = new ListNode(node2.val);
            curr = curr.next;
            node2 = node2.next;
        }

        return head.next;
    }

    private static ListNode createLinkedList(List<Integer> list) {
        ListNode head = new ListNode();
        ListNode curr = head;
        for (Integer val : list) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return head.next;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" + "val=" + val + ", next=" + next + '}';
    }
}



