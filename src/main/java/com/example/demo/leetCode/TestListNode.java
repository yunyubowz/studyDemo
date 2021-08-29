package com.example.demo.leetCode;

import com.example.demo.leetCode.ListNode;


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class TestListNode {
    public static void main(String[] args) {
        s(mergeTwoLists(null,new ListNode(0)));
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = null;
        while(true){
            if(l1==null){
                result = add(result,l2);
                break;
            }
            if(l2==null){
                result = add(result,l1);
                break;
            }
            if(l1.val>=l2.val){
                result = add(result,new ListNode(l2.val));
                l2 = l2.next;
                continue;
            }
            if(l2.val>l1.val){
                result = add(result,new ListNode(l1.val));
                l1 = l1.next;
            }
        }
        return result;
    }

    public static ListNode add(ListNode t,ListNode add){
        if(t == null){
            t = add;
        }else if(t.next == null){
            t.next = add;
        }else{
            add(t.next,add);
        }
        return t;
    }

    public static void s(ListNode t){
        if(t!=null&&t.next!=null){
            s(t.next);
        }else{
        }
        if(t!=null)System.out.println(t.val);
    }
}